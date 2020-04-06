package com.htp.controller;

import com.htp.domain.Roles;
import com.htp.domain.hibernate.HibernateRoles;
import com.htp.repository.hibernate.impl.HibernateRolesDaoImpl;
import com.htp.repository.jdbc.RolesDao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
