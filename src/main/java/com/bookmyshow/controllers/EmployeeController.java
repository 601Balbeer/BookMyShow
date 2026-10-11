package com.bookmyshow.controllers;

import com.bookmyshow.dtos.request.InviteEmployeeRequest;
import com.bookmyshow.enums.OperationType;
import com.bookmyshow.models.Employee;
import com.bookmyshow.services.AuthService;
import com.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emp")
public class EmployeeController {

    private AuthService authService;
    private UserService userService;

    @Autowired
    public EmployeeController(AuthService authService,
                              UserService userService){
        this.authService = authService;
        this.userService = userService;
    }


    @PostMapping("/invite-emp")
    public ResponseEntity inviteEmployee(
            @RequestBody InviteEmployeeRequest inviteEmployeeRequest,
            @RequestHeader String bmsToken
            ){
        Employee inviterEmp = authService.isEmployeeHavingAccess(bmsToken, OperationType.INVITE_EMPLOYEE.name());
        Employee invitee = userService.inviteEmployee(inviterEmp,inviteEmployeeRequest);
        return new ResponseEntity<>(invitee, HttpStatus.CREATED);
    }

}
