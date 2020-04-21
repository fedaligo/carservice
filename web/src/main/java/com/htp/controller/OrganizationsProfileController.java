package com.htp.controller;

import com.htp.controller.requests.organizations.OrganizationsUpdateRequest;
import com.htp.controller.requests.tracking.TrackingUpdateRequest;
import com.htp.domain.hibernate.HibernateOrganizations;
import com.htp.domain.hibernate.HibernateTracking;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.repository.springdata.HibernateOrganizationsRepository;
import com.htp.repository.springdata.HibernateTrackingRepository;
import com.htp.security.util.PrincipalUtil;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/organization")
@RequiredArgsConstructor
public class OrganizationsProfileController {


    private final ConversionService conversionService;

    private final HibernateOrganizationsRepository hibernateOrganizationsRepository;

    private final HibernateTrackingRepository hibernateTrackingRepository;

    @ApiOperation(value = "Update my Organization")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Organizations update"),
            @ApiResponse(code = 400, message = "Bad request, try again"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Organizations was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateOrganizations> updateMyOrganization(@RequestBody @Valid OrganizationsUpdateRequest request,
                                                                       @ApiIgnore Principal principal) {

        HibernateOrganizations hibernateOrganizations = hibernateOrganizationsRepository.findByLoginNotDeleted(
                principal.getName()).orElseThrow(() -> new EntityNotFoundException(principal.getName()));
        request.setId(hibernateOrganizations.getId());
        log.info("Organization with name {} made update of his profile", principal.getName());
        return new ResponseEntity<>(hibernateOrganizationsRepository.save(conversionService.convert(request,
                HibernateOrganizations.class)), HttpStatus.OK);
    }

    @DeleteMapping("/profile")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Access denied")
    })
    @ApiOperation(value = "Delete my Organization")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> fakedeleteOrganization(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        hibernateOrganizationsRepository.fakeDelete(login);
        log.info("Organization with name {} deleted his profile", login);
        return new ResponseEntity<>(login + " is deleted", HttpStatus.OK);
    }

    @ApiOperation(value = "Get info about my Organization")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Organization"),
            @ApiResponse(code = 400, message = "Bad request, try again"),
            @ApiResponse(code = 401, message = "You are not authorized"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Organization was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/profile")
    public ResponseEntity<HibernateOrganizations> viewOrganizationProfile(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateOrganizations org = hibernateOrganizationsRepository.findByLoginNotDeleted(
                principal.getName()).orElseThrow(() -> new EntityNotFoundException(login));
        log.info("Organization with name {} is watching info about his profile", login);
        return new ResponseEntity<>(org, HttpStatus.OK);
    }

    @ApiOperation(value = "View all exist tracks")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Access denied")
    })
    @GetMapping("/tracking")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateTracking>> getAllTracks(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        log.info("Organization with name {} is watching info about all existing tracks", login);
        return new ResponseEntity<>(hibernateTrackingRepository.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "Confirm chosen Track")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Tracking update"),
            @ApiResponse(code = 400, message = "Bad request, try again"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Track was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/tracking/confirm/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTracking> confirmTrack(@ApiParam("Path Id") @PathVariable Long id,
                                                          @RequestBody @Valid TrackingUpdateRequest request,
                                                          @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateOrganizations org = hibernateOrganizationsRepository.findByLoginNotDeleted(
                principal.getName()).orElseThrow(() -> new EntityNotFoundException(login));
        HibernateTracking hibernateTracking = hibernateTrackingRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("track"));
        if (hibernateTracking.getOrganizations() == null) {
            request.setIdOrganaizer(org.getId());
            request.setId(hibernateTracking.getId());
            request.setIdTask(hibernateTracking.getTasks().getId());
            log.info("Organization with name {} confirmed task with id={}", login, id);
            return new ResponseEntity<>(hibernateTrackingRepository.save(conversionService.convert(request, HibernateTracking.class)), HttpStatus.OK);
        } else {
            log.info("Organization with name {} can't confirm this task with id={}", login, id);
            return new ResponseEntity<>(hibernateTracking, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Cancel chosen Track")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Tracking update"),
            @ApiResponse(code = 400, message = "Bad request, try again"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Track was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/tracking/cancel/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTracking> cancelTrack(@ApiParam("Path Id") @PathVariable Long id,
                                                         @ApiIgnore Principal principal) {
        TrackingUpdateRequest request = new TrackingUpdateRequest();
        String login = PrincipalUtil.getUsername(principal);
        HibernateOrganizations org = hibernateOrganizationsRepository.findByLoginNotDeleted(
                principal.getName()).orElseThrow(() -> new EntityNotFoundException(login));
        HibernateTracking hibernateTracking = hibernateTrackingRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("track"));
        if (hibernateTracking.getOrganizations().getId() == org.getId()) {
            request.setId(id);
            request.setIdTask(hibernateTracking.getTasks().getId());
            log.info("Organization with name {} canceled this task with id={}", login, id);
            return new ResponseEntity<>(hibernateTrackingRepository.save(conversionService.convert(request, HibernateTracking.class)), HttpStatus.OK);
        } else {
            log.info("Organization with name {} can't cancel this task with id={}", login, id);
            return new ResponseEntity<>(hibernateTracking, HttpStatus.NOT_FOUND);
        }
    }
}
