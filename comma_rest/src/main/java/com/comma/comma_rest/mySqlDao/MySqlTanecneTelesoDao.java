package com.comma.comma_rest.mySqlDao;

import com.comma.comma_rest.dao.TanecneTelesoDao;
import com.comma.comma_rest.entity.TanecneTeleso;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import java.sql.*;
import java.util.List;
import java.util.Objects;

public class MySqlTanecneTelesoDao implements TanecneTelesoDao {
    private JdbcTemplate jdbcTemplate;

    public MySqlTanecneTelesoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<TanecneTeleso> tanecneTelesoRM() {
        return new RowMapper<TanecneTeleso>() {
            @Override
            public TanecneTeleso mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getInt("id_tanecne_teleso");
                String nazov = rs.getString("nazov");
                String umiestnenie = rs.getString("umiestnenie");
                String hudba = rs.getString("hudba");
                String klub = rs.getString("klub");
                String telefonne_cislo = rs.getString("telefonne_cislo");
                String tanecnici = rs.getString("tanecnici");
                String email = rs.getString("email");
                long kategoria_id = rs.getLong("kategoria_id_kategoria");
                long sutaz_id = rs.getLong("sutaz_id_sutaz");
                TanecneTeleso teleso = new TanecneTeleso(id, nazov, umiestnenie, hudba, klub, kategoria_id, sutaz_id, telefonne_cislo, email, tanecnici);
                return teleso;
            }
        };
    }

    @Override
    public TanecneTeleso insert(TanecneTeleso tanecneTeleso) {
        Objects.requireNonNull(tanecneTeleso,
                "Tanecne teleso cannot be null");
        Objects.requireNonNull(tanecneTeleso.getNazov(),
                "Tanecne teleso name cannot be null");
        Objects.requireNonNull(tanecneTeleso.getKlub(),
                "Klub cannot be null");
        Objects.requireNonNull(tanecneTeleso.getTanecnici(),
                "Tanecnici cannot be null");
        Objects.requireNonNull(tanecneTeleso.getHudba(),
                "Hudba cannot be null");
        Objects.requireNonNull(tanecneTeleso.getTelefonneCislo(),
                "Telefonne cislo cannot be null");
        Objects.requireNonNull(tanecneTeleso.getEmail(),
                "Email cannot be null");
        String query = "INSERT INTO tanecne_teleso (nazov, umiestnenie, hudba, klub, telefonne_cislo, tanecnici, email, kategoria_id_kategoria, sutaz_id_sutaz)  "
                + "VALUES (?,?,?,?,?,?,?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, tanecneTeleso.getNazov());
                statement.setString(2, tanecneTeleso.getUmiestnenie());
                statement.setString(3, tanecneTeleso.getHudba());
                statement.setString(4, tanecneTeleso.getKlub());
                statement.setString(5, tanecneTeleso.getTelefonneCislo());
                statement.setString(6, tanecneTeleso.getTanecnici());
                statement.setString(7, tanecneTeleso.getEmail());
                statement.setLong(8, tanecneTeleso.getKategoriaId());
                statement.setLong(9, tanecneTeleso.getSutazId());
                return statement;
            }
        }, keyHolder);

        tanecneTeleso.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return tanecneTeleso;
    }

    @Override
    public TanecneTeleso findById(long id) {
        String sql = "SELECT * FROM tanecne_teleso WHERE id_tanecne_teleso = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, tanecneTelesoRM());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<TanecneTeleso> findAll() {
        String query = "SELECT id_tanecne_teleso, nazov, umiestnenie, hudba, klub, telefonne_cislo, tanecnici, email, kategoria_id_kategoria, sutaz_id_sutaz FROM tanecne_teleso " + "ORDER BY umiestnenie DESC";
        List<TanecneTeleso> result = jdbcTemplate.query(query, tanecneTelesoRM());
        return result;
    }

    @Override
    public List<TanecneTeleso> findAllBySutazId(long sutazId) {
        String query = "SELECT id_tanecne_teleso, nazov, umiestnenie, hudba, klub, telefonne_cislo, tanecnici, email, kategoria_id_kategoria, sutaz_id_sutaz FROM tanecne_teleso " +
                "WHERE sutaz_id_sutaz = ? ORDER BY id_tanecne_teleso";
        return jdbcTemplate.query(query, new Object[]{sutazId}, tanecneTelesoRM());
    }

    @Override
    public List<TanecneTeleso> findAllBySutazIdKategoriaId(long  sutazId, Long kategoriaId) {
        String query = "SELECT id_tanecne_teleso, nazov, umiestnenie, hudba, klub, tanecnici, telefonne_cislo, email, kategoria_id_kategoria, sutaz_id_sutaz FROM tanecne_teleso " +
                "WHERE sutaz_id_sutaz = ? AND kategoria_id_kategoria = ? ORDER BY id_tanecne_teleso";
        List<TanecneTeleso> result = jdbcTemplate.query(query, new Object[]{sutazId, kategoriaId}, tanecneTelesoRM());
        return result;
    }

    @Override
    public TanecneTeleso update(TanecneTeleso tanecneTeleso) {
        String query = "UPDATE tanecne_teleso SET nazov = ?, umiestnenie = ?, hudba = ?, klub = ?, email = ?, telefonne_cislo = ?, tanecnici = ?, " +
                "kategoria_id_kategoria = ?, sutaz_id_sutaz = ? WHERE id_tanecne_teleso = ?";

        jdbcTemplate.update(query, tanecneTeleso.getNazov(), tanecneTeleso.getUmiestnenie(), tanecneTeleso.getHudba(), tanecneTeleso.getKlub(),
                tanecneTeleso.getEmail(), tanecneTeleso.getTelefonneCislo(), tanecneTeleso.getTanecnici(), tanecneTeleso.getKategoriaId(), tanecneTeleso.getSutazId(),
                tanecneTeleso.getId());

        return null;
    }

    @Override
    public boolean delete(TanecneTeleso tanecneTeleso) {
        String deleteQuery = "DELETE FROM tanecne_teleso WHERE id_tanecne_teleso = ?";
        int result = jdbcTemplate.update(deleteQuery, tanecneTeleso.getId());
        return result != 0;
    }


}
