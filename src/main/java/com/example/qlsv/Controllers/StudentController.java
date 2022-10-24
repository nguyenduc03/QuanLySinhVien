package com.example.qlsv.Controllers;

import com.example.qlsv.Entities.Student;
import com.example.qlsv.Models.*;
import com.example.qlsv.Services.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("API/Student")
public class StudentController {
    final
    StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/GetAll")
    public List<Student> getAllStudent() {
        return studentService.findAll();
    }

    @RequestMapping("/getStudents")
    public ResultGetStudents getStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/insertOrUpdateStudent")
    public ResponseEntity<Object> addStudent(@RequestBody StudentInsertModel studentInput) {
        ResultInsertStudent result = studentService.insertOrUpdate(studentInput);
        if (!result.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        else
            return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/deleteStudent")
    public ResponseEntity<ResultModel> deleteStudent(@RequestBody StudentInsertModel studentInput) {
        ResultModel result = studentService.deleteStudent(studentInput);
        if (!result.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        else
            return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/findStudent")
    public ResponseEntity<Object> findStudent(@RequestBody InfoFindStudent infoFindStudent) {

        ResultGetStudents result = studentService.findStudent(infoFindStudent);
        if (!result.isStatus())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        else
            return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
