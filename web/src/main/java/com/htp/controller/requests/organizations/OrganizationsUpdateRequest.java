package com.htp.controller.requests.organizations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationsUpdateRequest extends OrganizationsCreateRequest{
    private Long id;
}
