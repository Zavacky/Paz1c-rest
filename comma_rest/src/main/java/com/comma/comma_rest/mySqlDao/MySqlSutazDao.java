package com.comma.comma_rest.mySqlDao;

import com.comma.comma_rest.dao.SutazDao;
import com.comma.comma_rest.entity.Sutaz;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class MySqlSutazDao implements SutazDao {
    private JdbcTemplate jdbcTemplate;


    public MySqlSutazDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Sutaz> sutazRM() {
        return new RowMapper<Sutaz>() {
            @Override
            public Sutaz mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id_sutaz");
                String nazov = rs.getString("nazov");
                LocalDate datumOd = rs.getDate("od_datum").toLocalDate();
                LocalDate datumDo = rs.getDate("do_datum").toLocalDate();

                return new Sutaz(id, nazov, datumOd, datumDo);
            }
        };
    }

    @Override
    public Sutaz findById(int id) {
        String sql = "SELECT * FROM sutaz" + " WHERE id_sutaz = " + id;
        return jdbcTemplate.queryForObject(sql, sutazRM());
    }

    @Override
    public List<Sutaz> findAll() {
        String query = "SELECT * FROM sutaz " + "ORDER BY od_datnum";
        List<Sutaz> result = jdbcTemplate.query(query, sutazRM());
        return result;
    }

    @Override
    public Sutaz insert(Sutaz sutaz) {
        String query = "INSERT INTO sutaz (nazov, od_datnum, do_datum) VALUES (?, ?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, sutaz.getNazov());
                statement.setDate(2, Date.valueOf(sutaz.getOdDatum()));
                statement.setDate(3, Date.valueOf(sutaz.getDoDatum()));
                return statement;
            }
        }, keyHolder);

        sutaz.setId(keyHolder.getKey().intValue());

        return sutaz;
    }

    @Override
    public void update(Sutaz sutaz) {
        String query = "UPDATE sutaz SET nazov = ?, sutaz.od_datnum = ?, do_datum = ? WHERE id_sutaz = ?";
        jdbcTemplate.update(query, sutaz.getNazov(), sutaz.getOdDatum(), sutaz.getDoDatum(), sutaz.getId());
    }

    @Override
    public boolean delete(Sutaz sutaz) {
        return false;
    }
}
