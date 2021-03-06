package com.htp.controller;

import com.htp.controller.requests.tracking.TrackingCreateRequest;
import com.htp.controller.requests.tracking.TrackingUpdateRequest;
import com.htp.domain.hibernate.HibernateTracking;
import com.htp.repository.springdata.HibernateTrackingRepository;
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
@RequestMapping(value = "/admin/rest/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final HibernateTrackingRepository hibernateTrackingRepository;

    private final ConversionService conversionService;

    /*FindAll*/
    @GetMapping("/spring-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateTracking>> getHibernatesTrackingRepository() {
        return new ResponseEntity<>(hibernateTrackingRepository.findAll(), HttpStatus.OK);
    }

    /*FindById*/
    @ApiOperation(value = "Get from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Tracking"),
            @ApiResponse(code = 400, message = "Invalid Tracking ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Tracking was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/spring-data/{id}")
    public ResponseEntity<HibernateTracking> getHibernateTrackingByIdRepository(@ApiParam("Path Id") @PathVariable Long id) {
        HibernateTracking t = hibernateTrackingRepository.findById(id).orElse(null);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    /*Create */
    @PostMapping("/spring-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTracking> createConvertedHibernateTracking(@RequestBody @Valid TrackingCreateRequest request) {
        HibernateTracking savedConvertedTracking = conversionService.convert(request, HibernateTracking.class);
        return new ResponseEntity<>(hibernateTrackingRepository.saveAndFlush(savedConvertedTracking), CREATED);
    }

    /*Update*/
    @ApiOperation(value = "Update Tracking by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Tracking update"),
            @ApiResponse(code = 400, message = "Invalid Tracking ID supplied"),
            @ApiResponse(code = 404, message = "Tracking was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/spring-data/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTracking> updateHibernateTrackingRepository(@RequestBody @Valid TrackingUpdateRequest request) {
        return new ResponseEntity<>(hibernateTrackingRepository.save(conversionService.convert(request, HibernateTracking.class)), HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/spring-data/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Long> deleteHibernateTrackingRepository(@PathVariable("id") Long id) {
        hibernateTrackingRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
