package com.htp.controller.requests.tasks;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TasksUpdateRequest extends TasksCreateRequest{
    private Long id;
}
