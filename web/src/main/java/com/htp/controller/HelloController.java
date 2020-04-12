package com.htp.controller;

import com.htp.domain.Users;
import com.htp.repository.jdbc.UsersDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/api")
public class HelloController {

    @Autowired
    @Qualifier("usersDaoImpl")
    private UsersDao userDao;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello(ModelMap model) {

        model.addAttribute("message", "Hello Spring MVC Framework!");
        return "hello";
    }

    @RequestMapping(value = "/user/search", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello(@RequestParam("query") String query, ModelMap model) {
        List<Users> search = userDao.search(query, 10L, 0L);
        model.addAttribute("userName",
                StringUtils.join(search.stream().map(Users::getLogin).collect(Collectors.toList()), ","));
        return "hello";
    }
}

