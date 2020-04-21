package com.htp.controller.requests.tracking;


import lombok.Setter;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TrackingUpdateRequest extends TrackingCreateRequest {
    private Long id;
}
