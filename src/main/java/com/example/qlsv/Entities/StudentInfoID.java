package com.example.qlsv.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
@Embeddable
public class StudentInfoID implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int infoId;
    int studentId;

    public StudentInfoID(int studentId) {
        this.studentId = studentId;
    }
    public StudentInfoID (){}

}
