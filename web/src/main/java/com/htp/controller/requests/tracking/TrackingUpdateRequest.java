package com.htp.controller.requests.tracking;


import lombok.*;

//@Data
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TrackingUpdateRequest extends TrackingCreateRequest {
    private Long id;
}
