package com.htp.dao.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@PropertySource("classpath:database.properties")
public class DBConfig {
    /*@Autowired
    private Environment properties;
    @Bean(value = "dataSource", destroyMethod = "close")
    @Scope("singleton")
    @Primary
    public BasicDataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getProperty("driverClassName"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        //dataSource.setInitialSize(Integer.valueOf(Objects.requireNonNull(properties.getProperty("initialSize"))));
        // dataSource.setMaxActive(Integer.valueOf(Objects.requireNonNull(properties.getProperty("maxActive"))));
        return dataSource;*/

    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${password}")
    private String password;

    @Value("${url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Bean(value = "dataSource", destroyMethod = "close")
    @Scope("singleton")
    @Primary
    public BasicDataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        //dataSource.setInitialSize(Integer.valueOf(Objects.requireNonNull(properties.getProperty("initialSize"))));
        // dataSource.setMaxActive(Integer.valueOf(Objects.requireNonNull(properties.getProperty("maxActive"))));
        return dataSource;
    }
    /*
     @Value("org.postgresql.Driver")
    private String driverClassName;

    @Value("root")
    private String password;

    @Value("jdbc:postgresql://localhost:5432/test_database")
    private String url;

    @Value("test")
    private String username;

    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${password}")
    private String password;

    @Value("${url}")
    private String url;

    @Value("${username}")
    private String username;*/
}

