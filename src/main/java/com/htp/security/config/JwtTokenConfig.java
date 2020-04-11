package com.htp.security.config;

import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@NoArgsConstructor
@Configuration
//@ConfigurationProperties("app")
@ConfigurationProperties("tokenset")
public class JwtTokenConfig {

    private String secret;

    private int expire;

}

