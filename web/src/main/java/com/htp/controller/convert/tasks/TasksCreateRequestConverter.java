package com.htp.controller.convert.tasks;

import com.htp.controller.requests.tasks.TasksCreateRequest;
import com.htp.domain.hibernate.HibernateTasks;
import com.htp.repository.hibernate.HibernateCarsDao;
import org.springframework.stereotype.Component;

@Component
public class TasksCreateRequestConverter extends TasksRequestConverter<TasksCreateRequest, HibernateTasks> {
    public TasksCreateRequestConverter(HibernateCarsDao hibernateCarsDao) {
        super(hibernateCarsDao);
    }

    @Override
    public HibernateTasks convert(TasksCreateRequest request) {
        HibernateTasks t = new HibernateTasks();
        return doConvert(t, request);
    }
}
