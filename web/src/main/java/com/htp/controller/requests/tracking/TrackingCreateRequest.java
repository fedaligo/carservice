package com.htp.controller.requests.tracking;

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
public class TrackingCreateRequest {

        @NotNull
        @NotEmpty
        @Size(min = 1, max = 40)
        private String status;

        @NotNull
        @PositiveOrZero
        //@Size(min = 6, max = 20)
        private Long cost;

        @NotNull
        @NotEmpty
        //@Size(min = 2, max = 100)
        private Long idTask;

        @NotNull
        @NotEmpty
        //@Size(min = 2, max = 100)
        private Long idOrganaizer;
}
