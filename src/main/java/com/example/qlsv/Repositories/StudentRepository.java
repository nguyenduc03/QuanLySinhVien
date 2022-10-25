package com.example.qlsv.Repositories;

import com.example.qlsv.Entities.Student;
import com.example.qlsv.Entities.StudentInfo;
import com.example.qlsv.Models.StudentInsertModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Date;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query("select " +
            "new com.example.qlsv.Models.StudentInsertModel(v.studentId,v.studentName,v.studentCode ,u.address,u.dateOfBirth ,u.averageScore)  " +
            "from StudentInfo u, Student  v " +
            "where u.studentInfoID.studentId =  v.studentId " +
            "and(u.dateOfBirth = ?2 or v.studentCode = ?1 or v.studentName =?3 )")
    List<StudentInsertModel> findStudent(String code, Date date, String name);

}
