package com.htp.controller;

import com.htp.controller.convert.tracking.TrackingCreateRequestConverter;
import com.htp.controller.requests.cars.CarsCreateRequest;
import com.htp.controller.requests.cars.CarsUpdateRequest;
import com.htp.controller.requests.tasks.TasksCreateRequest;
import com.htp.controller.requests.tasks.TasksUpdateRequest;
import com.htp.controller.requests.tracking.TrackingCreateRequest;
import com.htp.controller.requests.users.UserUpdateRequest;
import com.htp.domain.hibernate.HibernateCars;
import com.htp.domain.hibernate.HibernateTasks;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.exceptions.EntityNotFoundException;
import com.htp.repository.springdata.HibernateCarsRepository;
import com.htp.repository.springdata.HibernateTasksRepository;
import com.htp.repository.springdata.HibernateTrackingRepository;
import com.htp.repository.springdata.HibernateUsersRepository;
import com.htp.security.util.PrincipalUtil;
import io.swagger.annotations.*;
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

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UsersProfileController {

    private final HibernateCarsRepository hibernateCarsRepository;

    private final HibernateUsersRepository hibernateUsersRepository;

    private final HibernateTasksRepository hibernateTasksRepository;

    private final HibernateTrackingRepository hibernateTrackingRepository;

    private final TrackingCreateRequestConverter trackingCreateRequestConverter;

    private final ConversionService conversionService;

    @ApiOperation(value = "Update my User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful User update"),
            @ApiResponse(code = 400, message = "Bad request, try again"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/profile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateUsers> updateMyUser(@RequestBody @Valid UserUpdateRequest request,
                                                       @ApiIgnore Principal principal) {
        HibernateUsers hibernateUsers = hibernateUsersRepository.findByLoginNotDeleted(principal.getName()).
                orElseThrow(() -> new EntityNotFoundException(principal.getName()));
        request.setUserId(hibernateUsers.getUserId());
        log.info("User with username {} made update of his profile", hibernateUsers.getLogin());
        return new ResponseEntity<>(hibernateUsersRepository.save(conversionService.convert(request, HibernateUsers.class)), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete my User")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Access denied")
    })
    @DeleteMapping("/profile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> deleteMyUser(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        hibernateUsersRepository.fakeDelete(login);
        log.info("User with username {} deleted his profile", login);
        return new ResponseEntity<>(login + " is deleted", HttpStatus.OK);
    }

    @ApiOperation(value = "View my User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting User"),
            @ApiResponse(code = 400, message = "Bad request, try again"),
            @ApiResponse(code = 401, message = "You are not authorized"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/profile")
    public ResponseEntity<HibernateUsers> viewUserProfile(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers hibernateUsers = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        log.info("User with username {} is watching his profile", hibernateUsers.getLogin());
        return new ResponseEntity<>(hibernateUsers, HttpStatus.OK);
    }

    @ApiOperation(value = "Create car for User")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Access denied")
    })
    @PostMapping("/car")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateCars> createConvertedHibernateCars(@RequestBody @Valid CarsCreateRequest request,
                                                                      @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        request.setUserId(user.getUserId());
        log.info("User with username {} created a new car", login);
        return new ResponseEntity<>(hibernateCarsRepository.saveAndFlush(conversionService.convert(request, HibernateCars.class)), CREATED);
    }

    @ApiOperation(value = "Update Cars for User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Car update"),
            @ApiResponse(code = 400, message = "Bad request, try again"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Car was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @PutMapping("/car")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateCars> updateHibernateCarsRepository(@RequestBody @Valid CarsUpdateRequest request,
                                                                       String brand, String model, @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).
                orElseThrow(() -> new EntityNotFoundException("car"));
        request.setUserId(user.getUserId());
        request.setId(car.getId());
        log.info("User with username {} changed info about his car {} {}", login, brand, model);
        return new ResponseEntity<>(hibernateCarsRepository.save(conversionService.convert(request, HibernateCars.class)), HttpStatus.OK);

    }

    @ApiOperation(value = "Delete Car for User")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Access denied")
    })
    @DeleteMapping("/car")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> deleteCars(String brand, String model, @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        hibernateCarsRepository.deleteCars(brand, model, user);
        log.info("User with username {} deleted his car {} {}", login, brand, model);
        return new ResponseEntity<>("car deleted", HttpStatus.OK);
    }

    @ApiOperation(value = "Get info about one Car for User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Car"),
            @ApiResponse(code = 400, message = "Bad request, try again"),
            @ApiResponse(code = 401, message = "You are not authorized"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Car was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/car")
    public ResponseEntity<HibernateCars> viewOneCar(String brand, String model, @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).
                orElseThrow(() -> new EntityNotFoundException("car"));
        log.info("User with username {} is watching info about his car {} {}", login, brand, model);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @ApiOperation(value = "Get info about all Cars for User")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Access denied")
    })
    @GetMapping("/car/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateCars>> viewAllCars(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        log.info("User with username {} is watching info about all his cars", login);
        return new ResponseEntity<>(hibernateCarsRepository.findByUser(user), HttpStatus.OK);
    }

    @ApiOperation(value = "Create new Task for Car of User")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Access denied")
    })
    @PostMapping("/task")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTasks> createNewTask(@RequestBody @Valid TasksCreateRequest request,
                                                        String brand, String model,
                                                        @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).
                orElseThrow(() -> new EntityNotFoundException("car"));
        request.setIdCar(car.getId());
        HibernateTasks task = hibernateTasksRepository.saveAndFlush(conversionService.convert(request, HibernateTasks.class));
        TrackingCreateRequest trackingCreateRequest = new TrackingCreateRequest();
        trackingCreateRequest.setIdTask(task.getId());
        hibernateTrackingRepository.saveAndFlush(trackingCreateRequestConverter.convert(trackingCreateRequest));
        log.info("User with username {} created a new task for his car {} {}", login, brand, model);
        return new ResponseEntity<>(task, CREATED);
    }

    @ApiOperation(value = "Update Task for Car of User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Task update"),
            @ApiResponse(code = 400, message = "Bad request, try again"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Task was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/task")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTasks> updateTask(@RequestBody @Valid TasksUpdateRequest request,
                                                     String brand, String model, String serviceworkname,
                                                     @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).
                orElseThrow(() -> new EntityNotFoundException("car"));
        HibernateTasks task = hibernateTasksRepository.findByCarsAndServiceWorkName(car, serviceworkname).
                orElseThrow(() -> new EntityNotFoundException(serviceworkname));
        request.setIdCar(car.getId());
        request.setId(task.getId());
        log.info("User with username {} updated task {} for his car {} {}", login, serviceworkname, brand, model);
        return new ResponseEntity<>(hibernateTasksRepository.save(conversionService.convert(request, HibernateTasks.class)), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Task for Car of User")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Access denied")
    })
    @DeleteMapping("/task")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> deleteTask(String brand, String model, String serviceworkname,
                                             @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).
                orElseThrow(() -> new EntityNotFoundException("car"));
        hibernateTasksRepository.deleteTasks(serviceworkname, car);
        log.info("User with username {} deleted task {} for his car {} {}", login, serviceworkname, brand, model);
        return new ResponseEntity<>("task deleted", HttpStatus.OK);

    }

    @ApiOperation(value = "View all Tasks for one Car of User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Task"),
            @ApiResponse(code = 400, message = "Bad request, try again"),
            @ApiResponse(code = 401, message = "You are not authorized"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Task was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/task")
    public ResponseEntity<List<HibernateTasks>> viewTaskProfile(String brand, String model,
                                                                @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).
                orElseThrow(() -> new EntityNotFoundException("car"));
        List<HibernateTasks> task = hibernateTasksRepository.findByCars(car);
        log.info("User with username {} is watching all tasks for his car {} {}", login, brand, model);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @ApiOperation(value = "View one Task for one Car of User")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Task"),
            @ApiResponse(code = 400, message = "Bad request, try again"),
            @ApiResponse(code = 401, message = "You are not authorized"),
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 404, message = "Task was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/task/one")
    public ResponseEntity<HibernateTasks> viewOneTaskProfile(String brand, String model, String serviceworkname,
                                                             @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).
                orElseThrow(() -> new EntityNotFoundException("car"));
        HibernateTasks task = hibernateTasksRepository.findByCarsAndServiceWorkName(car, serviceworkname).
                orElseThrow(() -> new EntityNotFoundException(serviceworkname));
        log.info("User with username {} is watching task {} for his car {} {}", login, serviceworkname, brand, model);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @ApiOperation(value = "View all Task of User")
    @ApiResponses({
            @ApiResponse(code = 403, message = "Access denied")
    })
    @GetMapping("/task/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateCars>> getAllTasksOfUser(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).
                orElseThrow(() -> new EntityNotFoundException(login));
        log.info("User with username {} is watching all tasks", login);
        return new ResponseEntity<>(hibernateCarsRepository.listOfTasks(user), HttpStatus.OK);
    }
}
