package com.htp.domain;

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
    private String status;
    private Date confirmDate;
    private Long cost;

}
