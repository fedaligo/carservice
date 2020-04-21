package com.htp.config.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Primary;

@Configuration
@PropertySource("classpath:database.properties")
public class DBConfig {

    @Value("${dddriverClassName}")
    private String driverName;

    @Value("${ddpassword}")
    private String password;

    @Value("${ddurl}")
    private String url;

    @Value("${ddusername}")
    private String login;

    @Value("${ddinitialSize}")
    private int initialSize;

    @Value("${ddmaxActive}")
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

