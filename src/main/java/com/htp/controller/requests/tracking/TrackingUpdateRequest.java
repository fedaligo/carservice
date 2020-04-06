package com.htp.controller.requests.tracking;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingUpdateRequest extends TrackingCreateRequest {
    private Long id;
}
