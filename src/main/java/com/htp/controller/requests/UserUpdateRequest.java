package com.htp.controller.requests;

import com.htp.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
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

    @NotNull
    @NotEmpty
    @Size(min = 11, max = 11)
    private Long phNumberUser;

    private Gender gender;
}
