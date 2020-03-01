package com.htp.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Cars {
    private Long id;
    private String carBrand;
    private String brandModel;
    private String typeOfTransmission;
    private String typeOfFuel;
    private String vinNumber;
    private Long userId;
    private Long carWeight;

}
