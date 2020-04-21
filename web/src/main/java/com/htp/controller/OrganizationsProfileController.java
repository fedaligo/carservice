package com.htp.controller;

import com.htp.controller.requests.organizations.OrganizationsUpdateRequest;
import com.htp.controller.requests.tracking.TrackingUpdateRequest;
import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.domain.hibernate.HibernateTracking;
import com.htp.repository.springdata.HibernateOrganizationsRepository;
import com.htp.repository.springdata.HibernateTrackingRepository;
import com.htp.security.util.PrincipalUtil;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/organization")
@RequiredArgsConstructor
public class OrganizationsProfileController {


    private final ConversionService conversionService;

    private final HibernateOrganizationsRepository hibernateOrganizationsRepository;

    private final HibernateTrackingRepository hibernateTrackingRepository;

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
    @PutMapping("/organization")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateOrganizations> updateMyOrganization(@RequestBody @Valid OrganizationsUpdateRequest request,
                                                                       @ApiIgnore Principal principal) {

        HibernateOrganizations hibernateOrganizations = hibernateOrganizationsRepository.findByLoginNotDeleted(
                                                                        principal.getName()).orElse(null);
        request.setId(hibernateOrganizations.getId());
        return new ResponseEntity<>(hibernateOrganizationsRepository.save(conversionService.convert(request,
                                                                        HibernateOrganizations.class)), HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/organization")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> deleteHibernateOrganizationsRepository(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        hibernateOrganizationsRepository.fakeDelete(login);
        return new ResponseEntity<>(login + " is deleted", HttpStatus.OK);
    }

    /*View organization profile*/
    @ApiOperation(value = "Get user from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting user"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/organization")
    public ResponseEntity<HibernateOrganizations> viewOrganizationProfile(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateOrganizations org = hibernateOrganizationsRepository.findByLoginNotDeleted(login).orElse(null);
        return new ResponseEntity<>(org, HttpStatus.OK);
    }

    /*FindAll tracks*/
    @GetMapping("/tracking/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateTracking>> getHibernatesTrackingRepository(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateOrganizations org = hibernateOrganizationsRepository.findByLoginNotDeleted(login).orElse(null);
        if(org != null){
            return new ResponseEntity<>(hibernateTrackingRepository.findAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Collections.emptyList(),HttpStatus.OK);
        }
    }

    /*Update track*/
    @ApiOperation(value = "Update Tracking by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Tracking update"),
            @ApiResponse(code = 400, message = "Invalid Tracking ID supplied"),
            @ApiResponse(code = 404, message = "Tracking was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/tracking/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTracking> updateHibernateTrackingRepository(@ApiParam("Path Id") @PathVariable Long id,
                                                                               @RequestBody @Valid TrackingUpdateRequest request,
                                                                               @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateOrganizations org = hibernateOrganizationsRepository.findByLoginNotDeleted(login).orElse(null);

        request.setIdOrganaizer(org.getId());
        HibernateTracking hibernateTracking = hibernateTrackingRepository.findById(id).orElse(null);
        request.setId(hibernateTracking.getId());
        request.setIdTask(hibernateTracking.getTasks().getId());
        return new ResponseEntity<>(hibernateTrackingRepository.save(conversionService.convert(request, HibernateTracking.class)), HttpStatus.OK);
    }

    /*Cancel track*/
    @ApiOperation(value = "Update Tracking by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Tracking update"),
            @ApiResponse(code = 400, message = "Invalid Tracking ID supplied"),
            @ApiResponse(code = 404, message = "Tracking was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/tracking/cancel/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTracking> cancelHibernateTrackingRepository(@ApiParam("Path Id") @PathVariable Long id,
                                                                               @ApiIgnore Principal principal) {
        TrackingUpdateRequest request = new TrackingUpdateRequest();
        String login = PrincipalUtil.getUsername(principal);
        HibernateOrganizations org = hibernateOrganizationsRepository.findByLoginNotDeleted(login).orElse(null);
        //request.setIdOrganaizer(null);
        HibernateTracking hibernateTracking = hibernateTrackingRepository.findById(id).orElse(null);
        if(hibernateTracking.getOrganizations().getId()==org.getId()) {
            request.setId(id);
            request.setIdTask(hibernateTracking.getTasks().getId());
            return new ResponseEntity<>(hibernateTrackingRepository.save(conversionService.convert(request, HibernateTracking.class)), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(hibernateTracking,HttpStatus.OK);
        }
    }
}
