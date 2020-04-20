package com.htp.controller.requests.organizations;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationsCreateRequest {

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 30)
    @ApiModelProperty(example = "login")
    private String login;

    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "password")
    @Size(min = 1, max = 30)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 30)
    @ApiModelProperty(example = "ROLE_ORG")
    private String role;

    @NotNull
    @Size(min = 1, max = 30)
    @ApiModelProperty(example = "mysto.by")
    private String webSite;

    @NotNull
    @NotEmpty
    @PositiveOrZero
    @ApiModelProperty(example = "80291111111")
    private Long phoneNumber;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    @ApiModelProperty(example = "Minsk st.Shirokaya 36")
    private String location;

    @NotNull
    @NotEmpty
    @Size(min = 1, max = 30)
    @ApiModelProperty(example = "09.00-21.00")
    private String workingTime;

    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "any cars")
    @Size(min = 1, max = 50)
    private String specialize;

    @NotNull
    @Email
    @Size(min = 1, max = 100)
    private String eMail;
}
