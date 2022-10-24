package com.example.qlsv.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResultGetStudents {
    boolean status;
    private String message;
    private List<StudentInsertModel> studets;

    public ResultGetStudents() {
        this.status = false;
    }
}
