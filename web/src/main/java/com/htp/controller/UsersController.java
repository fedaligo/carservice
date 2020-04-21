package com.htp.controller;

import com.htp.controller.requests.users.UserCreateRequest;
import com.htp.controller.requests.users.UserUpdateRequest;
import com.htp.domain.hibernate.HibernateRoles;
import com.htp.domain.hibernate.HibernateUsers;
import com.htp.repository.springdata.HibernateRolesRepository;
import com.htp.repository.springdata.HibernateUsersRepository;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/admin/rest/users")
@RequiredArgsConstructor
public class UsersController {

    private final HibernateUsersRepository hibernateUsersRepository;

    private final HibernateRolesRepository hibernateRolesRepository;

    private final ConversionService conversionService;

    /*FindAll*/
    @GetMapping("/spring-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateUsers>> getHibernatesUsersRepository() {
        return new ResponseEntity<>( hibernateUsersRepository.findAll(), HttpStatus.OK);
    }

    /*FindById*/
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
    @GetMapping("/spring-data/{id}")
    public ResponseEntity<HibernateUsers> getHibernateUserByIdRepository(@ApiParam("User Path Id") @PathVariable Long id) {
        HibernateUsers user = hibernateUsersRepository.findById(id).orElse(null);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*Create */
    @PostMapping("/spring-data")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateUsers> createConvertedHibernateUser(@RequestBody @Valid UserCreateRequest request) {
        HibernateUsers savedConvertedUser = hibernateUsersRepository.saveAndFlush(conversionService.convert(request, HibernateUsers.class));
        hibernateRolesRepository.saveAndFlush(new HibernateRoles("ROLE_USER",savedConvertedUser));
        return new ResponseEntity<>( hibernateUsersRepository.saveAndFlush(savedConvertedUser), CREATED);
    }

    /*Update*/
    @ApiOperation(value = "Update user by userID")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful user update 1111111"),
            @ApiResponse(code = 400, message = "Invalid User ID supplied 111111"),
            @ApiResponse(code = 404, message = "User was not found 111111"),
            @ApiResponse(code = 500, message = "Server error, something wrong 1111111")
    })
    @PutMapping("/spring-data/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<HibernateUsers> updateHibernateUserRepository(@RequestBody @Valid UserUpdateRequest request) {
        return new ResponseEntity<>( hibernateUsersRepository.save(conversionService.convert(request, HibernateUsers.class)), HttpStatus.OK);
    }

    /*Delete*/
    @DeleteMapping("/spring-data/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @ResponseStatus(HttpStatus.OK)
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Long> deleteHibernateUserRepository(@PathVariable("id") Long userId) {
        hibernateUsersRepository.deleteById(userId);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
}
