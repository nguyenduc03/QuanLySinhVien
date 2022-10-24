package com.example.qlsv.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterModel {
    private String user_name;
    private String password;
    private String confirmPassword;
}
