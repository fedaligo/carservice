package com.htp.start;

import com.htp.dao.config.AppConfig;
import com.htp.dao.users.Users;
import com.htp.dao.users.UsersDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UsersDao usersDAO = (UsersDao) context.getBean("UsersDaoImpl");

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

        //System.out.println(usersDAO.deleted());

        Timestamp ts = new Timestamp(2019-1900,11,22,21,32,31,0);
        System.out.println(usersDAO.createdAfter());

        context.close();
    }
}
