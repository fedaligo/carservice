package com.htp.config.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;

@Setter
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties("datasource")
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

    //@Value("${driverClassName}")
    private String driverName;

    //@Value("${password}")
    private String password;

    //@Value("${url}")
    private String url;

    //@Value("${db.username}")
    private String login;

    //@Value("${initialSize}")
    private int initialSize;

    //@Value("${maxActive}")
    private int maxActive;

    @Bean(value = "dataSource", destroyMethod = "close")
    @Scope("singleton")
    @Primary
    public BasicDataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setUrl(url);
        dataSource.setUsername(login);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        return dataSource;
    }

}

