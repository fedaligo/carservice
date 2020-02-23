package com.htp.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

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
        dataSource.setInitialSize(Integer.valueOf(Objects.requireNonNull(properties.getProperty("initialSize"))));
        dataSource.setMaxActive(Integer.valueOf(Objects.requireNonNull(properties.getProperty("maxActive"))));
        return dataSource;*/

    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${password}")
    private String password;

    @Value("${url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${initialSize}")
    private int initialSize;

    @Value("${maxActive}")
    private int maxActive;

    @Bean(value = "dataSource", destroyMethod = "close")
    @Scope("singleton")
    @Primary
    public BasicDataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        return dataSource;
    }

}

