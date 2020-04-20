package com.htp.controller;

import com.htp.controller.requests.organizations.OrganizationsCreateRequest;
import com.htp.controller.requests.organizations.OrganizationsUpdateRequest;
import com.htp.domain.Organizations;
import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.repository.jdbc.OrganizationsDao;
import com.htp.repository.springdata.HibernateOrganizationsRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin
@RequestMapping(value = "/organization")
@RequiredArgsConstructor
public class OrganizationsProfileController {
    private final OrganizationsDao organizationsDao;

    private final ConversionService conversionService;

    private final HibernateOrganizationsRepository hibernateOrganizationsRepository;

    /*JDBC*/
    /*FindAll*/
    @GetMapping()
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Organizations>> getOrganizations() {
        return new ResponseEntity<>(organizationsDao.findAll(), HttpStatus.OK);
    }

    /*FindById*/
    @ApiOperation(value = "Get Organizations from server by id")
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
    @GetMapping("/{id}")
    public ResponseEntity<Organizations> getOrganizationById(@ApiParam("Organization Path Id") @PathVariable Long id) {
        Organizations organizations = organizationsDao.findById(id);
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    /*Create*/
    @PostMapping()
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Organizations> createOrganizations(@RequestBody OrganizationsCreateRequest request) {
        Organizations t = new Organizations();
        //userID is empty - will be generated by DB
        t.setLogin(request.getLogin());
        t.setPassword(request.getPassword());
        t.setRole(request.getRole());
        t.setWebSite(request.getWebSite());
        t.setPhoneNumber(request.getPhoneNumber());
        t.setLocation(request.getLocation());
        t.setWorkingTime(request.getWorkingTime());
        t.setSpecialize(request.getSpecialize());
        t.setEMail(request.getEMail());

        return new ResponseEntity<>(organizationsDao.save(t), HttpStatus.OK);
    }

    /*Update*/
    @ApiOperation(value = "Update Organization by userID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Organization update 1111111"),
            @ApiResponse(code = 400, message = "Invalid Organization ID supplied 111111"),
            @ApiResponse(code = 404, message = "Organization was not found 111111"),
            @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Organizations> updateOrganization(@PathVariable("id") Long id,
                                                            @RequestBody OrganizationsCreateRequest request) {
        Organizations t = organizationsDao.findById(id);
        t.setLogin(request.getLogin());
        t.setPassword(request.getPassword());
        t.setRole(request.getRole());
        t.setWebSite(request.getWebSite());
        t.setPhoneNumber(request.getPhoneNumber());
        t.setLocation(request.getLocation());
        t.setWorkingTime(request.getWorkingTime());
        t.setSpecialize(request.getSpecialize());
        t.setEMail(request.getEMail());

        return new ResponseEntity<>(organizationsDao.updateOne(t), HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Long> deleteOrganization(@PathVariable("id") Long id) {
        organizationsDao.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /*SPRING DATA*/

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
        //HibernateTasks savedConvertedTasks = conversionService.convert(request, HibernateTasks.class);
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
