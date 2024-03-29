package com.comma.comma_rest.mySqlDao;

import com.comma.comma_rest.dao.HodnotenieDao;
import com.comma.comma_rest.entity.Hodnotenie;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;

public class MySqlHodnotenieDao implements HodnotenieDao {

    private JdbcTemplate jdbcTemplate;

    public MySqlHodnotenieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Hodnotenie> hodnotenieRowMapper() {
        return new RowMapper<Hodnotenie>() {
            @Override
            public Hodnotenie mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id_hodnotenie");
                int hodnotenie = rs.getInt("hodnotenie");
                long porotcaId = rs.getLong("porotca_id_porotca");
                long tanecneTelesoId = rs.getLong("tanecne_teleso_id_tanecne_teleso");

                Hodnotenie hodnotenieObject = new Hodnotenie(id, hodnotenie, porotcaId, tanecneTelesoId);
                return hodnotenieObject;
            }
        };
    }

    @Override
    public List<Hodnotenie> getAll() {
        String query = "SELECT * FROM hodnotenie";
        return jdbcTemplate.query(query, hodnotenieRowMapper());
    }

    @Override
    public Hodnotenie getById(long id) {
        String query = "SELECT * FROM hodnotenie WHERE id_hodnotenie = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, hodnotenieRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Hodnotenie> getByTelesoId(long tanecneTelesoId) {
        String query = "SELECT * FROM hodnotenie WHERE tanecne_teleso_id_tanecne_teleso = ?";
        return jdbcTemplate.query(query, new Object[]{tanecneTelesoId}, hodnotenieRowMapper());
    }

    @Override
    public Hodnotenie save(Hodnotenie hodnotenie) {
        if (hodnotenie.getId() == null) { //insert
            String query = "INSERT INTO hodnotenie (hodnotenie, porotca_id_porotca, tanecne_teleso_id_tanecne_teleso) "
                    + "VALUES (?,?,?)";
            GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(con -> {
                PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, hodnotenie.getHodnotenie());
                statement.setLong(2, hodnotenie.getPorotcaId());
                statement.setLong(3, hodnotenie.getTanecneTelesoId());
                return statement;
            }, keyHolder);
            hodnotenie.setId(keyHolder.getKey().longValue());
        } else { // update
            String query = "UPDATE hodnotenie SET hodnotenie = ? WHERE id_hodnotenie = ?";
            int rowsAffected = jdbcTemplate.update(query, hodnotenie.getHodnotenie(), hodnotenie.getId());
            if (rowsAffected != 1) {
                return null;
            }
        }
        return hodnotenie;
    }

    @Override
    public Hodnotenie getByPorotcaIdAndTelesoId(long porotcaId, long tanecneTelesoId) {
        String query = "SELECT * FROM hodnotenie WHERE porotca_id_porotca = ? AND tanecne_teleso_id_tanecne_teleso = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{porotcaId, tanecneTelesoId}, hodnotenieRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM hodnotenie WHERE id_hodnotenie = ?";
        int rowsAffected = jdbcTemplate.update(query, id);
        if (rowsAffected == 0) {
            throw new EntityNotFoundException("Hodnotenie s id " + id + "sa nenašlo.");
        }
    }

    @Override
    public void deleteByTanecneTelesoId(long telesoId) {
        try {
            String deleteQuery = "DELETE FROM hodnotenie WHERE tanecne_teleso_id_tanecne_teleso = ?";
            int rowsAffected = jdbcTemplate.update(deleteQuery, telesoId);
            if (rowsAffected == 0) {
                throw new com.comma.comma_rest.entity.EntityNotFoundException("Hodnotenie tanečného telesa s id " + telesoId + "sa nenašlo.");
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
