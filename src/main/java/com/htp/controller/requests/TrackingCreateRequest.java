package com.htp.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
        @NotEmpty
        //@Size(min = 6, max = 20)
        private Long cost;
}
