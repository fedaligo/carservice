package com.htp.controller;

import com.htp.controller.requests.organizations.OrganizationsCreateRequest;
import com.htp.controller.requests.organizations.OrganizationsUpdateRequest;
import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.repository.springdata.HibernateOrganizationsRepository;

import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin
@RequestMapping(value = "/admin/rest/organizations")
@RequiredArgsConstructor
public class OrganizationsController {

    private final ConversionService conversionService;

    private final HibernateOrganizationsRepository hibernateOrganizationsRepository;

    /*FindAll*/
    @GetMapping("/spring-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateOrganizations>> getHibernatesOrganizationsRepository() {
        return new ResponseEntity<>(hibernateOrganizationsRepository.findAll(), HttpStatus.OK);
    }

    /*FindById*/
    @ApiOperation(value = "Get from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Organizations"),
            @ApiResponse(code = 400, message = "Invalid Organizations ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Organizations was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/spring-data/{id}")
    public ResponseEntity<HibernateOrganizations> getHibernateOrganizationsByIdRepository(@ApiParam("Path Id") @PathVariable Long id) {
        HibernateOrganizations t = hibernateOrganizationsRepository.findById(id).orElse(null);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    /*Create */
    @PostMapping("/spring-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateOrganizations> createConvertedHibernateOrganizations(@RequestBody @Valid OrganizationsCreateRequest request) {
        return new ResponseEntity<>(hibernateOrganizationsRepository.saveAndFlush(conversionService.convert(request, HibernateOrganizations.class)), CREATED);
    }

    /*Update*/
    @ApiOperation(value = "Update Organizations by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Organizations update"),
            @ApiResponse(code = 400, message = "Invalid Organizations ID supplied"),
            @ApiResponse(code = 404, message = "Organizations was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping("/spring-data/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateOrganizations> updateHibernateOrganizationsRepository(@RequestBody @Valid OrganizationsUpdateRequest request) {
        return new ResponseEntity<>(hibernateOrganizationsRepository.save(conversionService.convert(request, HibernateOrganizations.class)), HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/spring-data/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Long> deleteHibernateOrganizationsRepository(@PathVariable("id") Long id) {
        hibernateOrganizationsRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
