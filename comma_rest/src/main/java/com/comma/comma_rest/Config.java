package com.comma.comma_rest;

import com.comma.comma_rest.dao.HodnotenieDao;
import com.comma.comma_rest.dao.PorotcaDao;
import com.comma.comma_rest.mySqlDao.MySqlHodnotenieDao;
import com.comma.comma_rest.mySqlDao.MySqlPorotcaDao;
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

    @Bean
    public PorotcaDao getPorotcaDao() {
        return new MySqlPorotcaDao(jdbcTemplate);
    }
}
