package com.htp.entity.hibernate;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode/*(exclude = {
        "userId", "roles", "professions"
})*/
@ToString/*(exclude = {
        "roles", "professions"
})*/
@Entity
@Table(name = "tracking_system")
public class HibernateTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column
    private String status;

    @Column
    private Date confirm_date;

    @Column
    private Long cost;
}
