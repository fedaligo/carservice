package com.htp.controller.requests.roles;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 30)
    @ApiModelProperty(example = "ROLE_USER")
    private String nameOfRole;

    @NotNull
    @PositiveOrZero
    @NotEmpty
    private Long userId;
}
