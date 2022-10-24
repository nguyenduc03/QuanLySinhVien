package com.example.qlsv.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_info")
public class StudentInfo implements Serializable {
    @EmbeddedId
    StudentInfoID studentInfoID;

    @Column(columnDefinition = "varchar(255) ")
    String address;
    Date date_of_birth;
    Double average_score;
}
