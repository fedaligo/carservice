package com.htp.controller.requests.users;

import com.htp.domain.enums.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20)
    @ApiModelProperty(example = "login")
    private String login;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 100)
    @ApiModelProperty(example = "password")
    private String password;

    @NotNull
    @Email
    @ApiModelProperty(example = "example@mail.ru")
    @Size(min = 1, max = 100)
    private String eMail;

    @NotNull
    @PositiveOrZero
    @ApiModelProperty(example = "80291111111")
    private Long phNumberUser;

    private Gender gender;
}