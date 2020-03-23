package com.htp.controller;

import com.htp.controller.requests.TasksCreateRequest;
import com.htp.dao.hibernate.impl.HibernateRolesDaoImpl;
import com.htp.dao.hibernate.impl.HibernateTasksDaoImpl;
import com.htp.dao.jdbc.RolesDao;
import com.htp.dao.jdbc.TasksDao;
import com.htp.entity.Roles;
import com.htp.entity.Tasks;
import com.htp.entity.hibernate.HibernateRoles;
import com.htp.entity.hibernate.HibernateTasks;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/roles")
@RequiredArgsConstructor
public class RolesController {

        private final RolesDao rolesDao;

        private final HibernateRolesDaoImpl hibernateRolesDao;

        @GetMapping("/all")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<Roles>> getRoles() {
            return new ResponseEntity<>(rolesDao.findAll(), HttpStatus.OK);
        }

        @GetMapping("/hibernate/all")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<HibernateRoles>> getHibernateRoles() {
            return new ResponseEntity<>(hibernateRolesDao.findAll(), HttpStatus.OK);
        }

        @ApiOperation(value = "Get Roles from server by id")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful getting Roles"),
                @ApiResponse(code = 400, message = "Invalid Roles ID supplied"),
                @ApiResponse(code = 401, message = "Lol kek"),
                @ApiResponse(code = 404, message = "Roles was not found"),
                @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @RequestMapping(value = "/getRolesById/{id}", method = RequestMethod.GET)
        public ResponseEntity<Roles> getRolesById(@ApiParam("Role Path Id") @PathVariable Long id) {
            Roles roles = rolesDao.findById(id);
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }

        @ApiOperation(value = "Get Roles from server by id")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful getting Roles"),
                @ApiResponse(code = 400, message = "Invalid Roles ID supplied"),
                @ApiResponse(code = 401, message = "Lol kek"),
                @ApiResponse(code = 404, message = "Roles was not found"),
                @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @RequestMapping(value = "/hibernate/getRolesById/{id}", method = RequestMethod.GET)
        public ResponseEntity<HibernateRoles> getHibernateRolesById(@ApiParam("Role Path Id") @PathVariable Long id) {
            HibernateRoles roles = hibernateRolesDao.findById(id);
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<Long> deleteRole(@PathVariable("id") Long id) {
            rolesDao.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        @DeleteMapping("/hibernate/delete/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<Long> deleteHibernateRole(@PathVariable("id") Long id) {
            hibernateRolesDao.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }




    /*private final RolesDao rolesDao;

    public RolesController(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }

    //http://localhost:8081/tracking/search?cost=100
   *//* @RequestMapping(value = "/tracking/search", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello(@RequestParam("cost") Long query, ModelMap model) {
        List<Tracking> search = trackingDao.trackingByHigherCost(query);
        model.addAttribute("bycost",
                StringUtils.join(search.stream().map(Tracking::toString).collect(Collectors.toList()), ","));
        return "hello";
    }*//*

    *//*GET localhost:8081/tracking/all*//*
    @RequestMapping(value = "/roles/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printAllRoles(ModelMap model) {
        model.addAttribute("rolesreadall",
                rolesDao.findAll().stream()
                        .map(Roles::toString)
                        .collect(Collectors.joining(","))
        );
        return "hello";
    }*/
}
