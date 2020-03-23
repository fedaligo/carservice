package com.htp.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationsCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 30)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 30)
    private String webSite;

    @NotNull
    @NotEmpty
    //@Size(min = 11, max = 11)
    private Long phoneNumber;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private String location;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 30)
    private String workingTime;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private String specialize;

    @NotNull
    @NotEmpty
    @Email
    @Size(min = 1, max = 100)
    private String e_Mail;
}
