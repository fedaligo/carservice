package com.htp.controller;

import com.htp.dao.jdbc.OrganizationsDao;
import com.htp.entity.Organization;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    public String printAllOrganizations(ModelMap model) {
        model.addAttribute("organizationsreadall",
               organizationsDao.findAll().stream()
                        .map(Organization::toString)
                        .collect(Collectors.joining(","))
        );
        return "hello";
    }
}
