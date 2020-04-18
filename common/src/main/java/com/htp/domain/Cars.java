package com.htp.domain;

import com.htp.domain.enums.TypeOfFuel;
import com.htp.domain.enums.TypeOfTransmission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cars {
    private Long id;
    private String carBrand;
    private String brandModel;
    private TypeOfTransmission typeOfTransmission;
    private TypeOfFuel typeOfFuel;
    private String vinNumber;
    private Long userId;
    private Long carWeight;

}
