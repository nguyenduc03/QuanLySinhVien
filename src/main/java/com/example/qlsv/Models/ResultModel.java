package com.example.qlsv.Models;

import com.example.qlsv.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ResultModel {
    private boolean status;
    private String message;
    private User user;
    public ResultModel() {
        this.status = true;
    }
}


