package com.htp.controller;

import com.htp.dao.OrganizationsDao;
import com.htp.dao.TrackingDao;
import com.htp.entity.Organization;
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
public class OrganizationsController {
    private final OrganizationsDao organizationsDao;

    public OrganizationsController(OrganizationsDao organizationsDao) {
        this.organizationsDao = organizationsDao;
    }

    //http://localhost:8081/tracking/search?cost=100
    /*@RequestMapping(value = "/tracking/search", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello(@RequestParam("cost") Long query, ModelMap model) {
        List<Tracking> search = trackingDao.trackingByHigherCost(query);
        model.addAttribute("bycost",
                StringUtils.join(search.stream().map(Tracking::toString).collect(Collectors.toList()), ","));
        return "hello";
    }*/

    /*GET localhost:8081/tracking/all*/
    @RequestMapping(value = "/organizations/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello2(ModelMap model) {
        model.addAttribute("organizationsreadall",
               organizationsDao.findAll().stream()
                        .map(Organization::toString)
                        .collect(Collectors.joining(","))
        );
        return "hello";
    }
}
