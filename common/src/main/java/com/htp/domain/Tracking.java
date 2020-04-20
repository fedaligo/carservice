package com.htp.domain;

import com.htp.domain.enums.Status;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tracking {
    private Long id;
    private Long idTask;
    private Long idOrganaizer;
    private Status status;
    private Date confirmDate;
    private Long cost;

}
