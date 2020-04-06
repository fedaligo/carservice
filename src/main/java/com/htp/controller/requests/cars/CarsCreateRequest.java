package com.htp.controller.requests.cars;

import com.htp.domain.hibernate.HibernateUsers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarsCreateRequest {

        @NotNull
        @NotEmpty
        @Size(min = 3, max = 30)
        private String carBrand;

        @NotNull
        @NotEmpty
        @Size(min = 1, max = 30)
        private String brandModel;

        @NotNull
        @NotEmpty
        @Size(min = 2, max = 2)
        private String typeOfTransmission;

        @NotNull
        @NotEmpty
        @Size(min = 3, max = 10)
        private String typeOfFuel;

        @NotNull
        @NotEmpty
        //@Size(min = 3, max = 10)
        private Long user_id;

        @NotNull
        @NotEmpty
        @Size(min = 5, max = 17)
        private String vinNumber;

        @NotNull
        @NotEmpty
        //@Size(min = 11, max = 11)
        private Long carWeight;
}
