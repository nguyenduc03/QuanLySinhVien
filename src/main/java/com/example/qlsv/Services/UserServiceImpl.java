package com.example.qlsv.Services;

import com.example.qlsv.Entities.User;
import com.example.qlsv.Models.*;
import com.example.qlsv.Repositories.UserRepository;
import com.example.qlsv.Services.Interface.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static final int lengthPassword = 15;
    public static final int lengthUserName = 20;

    @Override
    public ResultLogin login(UserLogin userLogin) {
        ResultLogin resultLogin = new ResultLogin();
        ResultModel temp = checkLengthUser(userLogin.getUserName(), userLogin.getPassword());
        resultLogin.setStatus(temp.isStatus());
        resultLogin.setMessage(temp.getMessage());
        if (!resultLogin.isStatus())
            return resultLogin;
        else {
            UserModel userModel = new UserModel();
            User user = userRepository.Login(userLogin.getUserName(), userLogin.getPassword());

            userModel.setUserName(user.getUserName());
            userModel.setPassword(user.getPassword());

            resultLogin.setUser(userModel);
            if (resultLogin.getUser() == null) {
                resultLogin.setMessage("Tai khoan hoac mat khau sai");
            } else {
                resultLogin.setMessage("Dang nhap thanh cong");
                resultLogin.setStatus(true);
            }
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
                result.setMessage("Mat khau xac nhan khong giong");
            }
        } else if (!(userRepository.GetUserByName(userRegister.getUserName()) == null)) {
            {
                result.setStatus(false);
                result.setMessage("User name da ton tai");
            }
        } else {
            User user = new User();
            BeanUtils.copyProperties(userRegister, user);
            userRepository.save(user);
            result.setMessage("Tao thanh cong user ");
        }
        return result;
    }

    @Valid
    private ResultModel checkLengthUser(String userName, String password) {
        ResultModel result = new ResultModel();
        if (!checkLength(userName, lengthUserName)) {
            result.setStatus(false);
            result.setMessage("Do dai user name khong hop le ");
        } else if (!checkLength(password, lengthPassword)) {
            result.setStatus(false);
            result.setMessage("Do dai mat khau khong cho phep");
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

    private String hashMD5(String password) {
        byte[] bytesOfMessage = new byte[0];
        try {
            bytesOfMessage = password.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] bytesOfDigest = md.digest(bytesOfMessage);
        String hash = DatatypeConverter.printHexBinary(bytesOfDigest).toLowerCase();
        System.out.println(hash);
        return hash;
    }

}
