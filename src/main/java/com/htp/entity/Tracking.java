package com.htp.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Tracking {
    private Long id;
    private Long idTask;
    private Long idOrganaizer;
    private String status;
    private Date confirmDate;
    private Long cost;

}
