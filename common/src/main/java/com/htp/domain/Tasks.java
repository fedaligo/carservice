package com.htp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tasks {
    private Long id;
    private String serviceWorkName;
    private Boolean necessityOfEvacuation;
    private Boolean wheelBrake;
    private Long idCar;
    private Date created;
    private String description;
    private Double latitude;
    private Double longitude;
    private String localDescription;

}
