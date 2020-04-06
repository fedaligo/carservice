package com.htp.controller.requests.roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesUpdateRequest extends RolesCreateRequest{
    private Long id;
}
