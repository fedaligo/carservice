package com.htp.entity;

import lombok.*;

import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
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
