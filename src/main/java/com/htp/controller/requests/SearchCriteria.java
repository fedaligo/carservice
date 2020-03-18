package com.htp.controller.requests;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class SearchCriteria {
    private String query;
    private Integer limit;
    private Integer offset;


}
