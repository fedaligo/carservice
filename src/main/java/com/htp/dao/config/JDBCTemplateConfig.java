package com.htp.dao.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.htp")
public class JDBCTemplateConfig {
    @Autowired
    @Qualifier("dataSource")
    private BasicDataSource dataSource;
    //private DataSource dataSource1;

    //https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/jdbc.html
    //https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html

    // простые запросы
    @Bean("jdbcTemplate")
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

    //запросы с параметрами
    @Bean("namedJdbcTemplate")
    public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean("txManager")
    public DataSourceTransactionManager getTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    //https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/aop.html#aop-understanding-aop-proxies
    //https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/transaction.html
}
