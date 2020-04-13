package com.htp.controller;

import com.htp.domain.Roles;
import com.htp.domain.hibernate.HibernateRoles;
import com.htp.repository.jdbc.RolesDao;
import com.htp.repository.springdata.HibernateRolesRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/roles")
@RequiredArgsConstructor
public class RolesController {

    private final RolesDao rolesDao;

    private final HibernateRolesRepository hibernateRolesRepository;

    /*JDBC*/

    /*FindAll*/
    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Roles>> getRoles() {
        return new ResponseEntity<>(rolesDao.findAll(), HttpStatus.OK);
    }

    /*FindByID*/
    @ApiOperation(value = "Get Roles from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Roles"),
            @ApiResponse(code = 400, message = "Invalid Roles ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Roles was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/getRolesById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Roles> getRolesById(@ApiParam("Role Path Id") @PathVariable Long id) {
        Roles roles = rolesDao.findById(id);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/delete/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteRole(@PathVariable("id") Long id) {
        rolesDao.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /*SPRING DATA*/

    /*FindAll*/
    @GetMapping("/spring-data/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateRoles>> getHibernatesRolesRepository() {
        return new ResponseEntity<>( hibernateRolesRepository.findAll(), HttpStatus.OK);
    }

    /*FindAll(pageable)*/
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported."),
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/spring-data/all(pageable)")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<HibernateRoles>> getRolesSpringData(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>( hibernateRolesRepository.findAll(pageable), HttpStatus.OK);
    }

    /*FindById*/
    @ApiOperation(value = "Get Roles from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Roles"),
            @ApiResponse(code = 400, message = "Invalid Roles ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Roles was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/spring-data/getRolesById/{id}", method = RequestMethod.GET)
    public ResponseEntity<HibernateRoles> getHibernateRolesByIdRepository(@ApiParam("Roles Path Id") @PathVariable Long id) {
        HibernateRoles t = hibernateRolesRepository.findById(id).orElse(null);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/spring-data/delete/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteHibernateRolesRepository(@PathVariable("id") Long id) {
        hibernateRolesRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
