package com.example.qlsv.Services.Interface;

import com.example.qlsv.Models.ResultLogin;
import com.example.qlsv.Models.ResultModel;
import com.example.qlsv.Models.UserLogin;
import com.example.qlsv.Models.UserRegisterModel;

public interface UserService {

    ResultLogin login(UserLogin userLogin);
    ResultModel Register(UserRegisterModel userRegister);
}
