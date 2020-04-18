package com.htp.domain;


import com.htp.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
