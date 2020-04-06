package com.htp.controller.requests.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarsUpdateRequest extends CarsCreateRequest{
    private Long id;
}
