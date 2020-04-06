package com.htp.controller;

import com.htp.controller.requests.organizations.OrganizationsCreateRequest;
import com.htp.domain.Organizations;
import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.repository.hibernate.impl.HibernateOrganizationsDaoImpl;
import com.htp.repository.jdbc.OrganizationsDao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    /*SPRING DATA*//*

    *//*FindAll*//*
    @GetMapping("/spring-data/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateUsers>> getHibernatesUsersRepository() {
        return new ResponseEntity<>( hibernateUsersRepository.findAll(), HttpStatus.OK);
    }

    *//*FindAll(pageable)*//*
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping("/spring-data/all(pageable)")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<HibernateUsers>> getUsersSpringData(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>( hibernateUsersRepository.findAll(pageable), HttpStatus.OK);
    }

    *//*FindById*//*
    @ApiOperation(value = "Get user from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting user"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/spring-data/getUserById/{id}", method = RequestMethod.GET)
    public ResponseEntity<HibernateUsers> getHibernateUserByIdRepository(@ApiParam("User Path Id") @PathVariable Long id) {
        HibernateUsers user = hibernateUsersRepository.findById(id).orElse(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    *//*Create *//*
    @PostMapping("/spring-data/create(converted)")
    @Transactional
    public ResponseEntity<HibernateUsers> createConvertedHibernateUser(@RequestBody @Valid UserCreateRequest request) {
        HibernateUsers savedConvertedUser = hibernateUsersRepository.saveAndFlush(conversionService.convert(request, HibernateUsers.class));
        hibernateRolesRepository.saveAndFlush(new HibernateRoles("ROLE_USER",savedConvertedUser));
        return new ResponseEntity<>( hibernateUsersRepository.saveAndFlush(savedConvertedUser), CREATED);
    }

    *//*Update*//*
    @ApiOperation(value = "Update user by userID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update 1111111"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied 111111"),
            @ApiResponse(code = 404, message = "User was not found 111111"),
            @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
    })
    @PutMapping("/spring-data/update(converted)/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateUsers> updateHibernateUserRepository(@RequestBody @Valid UserUpdateRequest request) {
        return new ResponseEntity<>( hibernateUsersRepository.save(conversionService.convert(request, HibernateUsers.class)), HttpStatus.OK);
    }

    *//*Delete*//*
    @DeleteMapping("/spring-data/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteHibernateUserRepository(@PathVariable("id") Long userId) {
        hibernateUsersRepository.deleteById(userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }*/
}
