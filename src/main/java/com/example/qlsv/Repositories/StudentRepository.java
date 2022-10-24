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
            "new com.example.qlsv.Models.StudentInsertModel(v.student_id,v.student_name,v.student_code ,u.address,u.date_of_birth ,u.average_score)  " +
            "from StudentInfo u, Student  v " +
            "where u.studentInfoID.student_id =  v.student_id " +
            "and(u.date_of_birth = ?2 or v.student_code = ?1 or v.student_name =?3 )")
    List<StudentInsertModel> findStudent(String code, Date date, String name);

}
