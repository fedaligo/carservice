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
