package com.example.qlsv.Controllers;

import com.example.qlsv.Models.*;
import com.example.qlsv.Services.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("API/User")
public class UserController {
    final
    UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/Login")
    public ResultLogin login(@RequestBody UserLogin userLogin) {
        return userService.login(userLogin);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRegisterModel studentInput) {
        ResultModel result = userService.Register(studentInput);
        if (result.isStatus())
            return ResponseEntity.status(HttpStatus.OK).body(result);
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


}
