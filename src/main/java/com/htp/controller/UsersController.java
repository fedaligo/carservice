package com.htp.controller;

import com.htp.controller.requests.UserCreateRequest;
import com.htp.controller.requests.UserUpdateRequest;
import com.htp.domain.Gender;
import com.htp.domain.Roles;
import com.htp.domain.Users;
import com.htp.domain.hibernate.HibernateRoles;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.repository.hibernate.HibernateRolesDao;
import com.htp.repository.hibernate.impl.HibernateUsersDaoImpl;
import com.htp.repository.jdbc.RolesDao;
import com.htp.repository.jdbc.UsersDao;
import com.htp.repository.springdata.HibernateRolesRepository;
import com.htp.repository.springdata.HibernateUsersRepository;
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
@RequestMapping(value = "/rest/users")
@RequiredArgsConstructor
public class UsersController {

    private static final Gender gender = Gender.NOT_SELECTED;
    private final UsersDao userDao;

    //@Autowired
    private final HibernateUsersRepository hibernateUsersRepository;

    private final HibernateRolesRepository hibernateRolesRepository;

    private final HibernateUsersDaoImpl hibernateUserDao;

    private final ConversionService conversionService;

    /*@Autowired
    public void setHibernateUsersRepository(HibernateUsersRepository hibernateUsersRepository){
        this.hibernateUsersRepository = hibernateUsersRepository;
    }*/



    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Users>> getUsers() {
        return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/hibernate/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateUsers>> getHibernateUsers() {
        return new ResponseEntity<>(hibernateUserDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/spring-data/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateUsers>> getHibernatesUsersRepository() {
        return new ResponseEntity<>( hibernateUsersRepository.findAll(), HttpStatus.OK);
    }

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

    @ApiOperation(value = "Get user from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting user"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    public ResponseEntity<Users> getUserById(@ApiParam("User Path Id") @PathVariable Long id) {
        Users user = userDao.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Get user from server by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful getting user"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 401, message = "Lol kek"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/hibernate/getUserById/{id}", method = RequestMethod.GET)
    public ResponseEntity<HibernateUsers> getHibernateUserById(@ApiParam("User Path Id") @PathVariable Long id) {
        HibernateUsers user = hibernateUserDao.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

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


    private final RolesDao rolesDao;
    private final HibernateRolesDao hibernateRolesDao;

    @PostMapping("/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Users> createUser(@RequestBody UserCreateRequest request) {
        Users user = new Users();
        //userID is empty - will be generated by DB
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        user.setEMail(request.getEMail());
        user.setPhNumberUser(request.getPhNumberUser());

        Users savedUser = userDao.save(user);
        rolesDao.save(new Roles(savedUser.getId(), "ROLE_USER"));

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping("/hibernate/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateUsers> createHibernateUser(@RequestBody UserCreateRequest request) {
        HibernateUsers user = new HibernateUsers();
        //userID is empty - will be generated by DB
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setIs_deleted(false);
        user.setE_mail(request.getEMail());
        user.setPhone_number_user(request.getPhNumberUser());

        /*HibernateUsers savedUser = hibernateUserDao.save(user);
        hibernateRolesDao.save(new HibernateRoles("ROLE_USER",savedUser));*/

        HibernateUsers savedUser = hibernateUserDao.save(user);
        rolesDao.save(new Roles(savedUser.getUserId(), "ROLE_USER"));

        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @PostMapping("/spring-data/create(converted)")
    @Transactional
    public ResponseEntity<HibernateUsers> createConvertedHibernateUser(@RequestBody @Valid UserCreateRequest request) {
        HibernateUsers savedConvertedUser = hibernateUsersRepository.saveAndFlush(conversionService.convert(request, HibernateUsers.class));
        hibernateRolesRepository.saveAndFlush(new HibernateRoles("ROLE_USER",savedConvertedUser));
        return new ResponseEntity<>( hibernateUsersRepository.saveAndFlush(savedConvertedUser), CREATED);
    }

    @ApiOperation(value = "Update user by userID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update 1111111"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied 111111"),
            @ApiResponse(code = 404, message = "User was not found 111111"),
            @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
    })
   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })*/
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Users> updateUser(@PathVariable("id") Long userId,
                                           @RequestBody UserCreateRequest request) {
        Users user = userDao.findById(userId);
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        //user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setIsDeleted(false);
        user.setEMail(request.getEMail());
        user.setPhNumberUser(request.getPhNumberUser());

        return new ResponseEntity<>(userDao.updateOne(user), HttpStatus.OK);
    }

    @ApiOperation(value = "Update user by userID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied"),
            @ApiResponse(code = 404, message = "User was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @RequestMapping(value = "/hibernate/update/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateUsers> updateHibernateUser(@ApiParam(value = "User ID", required = false) @PathVariable("id") Long userId,
                                                   @RequestBody UserCreateRequest request) {

        HibernateUsers user = hibernateUserDao.findById(userId);
        //userID is empty - will be generated by DB
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        //user.setCreated(new Timestamp(new Date().getTime()));
        user.setChanged(new Timestamp(new Date().getTime()));
        user.setIs_deleted(false);
        user.setE_mail(request.getEMail());
        user.setPhone_number_user(request.getPhNumberUser());

        return new ResponseEntity<>(hibernateUserDao.updateOne(user), HttpStatus.OK);
    }

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




    /*@ApiOperation(value = "Search user by query")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update"), //OK
            @ApiResponse(code = 400, message = "Invalid query supplied"), //Invalid request
            @ApiResponse(code = 404, message = "User was not found"), //Resourse not found
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "limit of users", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "offset", value = "start node of users", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "query", value = "search query", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Users>> searchUsers(@ApiIgnore @ModelAttribute SearchCriteria search) {
        List<Users> searchResult = userDao.search(
                                                    search.getQuery(),
                                                    search.getLimit(),
                                                    search.getOffset()
                                                    );
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }*/

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteUser(@PathVariable("id") Long userId) {
        userDao.deleteById(userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }


    @DeleteMapping("/spring-data/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteHibernateUserRepository(@PathVariable("id") Long userId) {
        hibernateUsersRepository.deleteById(userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    /*@DeleteMapping("/hibernate/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteHibernateUser(@PathVariable("id") Long userId) {
        hibernateUserDao.deleteById(userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }*/


/* PREVIOUS VARIANT


    private final UsersDao userDao;

    public UsersController(UsersDao userDao) {
        this.userDao = userDao;
    }

    *//*GET localhost:8080/HelloWeb/users/create*//*
    @RequestMapping(value = "/users/deleted", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printDeletedUsers(ModelMap model) {
        model.addAttribute("usersdeleted",
                userDao.findAllDeletedUsers().stream()
                        .map(Users::toString)
                        .collect(Collectors.joining("---"))
        );
        return "hello";
    }

    *//*GET localhost:8080/HelloWeb/users/all*//*
    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printAllUsers(ModelMap model) {
        model.addAttribute("usersreadall",
                userDao.findAll().stream()
                        .map(Users::toString)
                        .collect(Collectors.joining(","))
        );
        return "hello";}*/



}
