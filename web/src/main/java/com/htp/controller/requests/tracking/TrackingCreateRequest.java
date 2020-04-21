package com.htp.controller.requests.tracking;

import com.htp.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingCreateRequest {

        private Status status;

        @PositiveOrZero
        private Long cost;

        @PositiveOrZero
        private Long idTask;

        @PositiveOrZero
        private Long idOrganaizer;
}
