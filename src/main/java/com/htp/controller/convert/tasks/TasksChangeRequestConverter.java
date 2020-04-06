package com.htp.controller.convert.tasks;

import com.htp.controller.requests.tasks.TasksUpdateRequest;
import com.htp.domain.hibernate.HibernateTasks;
import com.htp.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import static java.util.Optional.ofNullable;

@Component
public class TasksChangeRequestConverter extends TasksRequestConverter<TasksUpdateRequest, HibernateTasks> {
    @Override
    public HibernateTasks convert(TasksUpdateRequest request) {
        HibernateTasks t =
                ofNullable(entityManager.find(HibernateTasks.class, request.getId()))
                        .orElseThrow(() -> new EntityNotFoundException(HibernateTasks.class, request.getId()));
        return doConvert(t, request);
    }
}
