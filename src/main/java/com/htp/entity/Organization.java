package com.htp.entity;

import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Organization {
    private Long id;
    private String name;
    private String webSite;
    private Long phoneNumber;
    private String location;
    private String workingTime;
    private String specialize;
    private String eMail;
}
