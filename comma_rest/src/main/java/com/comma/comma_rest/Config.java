package com.comma.comma_rest;

import com.comma.comma_rest.dao.*;
import com.comma.comma_rest.mySqlDao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class Config {

    private final JdbcTemplate jdbcTemplate;

    public Config(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public HodnotenieDao getHodnoenieDao() {
        return new MySqlHodnotenieDao(jdbcTemplate);
    }

    @Bean
    public PorotcaDao getPorotcaDao() {
        return new MySqlPorotcaDao(jdbcTemplate);
    }

    @Bean
    public KategoriaDao getKategoriaDao() {
        return new MySqlKategoriaDao(jdbcTemplate);
    }
    @Bean
    public SutazDao getSutazDao() {
        return new MySqlSutazDao(jdbcTemplate);
    }
    @Bean
    public TanecneTelesoDao getTanecneTelesoDao() {
        return new MySqlTanecneTelesoDao(jdbcTemplate);
    }

}
