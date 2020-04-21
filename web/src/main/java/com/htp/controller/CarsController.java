package com.htp.controller;

import com.htp.controller.requests.cars.CarsCreateRequest;
import com.htp.controller.requests.cars.CarsUpdateRequest;
import com.htp.domain.hibernate.HibernateCars;
import com.htp.repository.springdata.HibernateCarsRepository;
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
@RequestMapping(value = "/admin/rest/cars")
@RequiredArgsConstructor
public class CarsController {

    private final ConversionService conversionService;

    private final HibernateCarsRepository hibernateCarsRepository;

    /*FindAll*/
    @GetMapping("/spring-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateCars>> getHibernatesCarsRepository() {
        return new ResponseEntity<>(hibernateCarsRepository.findAll(), HttpStatus.OK);
    }

    /*FindById*/
    @ApiOperation(value = "Get from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Cars"),
            @ApiResponse(code = 400, message = "Invalid Cars ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Cars was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping(value = "/spring-data/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateCars> getHibernateCarsByIdRepository(@ApiParam("Path Id") @PathVariable Long id) {
        HibernateCars t = hibernateCarsRepository.findById(id).orElse(null);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    /*Create */
    @PostMapping("/spring-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateCars> createConvertedHibernateCars(@RequestBody @Valid CarsCreateRequest request) {
        return new ResponseEntity<>(hibernateCarsRepository.saveAndFlush(conversionService.convert(request, HibernateCars.class)), CREATED);
    }

    /*Update*/
    @ApiOperation(value = "Update Cars by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Cars update"),
            @ApiResponse(code = 400, message = "Invalid Cars ID supplied"),
            @ApiResponse(code = 404, message = "Cars was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping("/spring-data/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateCars> updateHibernateCarsRepository(@RequestBody @Valid CarsUpdateRequest request) {
        return new ResponseEntity<>(hibernateCarsRepository.save(conversionService.convert(request, HibernateCars.class)), HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/spring-data/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Long> deleteHibernateCarsRepository(@PathVariable("id") Long id) {
        hibernateCarsRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
