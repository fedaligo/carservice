package com.htp.controller.convert.tasks;

import com.htp.controller.convert.EntityConverter;
import com.htp.controller.requests.tasks.TasksCreateRequest;
import com.htp.domain.hibernate.HibernateTasks;

import java.sql.Timestamp;
import java.util.Date;


public abstract class TasksRequestConverter<S, T> extends EntityConverter<S, T> {
    protected HibernateTasks doConvert(HibernateTasks t, TasksCreateRequest request) {

        //HibernateCarsDao hibernateCarsDao;

        t.setServiceWorkName(request.getServiceWorkName());
        t.setNecessityOfEvacuation(request.getNecessityOfEvacuation());
        t.setWheelBrake(request.getWheelBrake());
        //t.setCars(hibernateCarsDao.findById(request.getId_car()));
        t.setCreated(new Timestamp(new Date().getTime()));
        t.setDescription(request.getDescription());
        t.setLatitude(request.getLatitude());
        t.setLongitude(request.getLongitude());
        t.setLocalDescription(request.getLocalDescription());

        return t;
    }
}
