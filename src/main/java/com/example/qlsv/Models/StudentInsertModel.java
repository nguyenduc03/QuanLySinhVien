package com.example.qlsv.Models;

import com.example.qlsv.Entities.Student;
import com.example.qlsv.Entities.StudentInfo;
import com.example.qlsv.Entities.StudentInfoID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInsertModel {
    private Integer studentID;
    private String studentName;
    private String studentCode;
    private String address;
    private Date dateOfBirth;
    private Double averageScore;

    public Student toStudent() {
        return new Student(studentID, studentName, studentCode);
    }

    public StudentInfo toStudentInfo() {
        return new StudentInfo(new StudentInfoID(studentID), address, dateOfBirth, averageScore);
    }
}

