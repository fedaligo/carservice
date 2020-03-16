package com.htp.controller;

import com.htp.dao.RolesDao;
import com.htp.dao.TrackingDao;
import com.htp.entity.Roles;
import com.htp.entity.Tracking;
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
public class RolesController {
    private final RolesDao rolesDao;

    public RolesController(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }

    //http://localhost:8081/tracking/search?cost=100
   /* @RequestMapping(value = "/tracking/search", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello(@RequestParam("cost") Long query, ModelMap model) {
        List<Tracking> search = trackingDao.trackingByHigherCost(query);
        model.addAttribute("bycost",
                StringUtils.join(search.stream().map(Tracking::toString).collect(Collectors.toList()), ","));
        return "hello";
    }*/

    /*GET localhost:8081/tracking/all*/
    @RequestMapping(value = "/roles/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printAllRoles(ModelMap model) {
        model.addAttribute("rolesreadall",
                rolesDao.findAll().stream()
                        .map(Roles::toString)
                        .collect(Collectors.joining(","))
        );
        return "hello";
    }
}
