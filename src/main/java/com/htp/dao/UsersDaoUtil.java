package com.htp.dao;

import com.htp.dao.jdbc.UsersDao;
import com.htp.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Component
@Transactional(rollbackFor = DataIntegrityViolationException.class)
public class UsersDaoUtil {

    @Autowired
    private UsersDao usersDao;

    public void testOperations() {
        usersDao.update(
                new Users(30l, "Mouse", "2er4h23", new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()),
                        false, "Mouse@gmail.com", 80291904111l)
                //100L, "test", "test", new Timestamp(new Date().getTime()), 1L, "qweqwe", "qweqwe"
        );

        Users second = usersDao.findById(2L);
        second.setLogin("testTransaction");
        Users third = usersDao.findById(3L);
        third.setLogin("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

        usersDao.batchUpdate(Arrays.asList(second, third));
    }
}