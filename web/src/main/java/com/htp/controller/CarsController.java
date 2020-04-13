package com.htp.controller;

import com.htp.controller.requests.cars.CarsCreateRequest;
import com.htp.controller.requests.cars.CarsUpdateRequest;
import com.htp.domain.Cars;
import com.htp.domain.hibernate.HibernateCars;
import com.htp.repository.jdbc.CarsDao;
import com.htp.repository.springdata.HibernateCarsRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/cars")
@RequiredArgsConstructor
public class CarsController {

    private final CarsDao carsDao;

    private final ConversionService conversionService;

    private final HibernateCarsRepository hibernateCarsRepository;

    /*JDBC*/

    /*FindAll*/
    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Cars>> getCars() {
        return new ResponseEntity<>(carsDao.findAll(), HttpStatus.OK);
    }

    /*FindById*/
    @ApiOperation(value = "Get Cars from server by id")
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
    @RequestMapping(value = "/getCarsById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cars> getCarById(@ApiParam("Car Path Id") @PathVariable Long id) {
        Cars cars = carsDao.findById(id);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /*Create*/
    @PostMapping("/create")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cars> createCar(@RequestBody CarsCreateRequest request) {
        Cars t = new Cars();
        t.setCarBrand(request.getCarBrand());
        t.setBrandModel(request.getBrandModel());
        t.setTypeOfTransmission(request.getTypeOfTransmission());
        t.setTypeOfFuel(request.getTypeOfFuel());
        t.setCarWeight(request.getCarWeight());
        t.setVinNumber(request.getVinNumber());
        t.setUserId(request.getUserId());

        Cars savedCar = carsDao.save(t);

        return new ResponseEntity<>(savedCar, HttpStatus.OK);
    }

    /*Update*/
    @ApiOperation(value = "Update Cars by userID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Cars update 1111111"),
            @ApiResponse(code = 400, message = "Invalid Cars ID supplied 111111"),
            @ApiResponse(code = 404, message = "Cars was not found 111111"),
            @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
    })
    @ApiImplicitParams({
                @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
        })
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Cars> updateCars(@PathVariable("id") Long id,
                                           @RequestBody CarsCreateRequest request) {
        Cars t = carsDao.findById(id);
        t.setCarBrand(request.getCarBrand());
        t.setBrandModel(request.getBrandModel());
        t.setTypeOfTransmission(request.getTypeOfTransmission());
        t.setTypeOfFuel(request.getTypeOfFuel());
        t.setCarWeight(request.getCarWeight());
        t.setVinNumber(request.getVinNumber());
        t.setUserId(request.getUserId());

        return new ResponseEntity<>(carsDao.updateOne(t), HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/delete/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteCar(@PathVariable("id") Long id) {
        carsDao.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    /*SPRING DATA*/

    /*FindAll*/
    @GetMapping("/spring-data/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateCars>> getHibernatesCarsRepository() {
        return new ResponseEntity<>(hibernateCarsRepository.findAll(), HttpStatus.OK);
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
    public ResponseEntity<Page<HibernateCars>> getCarsSpringData(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(hibernateCarsRepository.findAll(pageable), HttpStatus.OK);
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
    @RequestMapping(value = "/spring-data/getCarsById/{id}", method = RequestMethod.GET)
    public ResponseEntity<HibernateCars> getHibernateCarsByIdRepository(@ApiParam("Path Id") @PathVariable Long id) {
        HibernateCars t = hibernateCarsRepository.findById(id).orElse(null);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    /*Create */
    @PostMapping("/spring-data/create(converted)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional
    public ResponseEntity<HibernateCars> createConvertedHibernateCars(@RequestBody @Valid CarsCreateRequest request) {
        //HibernateTasks savedConvertedTasks = conversionService.convert(request, HibernateTasks.class);
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
    @PutMapping("/spring-data/update(converted)/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateCars> updateHibernateCarsRepository(@RequestBody @Valid CarsUpdateRequest request) {
        return new ResponseEntity<>(hibernateCarsRepository.save(conversionService.convert(request, HibernateCars.class)), HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/spring-data/delete/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteHibernateCarsRepository(@PathVariable("id") Long id) {
        hibernateCarsRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
