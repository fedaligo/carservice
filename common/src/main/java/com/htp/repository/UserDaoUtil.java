package com.htp.repository;

import com.htp.domain.Users;
import com.htp.repository.jdbc.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

@Component
@Transactional(rollbackFor = DataIntegrityViolationException.class)
public class UserDaoUtil {

    @Autowired
    @Qualifier("usersDaoImpl")
    private UsersDao userDao;

    public void testOperations() {
        /*userDao.save(
                new Users(100L, "test", "test",
                        new Timestamp(new Date().getTime()), 1L, "qweqwe", "qweqwe")
        );

        Users second = userDao.findById(2L);
        second.setUserSurname("testTransaction");
        User third = userDao.findById(3L);
        third.setUserSurname("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890");

        userDao.batchUpdate(Arrays.asList(second, third));*/
    }
}
