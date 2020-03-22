package com.htp.controller.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class TasksCreateRequest {
    @Size(min = 2, max = 100)
    private String serviceWorkName;

    /*@NotNull
    @PositiveOrZero
    private Float salary;

    private List<Long> users = Collections.emptyList();*/

    private Boolean necessityOfEvacuation;

    private Boolean wheelBrake;

    @NotNull
    private Long idCar;

    private Date created;

    private String description;

    private Double latitude;

    private Double longitude;

    private String localDescription;
}
