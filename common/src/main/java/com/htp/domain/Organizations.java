package com.htp.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Organizations {
    private Long id;
    private String name;
    private String webSite;
    private Long phoneNumber;
    private String location;
    private String workingTime;
    private String specialize;
    private String eMail;
}
