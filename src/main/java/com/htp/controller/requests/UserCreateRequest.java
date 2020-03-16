package com.htp.controller.requests;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class UserCreateRequest {

    @Size(min = 1, max = 100)
    private String userName;

    @Size(min = 1, max = 100)
    private String userSurname;

    private Timestamp birthDate;

    @Email
    @Size(min = 1, max = 100)
    private String login;

    private String password;
}