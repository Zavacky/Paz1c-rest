package com.comma.comma_rest;

import dao.HodnotenieDao;
import mySqlDao.MySqlHodnotenieDao;
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
    public HodnotenieDao getSubjectDao(HodnotenieDao hodnotenieDao) {
        return new MySqlHodnotenieDao(jdbcTemplate, hodnotenieDao);
    }
}
