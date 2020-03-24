package com.htp.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingCreateRequest {

        @NotNull
        @NotEmpty
        @Size(min = 1, max = 40)
        private String status;

        @NotNull
        @PositiveOrZero
        //@Size(min = 6, max = 20)
        private Long cost;
}
