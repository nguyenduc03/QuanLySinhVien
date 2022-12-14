package com.example.qlsv.Services;

import com.example.qlsv.Entities.User;
import com.example.qlsv.Models.*;
import com.example.qlsv.Repositories.UserRepository;
import com.example.qlsv.Services.Interface.UserService;
import com.example.qlsv.utils.Contants;
import com.example.qlsv.utils.Jwt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    @Autowired
    private Jwt jwt;
    @Autowired
    private AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static final int lengthPassword = 15;
    public static final int lengthUserName = 20;

    @Override
    public ResultLogin login(UserLogin userLogin) {
        ResultLogin resultLogin = new ResultLogin();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword()));
        resultLogin.setToken(jwt.generateToken(userLogin.getUserName()));
        if (resultLogin.getToken() == null)
            resultLogin.setMessage(Contants.loginFail);
        else {

            resultLogin.setMessage(Contants.loginSuccess);

            resultLogin.setStatus(true);
        }
        return resultLogin;
    }

    private boolean byteCheck(String stringInput) {
        char[] characters = stringInput.toCharArray();
        for (char c : characters)
            if (c > 255)
                return false;
        return true;
    }


    @Override
    public ResultModel Register(UserRegisterModel userRegister) {
        ResultModel result = checkLengthUser(userRegister.getUserName(), userRegister.getPassword());
        if (!result.isStatus())
            return result;
        else if (!userRegister.getPassword().equals(userRegister.getConfirmPassword())) {
            {
                result.setStatus(false);
                result.setMessage(Contants.confirmPassNotMatch);

            }
        } else if (!(userRepository.GetUserByName(userRegister.getUserName()) == null)) {
            {
                result.setStatus(false);
                result.setMessage(Contants.userNameExist);
            }
        } else {
            User user = new User();
            BeanUtils.copyProperties(userRegister, user);
            userRepository.save(user);
            result.setMessage(Contants.registerSuccess);
        }
        return result;
    }


    private ResultModel checkLengthUser(String userName, String password) {
        ResultModel result = new ResultModel();
        if (!checkLength(userName, lengthUserName)) {
            result.setStatus(false);
            result.setMessage(Contants.overSizeUserName);
        } else if (!checkLength(password, lengthPassword)) {
            result.setStatus(false);
            result.setMessage(Contants.overSizePassword);

        }
        if (!byteCheck(userName) || !byteCheck(password)) {
            result.setStatus(false);
            result.setMessage("Sai ki tu quy dinh (chi cho phep ki tu 1 byte)");
        }
        return result;
    }

    private boolean checkLength(String input, int length) {
        return input.length() <= length && input.length() != 0;
    }
}
