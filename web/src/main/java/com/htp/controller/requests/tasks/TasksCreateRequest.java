package com.htp.controller.requests.tasks;

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
public class TasksCreateRequest {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    @ApiModelProperty(example = "diagnostic")
    private String serviceWorkName;

    private Boolean necessityOfEvacuation;

    private Boolean wheelBrake;

    @NotNull
    @PositiveOrZero
    private Long idCar;

    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "traffic accident")
    @Size(min = 2, max = 100)
    private String description;

    @NotNull
    @PositiveOrZero
    private Double latitude;

    @NotNull
    @PositiveOrZero
    private Double longitude;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    @ApiModelProperty(example = "malinovka")
    private String localDescription;
}
