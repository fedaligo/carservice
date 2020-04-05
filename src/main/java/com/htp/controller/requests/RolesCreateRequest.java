package com.htp.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 30)
    private String name_of_role;

    @NotNull
    @NotEmpty
    private Long user_id;
}
