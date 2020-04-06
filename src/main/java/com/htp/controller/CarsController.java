package com.htp.controller;

import com.htp.controller.requests.cars.CarsCreateRequest;
import com.htp.domain.Cars;
import com.htp.domain.hibernate.HibernateCars;
import com.htp.repository.hibernate.impl.HibernateCarsDaoImpl;
import com.htp.repository.hibernate.impl.HibernateUsersDaoImpl;
import com.htp.repository.jdbc.CarsDao;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/cars")
@RequiredArgsConstructor
public class CarsController {

        private final CarsDao carsDao;

        private final HibernateCarsDaoImpl hibernateCarsDao;

        private final HibernateUsersDaoImpl hibernateUsersDao;

        @GetMapping("/all")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<Cars>> getCars() {
            return new ResponseEntity<>(carsDao.findAll(), HttpStatus.OK);
        }

        @GetMapping("/hibernate/all")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<HibernateCars>> getHibernateCars() {
            return new ResponseEntity<>(hibernateCarsDao.findAll(), HttpStatus.OK);
        }

        @ApiOperation(value = "Get Cars from server by id")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful getting Cars"),
                @ApiResponse(code = 400, message = "Invalid Cars ID supplied"),
                @ApiResponse(code = 401, message = "Lol kek"),
                @ApiResponse(code = 404, message = "Cars was not found"),
                @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @RequestMapping(value = "/getCarsById/{id}", method = RequestMethod.GET)
        public ResponseEntity<Cars> getCarById(@ApiParam("Car Path Id") @PathVariable Long id) {
            Cars cars = carsDao.findById(id);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }

        @ApiOperation(value = "Get Cars from server by id")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful getting Cars"),
                @ApiResponse(code = 400, message = "Invalid Cars ID supplied"),
                @ApiResponse(code = 401, message = "Lol kek"),
                @ApiResponse(code = 404, message = "Cars was not found"),
                @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @RequestMapping(value = "/hibernate/getCarById/{id}", method = RequestMethod.GET)
        public ResponseEntity<HibernateCars> getHibernateCarById(@ApiParam("Car Path Id") @PathVariable Long id) {
            HibernateCars cars = hibernateCarsDao.findById(id);
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }


        @PostMapping("/create")
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
            t.setUserId(request.getUser_id());

            Cars savedCar = carsDao.save(t);

            return new ResponseEntity<>(savedCar, HttpStatus.OK);
        }

        @PostMapping("/hibernate/create")
        @Transactional
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<HibernateCars> createHibernateCars(@RequestBody CarsCreateRequest request) {
            HibernateCars t = new HibernateCars();
            t.setCarBrand(request.getCarBrand());
            t.setBrandModel(request.getBrandModel());
            t.setTypeOfTransmission(request.getTypeOfTransmission());
            t.setTypeOfFuel(request.getTypeOfFuel());
            t.setCarWeight(request.getCarWeight());
            t.setVinNumber(request.getVinNumber());
            t.setUser(hibernateUsersDao.findById(request.getUser_id()));

            return new ResponseEntity<>(hibernateCarsDao.save(t), HttpStatus.OK);
        }

        @ApiOperation(value = "Update Cars by userID")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful Cars update 1111111"),
                @ApiResponse(code = 400, message = "Invalid Cars ID supplied 111111"),
                @ApiResponse(code = 404, message = "Cars was not found 111111"),
                @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
        })
        /*@ApiImplicitParams({
                @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
        })*/
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
            t.setUserId(request.getUser_id());

            return new ResponseEntity<>(carsDao.updateOne(t), HttpStatus.OK);
        }

        @ApiOperation(value = "Update Cars by userID")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful Cars update 1111111"),
                @ApiResponse(code = 400, message = "Invalid Cars ID supplied 111111"),
                @ApiResponse(code = 404, message = "Cars was not found 111111"),
                @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
        })
        @RequestMapping(value = "/hibernate/update/{id}", method = RequestMethod.PUT)
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<HibernateCars> updateHibernateCar(@ApiParam(value = "Car ID", required = false) @PathVariable("id") Long id,
                                                                  @RequestBody CarsCreateRequest request) {

            HibernateCars t = hibernateCarsDao.findById(id);
            t.setCarBrand(request.getCarBrand());
            t.setBrandModel(request.getBrandModel());
            t.setTypeOfTransmission(request.getTypeOfTransmission());
            t.setTypeOfFuel(request.getTypeOfFuel());
            t.setCarWeight(request.getCarWeight());
            t.setVinNumber(request.getVinNumber());
            t.setUser(hibernateUsersDao.findById(request.getUser_id()));

            return new ResponseEntity<>(hibernateCarsDao.updateOne(t), HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<Long> deleteCar(@PathVariable("id") Long id) {
            carsDao.deleteById(id);
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
