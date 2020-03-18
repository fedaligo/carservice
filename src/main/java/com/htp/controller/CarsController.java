package com.htp.controller;

import com.htp.dao.jdbc.CarsDao;
import com.htp.entity.Cars;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@Controller
public class CarsController {
    private final CarsDao carsDao;

    public CarsController(CarsDao carsDao) {
        this.carsDao = carsDao;
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
    @RequestMapping(value = "/cars/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printAllCars(ModelMap model) {
        model.addAttribute("carsreadall",
                carsDao.findAll().stream()
                        .map(Cars::toString)
                        .collect(Collectors.joining(","))
        );
        return "hello";
    }
}
