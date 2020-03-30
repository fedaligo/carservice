package com.htp.controller;

import com.htp.controller.requests.OrganizationsCreateRequest;
import com.htp.repository.hibernate.impl.HibernateOrganizationsDaoImpl;
import com.htp.repository.jdbc.OrganizationsDao;
import com.htp.domain.Organizations;
import com.htp.domain.hibernate.HibernateOrganizations;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/organizations")
@RequiredArgsConstructor
public class OrganizationsController {

        private final OrganizationsDao organizationsDao;

        private final HibernateOrganizationsDaoImpl hibernateOrganizationsDao;

        @GetMapping("/all")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<Organizations>> getOrganizations() {
            return new ResponseEntity<>(organizationsDao.findAll(), HttpStatus.OK);
        }

        @GetMapping("/hibernate/all")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<HibernateOrganizations>> getHibernateOrganizations() {
            return new ResponseEntity<>(hibernateOrganizationsDao.findAll(), HttpStatus.OK);
        }

        @ApiOperation(value = "Get Organizations from server by id")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful getting Organizations"),
                @ApiResponse(code = 400, message = "Invalid Organizations ID supplied"),
                @ApiResponse(code = 401, message = "Lol kek"),
                @ApiResponse(code = 404, message = "Organizations was not found"),
                @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @RequestMapping(value = "/getOrganizationsById/{id}", method = RequestMethod.GET)
        public ResponseEntity<Organizations> getOrganizationById(@ApiParam("Organization Path Id") @PathVariable Long id) {
            Organizations organizations = organizationsDao.findById(id);
            return new ResponseEntity<>(organizations, HttpStatus.OK);
        }

        @ApiOperation(value = "Get Organizations from server by id")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful getting Organizations"),
                @ApiResponse(code = 400, message = "Invalid Organizations ID supplied"),
                @ApiResponse(code = 401, message = "Lol kek"),
                @ApiResponse(code = 404, message = "Organizations was not found"),
                @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @RequestMapping(value = "/hibernate/getOrganizationById/{id}", method = RequestMethod.GET)
        public ResponseEntity<HibernateOrganizations> getHibernateOrganizationById(@ApiParam("Organization Path Id") @PathVariable Long id) {
            HibernateOrganizations organizations = hibernateOrganizationsDao.findById(id);
            return new ResponseEntity<>(organizations, HttpStatus.OK);
        }


        @PostMapping("/create")
        @Transactional
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<Organizations> createOrganizations(@RequestBody OrganizationsCreateRequest request) {
            Organizations t = new Organizations();
            //userID is empty - will be generated by DB
            t.setName(request.getName());
            t.setWebSite(request.getWebSite());
            t.setPhoneNumber(request.getPhoneNumber());
            t.setLocation(request.getLocation());
            t.setWorkingTime(request.getWorkingTime());
            t.setSpecialize(request.getSpecialize());
            t.setEMail(request.getE_Mail());

            return new ResponseEntity<>(organizationsDao.save(t), HttpStatus.OK);
        }

        @PostMapping("/hibernate/create")
        @Transactional
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<HibernateOrganizations> createHibernateOrganizations(@RequestBody OrganizationsCreateRequest request) {
            HibernateOrganizations t = new HibernateOrganizations();
            t.setName(request.getName());
            t.setWeb_site(request.getWebSite());
            t.setPhone_number(request.getPhoneNumber());
            t.setLocation(request.getLocation());
            t.setWorking_time(request.getWorkingTime());
            t.setSpecialize(request.getSpecialize());
            t.setE_mail(request.getE_Mail());

            return new ResponseEntity<>(hibernateOrganizationsDao.save(t), HttpStatus.OK);
        }

        @ApiOperation(value = "Update Organization by userID")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful Organization update 1111111"),
                @ApiResponse(code = 400, message = "Invalid Organization ID supplied 111111"),
                @ApiResponse(code = 404, message = "Organization was not found 111111"),
                @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
        })
        /*@ApiImplicitParams({
                @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
        })*/
        @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<Organizations> updateOrganization(@PathVariable("id") Long id,
                                                @RequestBody OrganizationsCreateRequest request) {
            Organizations t = organizationsDao.findById(id);
            t.setName(request.getName());
            t.setWebSite(request.getWebSite());
            t.setPhoneNumber(request.getPhoneNumber());
            t.setLocation(request.getLocation());
            t.setWorkingTime(request.getWorkingTime());
            t.setSpecialize(request.getSpecialize());
            t.setEMail(request.getE_Mail());

            return new ResponseEntity<>(organizationsDao.updateOne(t), HttpStatus.OK);
        }

        @ApiOperation(value = "Update Organization by userID")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful Organization update 1111111"),
                @ApiResponse(code = 400, message = "Invalid Organization ID supplied 111111"),
                @ApiResponse(code = 404, message = "Organization was not found 111111"),
                @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
        })
        @RequestMapping(value = "/hibernate/update/{id}", method = RequestMethod.PUT)
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<HibernateOrganizations> updateHibernateOrganizations(@ApiParam(value = "Organization ID", required = false) @PathVariable("id") Long id,
                                                                  @RequestBody OrganizationsCreateRequest request) {

            HibernateOrganizations t = hibernateOrganizationsDao.findById(id);
            t.setName(request.getName());
            t.setWeb_site(request.getWebSite());
            t.setPhone_number(request.getPhoneNumber());
            t.setLocation(request.getLocation());
            t.setWorking_time(request.getWorkingTime());
            t.setSpecialize(request.getSpecialize());
            t.setE_mail(request.getE_Mail());

            return new ResponseEntity<>(hibernateOrganizationsDao.updateOne(t), HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<Long> deleteOrganization(@PathVariable("id") Long id) {
            organizationsDao.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        /*@DeleteMapping("/hibernate/delete/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<Long> deleteHibernateOrganization(@PathVariable("id") Long id) {
            hibernateOrganizationsDao.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }*/





    /*private final OrganizationsDao organizationsDao;

    public OrganizationsController(OrganizationsDao organizationsDao) {
        this.organizationsDao = organizationsDao;
    }

    //http://localhost:8081/tracking/search?cost=100
    *//*@RequestMapping(value = "/tracking/search", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello(@RequestParam("cost") Long query, ModelMap model) {
        List<Tracking> search = trackingDao.trackingByHigherCost(query);
        model.addAttribute("bycost",
                StringUtils.join(search.stream().map(Tracking::toString).collect(Collectors.toList()), ","));
        return "hello";
    }*//*

    *//*GET localhost:8081/tracking/all*//*
    @RequestMapping(value = "/organizations/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printAllOrganizations(ModelMap model) {
        model.addAttribute("organizationsreadall",
               organizationsDao.findAll().stream()
                        .map(Organizations::toString)
                        .collect(Collectors.joining(","))
        );
        return "hello";
    }*/
}
