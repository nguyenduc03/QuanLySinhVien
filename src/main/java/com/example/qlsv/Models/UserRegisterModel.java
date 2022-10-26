package com.example.qlsv.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class UserRegisterModel {

    @NotNull(message = "User name khong duoc de trong")
    @Size(max = 20, message = "user name khong duoc qua 20 ki tu")
    private String userName;

    @NotNull(message = "Password khong duoc de trong")
    @Size(max = 15, message = "password khong duoc qua 15 ki tu")
    private String password;
    
    @NotNull(message = "Xac nhan password khong duoc de trong")
    @Size(max = 15, message = "password khong duoc qua 15 ki tu")
    private String confirmPassword;
}
