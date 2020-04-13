package com.htp.controller.requests.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksCreateRequest {
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String serviceWorkName;

    /*@NotNull
    @NotEmpty*/
    private Boolean necessityOfEvacuation;

    /*@NotNull
    @NotEmpty*/
    private Boolean wheelBrake;

    /*@NotNull
    @NotEmpty*/
    //@Size(min = 2, max = 100)
    private Long idCar;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String description;

    /*@NotNull
    @NotEmpty
    @Size(min = 2, max = 10)*/
    private Double latitude;

    /*@NotNull
    @NotEmpty
    @Size(min = 2, max = 10)*/
    private Double longitude;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 50)
    private String localDescription;

    //idCar
}
