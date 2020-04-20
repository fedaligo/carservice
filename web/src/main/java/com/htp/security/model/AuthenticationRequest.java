package com.htp.security.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Object withh values for user authentification")
public class AuthenticationRequest {

    @NotEmpty
    @ApiModelProperty(required = true, allowableValues = "login", dataType = "string", notes = "user's login")
    private String username;

    @NotEmpty
    @ApiModelProperty(required = true, allowableValues = "password", dataType = "string", notes = "user's password")
    private String password;

}
