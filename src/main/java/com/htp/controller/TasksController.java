package com.htp.controller;

import com.htp.controller.requests.TasksCreateRequest;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.repository.hibernate.impl.HibernateTasksDaoImpl;
import com.htp.repository.jdbc.TasksDao;
import com.htp.domain.Tasks;
import com.htp.domain.hibernate.HibernateTasks;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/tasks")
@RequiredArgsConstructor
public class TasksController {

        private final TasksDao tasksDao;

        private final HibernateTasksDaoImpl hibernateTasksDao;

        @GetMapping("/all")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<Tasks>> getTasks() {
            return new ResponseEntity<>(tasksDao.findAll(), HttpStatus.OK);
        }

        @GetMapping("/hibernate/all")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<List<HibernateTasks>> getHibernateTasks() {
            return new ResponseEntity<>(hibernateTasksDao.findAll(), HttpStatus.OK);
        }

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

        @ApiOperation(value = "Get Tasks from server by id")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful getting Tasks"),
                @ApiResponse(code = 400, message = "Invalid Tasks ID supplied"),
                @ApiResponse(code = 401, message = "Lol kek"),
                @ApiResponse(code = 404, message = "Tasks was not found"),
                @ApiResponse(code = 500, message = "Server error, something wrong")
        })
        @RequestMapping(value = "/hibernate/getTaskById/{id}", method = RequestMethod.GET)
        public ResponseEntity<HibernateTasks> getHibernateTaskById(@ApiParam("Task Path Id") @PathVariable Long id) {
            HibernateTasks tasks = hibernateTasksDao.findById(id);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }


        @PostMapping("/create")
        @Transactional
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<Tasks> createTasks(@RequestBody TasksCreateRequest request) {
            Tasks t = new Tasks();
            //userID is empty - will be generated by DB
            t.setServiceWorkName(request.getServiceWorkName());
            t.setNecessityOfEvacuation(request.getNecessityOfEvacuation());
            t.setWheelBrake(request.getWheelBrake());
            t.setCreated(new Timestamp(new Date().getTime()));
            t.setDescription(request.getDescription());
            t.setLatitude(request.getLatitude());
            t.setLongitude(request.getLongitude());
            t.setLocalDescription(request.getLocalDescription());

            return new ResponseEntity<>(tasksDao.save(t), HttpStatus.OK);
        }

        @PostMapping("/hibernate/create")
        @Transactional
        @ResponseStatus(HttpStatus.CREATED)
        public ResponseEntity<HibernateTasks> createHibernateTasks(@RequestBody TasksCreateRequest request) {
            HibernateTasks t = new HibernateTasks();
            //userID is empty - will be generated by DB
            t.setServiceWorkName(request.getServiceWorkName());
            t.setNecessityOfEvacuation(request.getNecessityOfEvacuation());
            t.setWheelBrake(request.getWheelBrake());
            t.setCreated(new Timestamp(new Date().getTime()));
            t.setDescription(request.getDescription());
            t.setLatitude(request.getLatitude());
            t.setLongitude(request.getLongitude());
            t.setLocalDescription(request.getLocalDescription());

            return new ResponseEntity<>(hibernateTasksDao.save(t), HttpStatus.OK);
        }

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
            t.setCreated(new Timestamp(new Date().getTime()));
            t.setDescription(request.getDescription());
            t.setLatitude(request.getLatitude());
            t.setLongitude(request.getLongitude());
            t.setLocalDescription(request.getLocalDescription());

            return new ResponseEntity<>(tasksDao.updateOne(t), HttpStatus.OK);
        }

        @ApiOperation(value = "Update Task by userID")
        @ApiResponses({
                @ApiResponse(code = 200, message = "Successful Task update 1111111"),
                @ApiResponse(code = 400, message = "Invalid Task ID supplied 111111"),
                @ApiResponse(code = 404, message = "Task was not found 111111"),
                @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
        })
        @RequestMapping(value = "/hibernate/update/{id}", method = RequestMethod.PUT)
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<HibernateTasks> updateHibernateTask(@ApiParam(value = "Task ID", required = false) @PathVariable("id") Long id,
                                                                  @RequestBody TasksCreateRequest request) {

            HibernateTasks t = hibernateTasksDao.findById(id);
            t.setServiceWorkName(request.getServiceWorkName());
            t.setNecessityOfEvacuation(request.getNecessityOfEvacuation());
            t.setWheelBrake(request.getWheelBrake());
            t.setCreated(new Timestamp(new Date().getTime()));
            t.setDescription(request.getDescription());
            t.setLatitude(request.getLatitude());
            t.setLongitude(request.getLongitude());
            t.setLocalDescription(request.getLocalDescription());

            return new ResponseEntity<>(hibernateTasksDao.updateOne(t), HttpStatus.OK);
        }

        @DeleteMapping("/delete/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<Long> deleteTask(@PathVariable("id") Long id) {
            tasksDao.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }

        @DeleteMapping("/hibernate/delete/{id}")
        @ResponseStatus(HttpStatus.OK)
        public ResponseEntity<Long> deleteHibernateTask(@PathVariable("id") Long id) {
            hibernateTasksDao.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }







    /*PREVIOUS VARIANT


    private final TasksDao tasksDao;

    public TasksController(TasksDao tasksDao) {
        this.tasksDao = tasksDao;
    }

    //http://localhost:8081/tracking/search?cost=100
    *//*@RequestMapping(value = "/tracking/search", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello(@RequestParam("cost") Long query, ModelMap model) {
        List<Tracking> search = trackingDao.trackingByHigherCost(query);
        model.addAttribute("bycost",
                StringUtils.join(search.stream().map(Tracking::toString).collect(Collectors.toList()), ","));
        return "hello";
    }*//*

    *//*GET localhost:8081/tracking/all*//*
    @RequestMapping(value = "/tasks/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printAllTasks(ModelMap model) {
        model.addAttribute("tasksreadall",
                tasksDao.findAll().stream()
                        .map(Tasks::toString)
                        .collect(Collectors.joining(","))
        );
        return "hello";
    }*/
}
