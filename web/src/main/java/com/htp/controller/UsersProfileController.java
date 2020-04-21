package com.htp.controller;

import com.htp.controller.convert.tracking.TrackingCreateRequestConverter;
import com.htp.controller.requests.cars.CarsCreateRequest;
import com.htp.controller.requests.cars.CarsUpdateRequest;
import com.htp.controller.requests.tasks.TasksCreateRequest;
import com.htp.controller.requests.tasks.TasksUpdateRequest;
import com.htp.controller.requests.tracking.TrackingCreateRequest;
import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.controller.requests.users.UserUpdateRequest;
import com.htp.domain.hibernate.HibernateCars;
import com.htp.domain.hibernate.HibernateTasks;
import com.htp.domain.hibernate.HibernateUsers;
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

    /*@GetMapping(value = "/test/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateUsers> getHibernateUserByIdRepository123(@ApiParam("User Path Id") @PathVariable String id,
                                                                            @ApiIgnore Principal principal) {
        String username = PrincipalUtil.getUsername(principal);
        HibernateUsers performer = hibernateUsersRepository.findByLogin(username).orElseThrow(() -> new EntityNotFoundException(HibernateUsers.class, username));
        HibernateUsers user = hibernateUsersRepository.findById(Long.valueOf(id)).orElseThrow(() -> new EntityNotFoundException(HibernateUsers.class, id));

        log.info("Performer with username {} find by id {} user with login {}", performer.getLogin(), id, user.getLogin());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
*/

    /*USER*/
    /*Update user*/
    @ApiOperation(value = "Update user by userID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update 1111111"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied 111111"),
            @ApiResponse(code = 404, message = "User was not found 111111"),
            @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
    })
    @PutMapping("/user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateUsers> updateMyUser(@RequestBody @Valid UserCreateRequest crequest,
                                                                        @ApiIgnore UserUpdateRequest request, Principal principal) {
        HibernateUsers hibernateUsers = hibernateUsersRepository.findByLoginNotDeleted(principal.getName()).orElse(null);
        request.setUserId(hibernateUsers.getUserId());
        request.setEMail(crequest.getEMail());
        request.setLogin(crequest.getLogin());
        request.setPassword(crequest.getPassword());
        request.setPhNumberUser(crequest.getPhNumberUser());
        request.setGender(crequest.getGender());
        return new ResponseEntity<>( hibernateUsersRepository.save(conversionService.convert(request, HibernateUsers.class)), HttpStatus.OK);
    }

    /*Fake delete user*/
    @DeleteMapping("/user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> deleteMyUser(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        hibernateUsersRepository.fakeDelete(login);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    /*View user profile*/
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
    @GetMapping("/user")
    public ResponseEntity<HibernateUsers> viewUserProfile(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*CAR*/
    /*Create car for user*/
    @PostMapping("/car")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateCars> createConvertedHibernateCars(@RequestBody @Valid CarsCreateRequest request,
                                                                      @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        request.setUserId(user.getUserId());
        return new ResponseEntity<>(hibernateCarsRepository.saveAndFlush(conversionService.convert(request, HibernateCars.class)), CREATED);
    }

    /*Update car*/
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
    @PutMapping("/car")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateCars> updateHibernateCarsRepository(@RequestBody @Valid CarsUpdateRequest request,
                                                                       String brand, String model, @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).orElse(null);
        request.setUserId(user.getUserId());
        request.setId(car.getId());
        return new ResponseEntity<>(hibernateCarsRepository.save(conversionService.convert(request, HibernateCars.class)), HttpStatus.OK);
    }

    /*Delete car*/
    @DeleteMapping("/car")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> deleteHibernateCarsRepository(String brand, String model, @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        hibernateCarsRepository.deleteCars(brand,model,user);
        return new ResponseEntity<>("car deleted", HttpStatus.OK);
    }

    /*View one car profile*/
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
    @GetMapping("/car")
    public ResponseEntity<HibernateCars> viewCarProfile(String brand, String model, @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).orElse(null);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /*View all cars profile*/
    @GetMapping("/car/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateCars>> getHibernatesCarsRepository(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        return new ResponseEntity<>(hibernateCarsRepository.findByUser(user), HttpStatus.OK);
    }

    /*TASK*/
    /*Create */
    @PostMapping("/task")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTasks> createConvertedHibernateTasks(@RequestBody @Valid TasksCreateRequest request,
                                                                        String brand, String model,
                                                                        @ApiIgnore Principal principal ) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).orElse(null);
        request.setIdCar(car.getId());
        HibernateTasks task = hibernateTasksRepository.saveAndFlush(conversionService.convert(request, HibernateTasks.class));
        TrackingCreateRequest trackingCreateRequest = new TrackingCreateRequest();
        trackingCreateRequest.setIdTask(task.getId());
        hibernateTrackingRepository.saveAndFlush(trackingCreateRequestConverter.convert(trackingCreateRequest));
        return new ResponseEntity<>(task, CREATED);
    }

    /*Update*/
    @ApiOperation(value = "Update Tasks by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Tasks update"),
            @ApiResponse(code = 400, message = "Invalid Tasks ID supplied"),
            @ApiResponse(code = 404, message = "Tasks was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/task")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTasks> updateHibernateTasksRepository(@RequestBody @Valid TasksUpdateRequest request,
                                                                         String brand, String model, String serviceworkname,
                                                                         @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).orElse(null);
        HibernateTasks task = hibernateTasksRepository.findByCarsAndServiceWorkName(car,serviceworkname).orElse(null);
        request.setIdCar(car.getId());
        request.setId(task.getId());
        return new ResponseEntity<>(hibernateTasksRepository.save(conversionService.convert(request, HibernateTasks.class)), HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/task")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> deleteHibernateTasksRepository(String brand, String model, String serviceworkname,
                                                               @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).orElse(null);
        hibernateTasksRepository.deleteTasks(serviceworkname, car);
        return new ResponseEntity<>("task deleted", HttpStatus.OK);
    }

    /*View all tasks profile for one car*/
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
    @GetMapping("/task")
    public ResponseEntity<List<HibernateTasks>> viewTaskProfile(String brand, String model, /*String serviceworkname,*/
                                                          @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).orElse(null);
        List<HibernateTasks> task = hibernateTasksRepository.findByCars(car);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

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
    @GetMapping("/task/one")
    public ResponseEntity<HibernateTasks> viewOneTaskProfile(String brand, String model, String serviceworkname,
                                                                @ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        HibernateCars car = hibernateCarsRepository.findByCarBrandAndBrandModelAndUser(brand, model, user).orElse(null);
        HibernateTasks task = hibernateTasksRepository.findByCarsAndServiceWorkName(car,serviceworkname).orElse(null);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    /*View all cars profile*/
    @GetMapping("/task/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateCars>> getHibernatesTasksRepository(@ApiIgnore Principal principal) {
        String login = PrincipalUtil.getUsername(principal);
        HibernateUsers user = hibernateUsersRepository.findByLoginNotDeleted(login).orElse(null);
        return new ResponseEntity<>(hibernateCarsRepository.listOfTasks(user), HttpStatus.OK);
    }
}
