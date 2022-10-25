package com.example.qlsv.Controllers;

import com.example.qlsv.Entities.User;
import com.example.qlsv.Models.*;
import com.example.qlsv.Services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("API/User")
public class UserController {
    final
    UserServiceImpl userService;


    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/Login")
    public ResultLogin login(@Valid @RequestBody UserLogin userLogin) {
        return userService.login(userLogin);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@Valid @RequestBody  UserRegisterModel studentInput) {
        ResultModel result = userService.Register(studentInput);
        if (result.isStatus())
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }



}
