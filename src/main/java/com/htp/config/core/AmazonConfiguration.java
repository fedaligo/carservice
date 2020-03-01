package com.htp.config.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties("amazon")
public class AmazonConfiguration {
    private String login;
    private String token;
    private String password;
}
