package com.comma.comma_rest.mySqlDao;

import com.comma.comma_rest.dao.KategoriaDao;
import com.comma.comma_rest.entity.Kategoria;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.util.List;
import java.util.Objects;

public class MySqlKategoriaDao implements KategoriaDao {

    private JdbcTemplate jdbcTemplate;

    private RowMapper<Kategoria> kategoriaRM() {
        return new RowMapper<Kategoria>() {
            @Override
            public Kategoria mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getInt("id_kategoria");
                String styl = rs.getString("styl");
                String vekova_skupina = rs.getString("vekova_skupina");
                String velkostna_skupina = rs.getString("velkostna_skupina");
                Kategoria kategoria = new Kategoria(id, styl, vekova_skupina, velkostna_skupina);
                return kategoria;
            }
        };
    }

    public MySqlKategoriaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Kategoria findById(long id) {
        String query = "SELECT * FROM kategoria WHERE id_kategoria = ?";
        try {
            return jdbcTemplate.queryForObject(query, new Object[]{id}, kategoriaRM());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Kategoria> findAll() {
        String query = "SELECT * FROM kategoria " + "ORDER BY id_kategoria DESC";
        List<Kategoria> result = jdbcTemplate.query(query, kategoriaRM());
        return result;
    }

    @Override
    public Kategoria insert(Kategoria kategoria) {
        String query = "INSERT INTO kategoria (styl, vekova_skupina, velkostna_skupina) VALUES (?, ?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, kategoria.getStyl());
                statement.setString(2, kategoria.getVekovaSkupina());
                statement.setString(3, kategoria.getVelkostnaSkupina());
                return statement;
            }
        }, keyHolder);
        kategoria.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return kategoria;
    }


}
