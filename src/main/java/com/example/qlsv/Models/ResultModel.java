package com.example.qlsv.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ResultModel {
    private boolean status;
    private String message;
    private Data data;

    public class Data {

    }

    public ResultModel() {
        this.status = true;
    }
}


