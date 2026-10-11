package com.bookmyshow.controllers;

import com.bookmyshow.dtos.request.UserLoginRequest;
import com.bookmyshow.dtos.response.LoginSuccessResponse;
import com.bookmyshow.exceptions.BMSUnauthorizedException;
import com.bookmyshow.models.User;
import com.bookmyshow.services.AuthService;
import com.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;
    private AuthService authService;

    @Autowired
    public UserController(UserService userService,
                          AuthService authService){
        this.userService = userService;
        this.authService = authService;
    }


    @PostMapping("/authenticate")
    public ResponseEntity authenticateUser(
            @RequestBody UserLoginRequest userLoginRequest
            ){
        User user = userService.authenticateUser(userLoginRequest);
        LoginSuccessResponse loginSuccessResponse = authService.generateLoginSuccessToken(user);
        return new ResponseEntity(loginSuccessResponse, HttpStatus.OK);
    }

}
