package com.htp.controller;

import com.htp.controller.requests.tasks.TasksCreateRequest;
import com.htp.controller.requests.tasks.TasksUpdateRequest;
import com.htp.domain.hibernate.HibernateTasks;
import com.htp.repository.springdata.HibernateTasksRepository;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiParam;
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
@RequestMapping(value = "/admin/rest/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final HibernateTasksRepository hibernateTasksRepository;

    private final ConversionService conversionService;

    /*FindAll*/
    @GetMapping("/spring-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateTasks>> getHibernatesTasksRepository() {
        return new ResponseEntity<>(hibernateTasksRepository.findAll(), HttpStatus.OK);
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
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping(value = "/spring-data/{id}")
    public ResponseEntity<HibernateTasks> getHibernateTasksByIdRepository(@ApiParam("Path Id") @PathVariable Long id) {
        HibernateTasks t = hibernateTasksRepository.findById(id).orElse(null);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    /*Create */
    @PostMapping("/spring-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTasks> createConvertedHibernateTasks(@RequestBody @Valid TasksCreateRequest request) {
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
    @PutMapping("/spring-data/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateTasks> updateHibernateTasksRepository(@RequestBody @Valid TasksUpdateRequest request) {
        return new ResponseEntity<>(hibernateTasksRepository.save(conversionService.convert(request, HibernateTasks.class)), HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/spring-data/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Long> deleteHibernateTasksRepository(@PathVariable("id") Long id) {
        hibernateTasksRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
