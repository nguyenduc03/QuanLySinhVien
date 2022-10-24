package com.example.qlsv.Repositories;

import com.example.qlsv.Entities.StudentInfo;
import com.example.qlsv.Entities.StudentInfoID;
import com.example.qlsv.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, StudentInfoID> {
//    @PersistenceContext
//     EntityManager entityManager ;

    @Modifying
    @Query(value = "insert into student_info (student_id,address,average_score,date_of_birth) VALUES (:studentID,:address,:averageScore,:dateOfBirth)", nativeQuery = true)
    @Transactional
    void add(@Param("studentID") int studentID, @Param("address") String address, @Param("averageScore") double averageScore, @Param("dateOfBirth") Date dateOfBirth);


    @Query("select u from StudentInfo u where u.studentInfoID.student_id = ?1")
    StudentInfo getStudentInfoByID(int studentID);

}
