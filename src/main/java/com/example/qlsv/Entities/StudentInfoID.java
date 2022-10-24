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
    int info_id;
    int student_id;

    public StudentInfoID(int student_id) {
        this.student_id = student_id;
    }
    public StudentInfoID (){}

}
