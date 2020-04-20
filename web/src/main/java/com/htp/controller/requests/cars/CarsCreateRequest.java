package com.htp.controller.requests.cars;

import com.htp.domain.enums.TypeOfFuel;
import com.htp.domain.enums.TypeOfTransmission;
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
public class CarsCreateRequest {

        @NotNull
        @NotEmpty
        @Size(min = 3, max = 30)
        @ApiModelProperty(example = "audi")
        private String carBrand;

        @NotNull
        @NotEmpty
        @Size(min = 1, max = 30)
        @ApiModelProperty(example = "a8")
        private String brandModel;

        private TypeOfTransmission typeOfTransmission;

        private TypeOfFuel typeOfFuel;

        //@JsonIgnore
        @NotNull
        @PositiveOrZero
        //@NotEmpty
        private Long userId;

        @NotNull
        @NotEmpty
        @ApiModelProperty(example = "1HGBH41JXMN109186")
        @Size(min = 5, max = 17)
        private String vinNumber;

        @NotNull
        @PositiveOrZero
        private Long carWeight;
}
