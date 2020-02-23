package com.htp.start;

import com.htp.aspect.StatisticsAspect;
import com.htp.config.AppConfig;
import com.htp.dao.*;
import com.htp.entity.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Main {
    @Autowired
    private StatisticsAspect statisticsAspect;
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UsersDao usersDAO = (UsersDao) context.getBean("UsersDaoImpl");
        TrackingDao trackingDao = (TrackingDao) context.getBean("TrackingDaoImpl");
        CarsDao carsDao = (CarsDao) context.getBean("CarsDaoImpl");
        OrganizationsDao organizationsDao = (OrganizationsDao) context.getBean("OrganizationsDaoImpl");
        RolesDao rolesDao = (RolesDao) context.getBean("RolesDaoImpl");
        TasksDao tasksDao = (TasksDao) context.getBean("TasksDaoImpl");

        /*CREATE
        Timestamp ts = new Timestamp(2019-1900,11,31,12,0,0,0);
        Users user = new Users(30l, "Mouse", "2er4h23", ts, ts,
                false, "Mouse@gmail.com", 80291904572l);
        usersDAO.create(user);*/

        /*READ ALL
        for(int i=0; i<usersDAO.readAll().size();i++){
            System.out.print(" FindAll : " + usersDAO.readAll().get(i));
        }*/

        /*READ BY ID
         System.out.println(usersDAO.readById(29l));*/

        /* UPDATE BY ID
        Timestamp ts = new Timestamp(2019-1900,11,31,12,0,0,0);
        Users user = new Users(30l, "Mouse", "2er4h23", ts, ts,
                false, "Mouse@gmail.com", 80291904111l);
        usersDAO.updateById(user);
        System.out.println(usersDAO.readById(30l));*/

        /*DELETE BY ID
        usersDAO.deleteById(30l);*/

        /*DELETED USERS
        System.out.println(usersDAO.deleted());*/

        /*CREATED AFTER DATE - not working
        Timestamp ts = new Timestamp(2019-1900,11,22,21,32,31,0);
        System.out.println(usersDAO.createdAfter());*/
        /*System.out.print(" FindAll : " + usersDAO.readAll());
        System.out.println(usersDAO.readById(29l));;

        Main mn = (Main)context.getBean("main") ;
        mn.outputLoggingCounter();*/
        //System.out.print(" FindAll : " + trackingDao.trackingByHigherCost(100l));
        System.out.println(organizationsDao.findAll());

        context.close();
    }


    private void outputLoggingCounter() {
        if (statisticsAspect != null) {
            System.out.print("Loggers statistics. Number of calls USERS READALL: ");
            for (Map.Entry<Class<?>, Integer> entry: statisticsAspect.getCounterReadAll().entrySet()) {
                //System.out.println("    " + entry.getKey().getSimpleName() + ": " + entry.getValue());
                System.out.println("    " + entry.getValue());
            }
        }
    }
}
