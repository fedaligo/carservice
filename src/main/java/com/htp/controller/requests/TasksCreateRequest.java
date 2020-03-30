package com.htp.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksCreateRequest {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String serviceWorkName;

    @NotNull
    @NotEmpty
    private Boolean necessityOfEvacuation;

    @NotNull
    @NotEmpty
    private Boolean wheelBrake;

    @NotNull
    @NotEmpty
    //@Size(min = 2, max = 100)
    private Long id_car;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String description;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 10)
    private Double latitude;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 10)
    private Double longitude;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String localDescription;

    //idCar
}
