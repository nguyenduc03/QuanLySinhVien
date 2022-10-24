package com.example.qlsv.Services.Interface;

import com.example.qlsv.Entities.Student;
import com.example.qlsv.Entities.StudentInfo;
import com.example.qlsv.Models.*;

import java.util.Date;
import java.util.List;

public interface StudentService {
    List<Student> findAll();

    ResultInsertStudent insertOrUpdate(StudentInsertModel studentInsertModel);

    StudentInsertModel updateStudent(Student student, StudentInfo studentInfo);

    ResultModel deleteStudent(StudentInsertModel studentInput);

    ResultGetStudents findStudent(InfoFindStudent infoFindStudent);

    ResultGetStudents getStudents();
}
