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
public class Roles {
    private Long id;
    private String nameOfRole;
    private Long userId;

    public Roles(Long userId, String nameOfRole) {
        this.userId = userId;
        this.nameOfRole = nameOfRole;

    }
}
