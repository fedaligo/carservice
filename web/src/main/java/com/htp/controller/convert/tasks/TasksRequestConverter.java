package com.htp.controller.convert.tasks;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.tasks.TasksCreateRequest;
import com.htp.domain.hibernate.HibernateCars;
import com.htp.domain.hibernate.HibernateTasks;
import com.htp.repository.hibernate.HibernateCarsDao;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@RequiredArgsConstructor
public abstract class TasksRequestConverter<S, T> extends EntityConverter<S, T> {

    private final HibernateCarsDao hibernateCarsDao;

    protected HibernateTasks doConvert(HibernateTasks t, TasksCreateRequest request) {

        HibernateCars hibernateCars = hibernateCarsDao.findById(request.getIdCar());

        t.setServiceWorkName(request.getServiceWorkName());
        t.setNecessityOfEvacuation(request.getNecessityOfEvacuation());
        t.setWheelBrake(request.getWheelBrake());
        t.setCars(hibernateCars);
        t.setCreated(new Timestamp(new Date().getTime()));
        t.setDescription(request.getDescription());
        t.setLatitude(request.getLatitude());
        t.setLongitude(request.getLongitude());
        t.setLocalDescription(request.getLocalDescription());

        return t;
    }
}
