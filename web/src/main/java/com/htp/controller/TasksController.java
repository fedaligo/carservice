package com.htp.controller;

import com.htp.controller.requests.tasks.TasksCreateRequest;
import com.htp.controller.requests.tasks.TasksUpdateRequest;
import com.htp.domain.Tasks;
import com.htp.domain.hibernate.HibernateTasks;
import com.htp.repository.jdbc.TasksDao;
import com.htp.repository.springdata.HibernateTasksRepository;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksDao tasksDao;

    private final HibernateTasksRepository hibernateTasksRepository;

    private final ConversionService conversionService;

    /*JDBC*/

    /*FindAll*/
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Tasks>> getTasks() {
        return new ResponseEntity<>(tasksDao.findAll(), HttpStatus.OK);
    }

    /*FindById*/
    @ApiOperation(value = "Get Tasks from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Tasks"),
            @ApiResponse(code = 400, message = "Invalid Tasks ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Tasks was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/getTasksById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tasks> getTaskById(@ApiParam("Task Path Id") @PathVariable Long id) {
        Tasks tasks = tasksDao.findById(id);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    /*Create*/
    @PostMapping("/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Tasks> createTasks(@RequestBody TasksCreateRequest request) {
        Tasks t = new Tasks();
        t.setServiceWorkName(request.getServiceWorkName());
        t.setNecessityOfEvacuation(request.getNecessityOfEvacuation());
        t.setWheelBrake(request.getWheelBrake());
        t.setIdCar(request.getId_car());
        t.setCreated(new Timestamp(new Date().getTime()));
        t.setDescription(request.getDescription());
        t.setLatitude(request.getLatitude());
        t.setLongitude(request.getLongitude());
        t.setLocalDescription(request.getLocalDescription());

        return new ResponseEntity<>(tasksDao.save(t), HttpStatus.OK);
    }

    /*Update*/
    @ApiOperation(value = "Update Task by userID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Task update 1111111"),
            @ApiResponse(code = 400, message = "Invalid Task ID supplied 111111"),
            @ApiResponse(code = 404, message = "Task was not found 111111"),
            @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
    })
       /* @ApiImplicitParams({
                @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
        })*/
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Tasks> updateTask(@PathVariable("id") Long id,
                                            @RequestBody TasksCreateRequest request) {
        Tasks t = tasksDao.findById(id);
        t.setServiceWorkName(request.getServiceWorkName());
        t.setNecessityOfEvacuation(request.getNecessityOfEvacuation());
        t.setWheelBrake(request.getWheelBrake());
        t.setIdCar(request.getId_car());
        t.setCreated(new Timestamp(new Date().getTime()));
        t.setDescription(request.getDescription());
        t.setLatitude(request.getLatitude());
        t.setLongitude(request.getLongitude());
        t.setLocalDescription(request.getLocalDescription());

        return new ResponseEntity<>(tasksDao.updateOne(t), HttpStatus.OK);
    }

    /*Delete*/

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteTask(@PathVariable("id") Long id) {
        tasksDao.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }



    /*SPRING DATA*/

    /*FindAll*/
    @GetMapping("/spring-data/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateTasks>> getHibernatesTasksRepository() {
        return new ResponseEntity<>(hibernateTasksRepository.findAll(), HttpStatus.OK);
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
                            "Multiple sort criteria are supported.")
    })
    @GetMapping("/spring-data/all(pageable)")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<HibernateTasks>> getTasksSpringData(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(hibernateTasksRepository.findAll(pageable), HttpStatus.OK);
    }

    /*FindById*/
    @ApiOperation(value = "Get from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting Tasks"),
            @ApiResponse(code = 400, message = "Invalid Tasks ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "Tasks was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/spring-data/getTasksById/{id}", method = RequestMethod.GET)
    public ResponseEntity<HibernateTasks> getHibernateTasksByIdRepository(@ApiParam("Path Id") @PathVariable Long id) {
        HibernateTasks t = hibernateTasksRepository.findById(id).orElse(null);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    /*Create */
    @PostMapping("/spring-data/create(converted)")
    @Transactional
    public ResponseEntity<HibernateTasks> createConvertedHibernateTasks(@RequestBody @Valid TasksCreateRequest request) {
        //HibernateTasks savedConvertedTasks = conversionService.convert(request, HibernateTasks.class);
        return new ResponseEntity<>(hibernateTasksRepository.saveAndFlush(conversionService.convert(request, HibernateTasks.class)), CREATED);
    }

    /*Update*/
    @ApiOperation(value = "Update Tasks by ID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful Tasks update"),
            @ApiResponse(code = 400, message = "Invalid Tasks ID supplied"),
            @ApiResponse(code = 404, message = "Tasks was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PutMapping("/spring-data/update(converted)/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateTasks> updateHibernateTasksRepository(@RequestBody @Valid TasksUpdateRequest request) {
        return new ResponseEntity<>(hibernateTasksRepository.save(conversionService.convert(request, HibernateTasks.class)), HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/spring-data/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteHibernateTasksRepository(@PathVariable("id") Long id) {
        hibernateTasksRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
