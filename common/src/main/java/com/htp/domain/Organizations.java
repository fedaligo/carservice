package com.htp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Organizations {
    private Long id;
    private String login;
    private String password;
    private String role;
    private String webSite;
    private Long phoneNumber;
    private String location;
    private String workingTime;
    private String specialize;
    private String eMail;
}
