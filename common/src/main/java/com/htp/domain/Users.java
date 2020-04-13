package com.htp.domain;



import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    private Long id;
    private String login;
    private String password;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;
    private String eMail;
    private Long phNumberUser;
    private Gender gender= Gender.NOT_SELECTED;


}
