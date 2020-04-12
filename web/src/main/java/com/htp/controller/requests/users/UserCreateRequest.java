package com.htp.controller.requests.users;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20)
    private String login;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    private String password;

    @NotNull
    @NotEmpty
    @Email
    @Size(min = 1, max = 100)
    private String eMail;

    /*@NotNull
    @NotEmpty*/
    private Long phNumberUser;
}