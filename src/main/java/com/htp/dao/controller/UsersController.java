package com.htp.dao.controller;

import com.htp.entity.Users;
import com.htp.dao.users.UsersDao;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@Controller
public class UsersController {

    private final UsersDao userDao;

    public UsersController(UsersDao userDao) {
        this.userDao = userDao;
    }

    /*GET localhost:8080/HelloWeb/users/create*/
    @RequestMapping(value = "/users/deleted", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello1(ModelMap model) {
        model.addAttribute("usersdeleted",
                userDao.deleted().stream()
                        .map(Users::toString)
                        .collect(Collectors.joining("---"))
        );
        return "hello";
    }

    /*GET localhost:8080/HelloWeb/users/readall*/
    @RequestMapping(value = "/users/readall", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello2(ModelMap model) {
        model.addAttribute("usersreadall",
                userDao.readAll().stream()
                        .map(Users::toString)
                        .collect(Collectors.joining(","))
        );
        return "hello";
    }


}