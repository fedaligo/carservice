package com.htp.controller;

import com.htp.domain.hibernate.HibernateRoles;
import com.htp.repository.springdata.HibernateRolesRepository;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin/rest/roles")
@RequiredArgsConstructor
public class RolesController {

    private final HibernateRolesRepository hibernateRolesRepository;

    /*FindAll*/
    @GetMapping("/spring-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateRoles>> getHibernatesRolesRepository() {
        return new ResponseEntity<>( hibernateRolesRepository.findAll(), HttpStatus.OK);
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
    @GetMapping("/spring-data/{id}")
    public ResponseEntity<HibernateRoles> getHibernateRolesByIdRepository(@ApiParam("Roles Path Id") @PathVariable Long id) {
        HibernateRoles t = hibernateRolesRepository.findById(id).orElse(null);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/spring-data/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Long> deleteHibernateRolesRepository(@PathVariable("id") Long id) {
        hibernateRolesRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
