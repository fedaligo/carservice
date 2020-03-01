package com.htp.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Users {
    private Long id;
    private String login;
    private String password;
    private Timestamp created;
    private Timestamp changed;
    private Boolean isDeleted;
    private String eMail;
    private Long phNumberUser;


}
