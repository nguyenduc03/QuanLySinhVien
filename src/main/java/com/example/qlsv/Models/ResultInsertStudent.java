package com.example.qlsv.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ResultInsertStudent {
    private boolean status;
    private String message;
    private StudentInsertModel dataStudent;
    public ResultInsertStudent(){
        this.status = true;
    }
}
