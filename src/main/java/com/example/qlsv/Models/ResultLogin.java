package com.example.qlsv.Models;

import com.example.qlsv.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResultLogin {
    private boolean status;
    private String message;
    private UserModel user;

    public ResultLogin() {
        this.status = false;
    }


}
