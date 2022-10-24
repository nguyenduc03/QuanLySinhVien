package com.example.qlsv.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ResultModel {
    private boolean status;
    private String message;

    public ResultModel() {
        this.status = true;
    }
}


