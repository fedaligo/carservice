package com.htp.dao.controller;

import com.htp.dao.tracking.TrackingDao;
import com.htp.dao.users.UsersDao;
import com.htp.entity.Tracking;
import com.htp.entity.Users;
import org.apache.commons.lang3.StringUtils;
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
public class TrackingController {

    private final TrackingDao trackingDao;

    public TrackingController(TrackingDao trackingDao) {
        this.trackingDao = trackingDao;
    }

    //http://localhost:8081/tracking/bycost?query=100
    @RequestMapping(value = "/tracking/bycost", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello(@RequestParam("query") Long query, ModelMap model) {
        List<Tracking> search = trackingDao.trackingByHigherCost(query);
        model.addAttribute("bycost",
                StringUtils.join(search.stream().map(Tracking::toString).collect(Collectors.toList()), ","));
        return "hello";
    }

    /*GET localhost:8081/tracking/readall*/
    @RequestMapping(value = "/tracking/readall", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello2(ModelMap model) {
        model.addAttribute("trackingreadall",
                trackingDao.readAll().stream()
                        .map(Tracking::toString)
                        .collect(Collectors.joining(","))
        );
        return "hello";
    }
}
