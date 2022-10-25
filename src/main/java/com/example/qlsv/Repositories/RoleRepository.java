package com.example.qlsv.Repositories;

import com.example.qlsv.Entities.RoleUser;
import com.example.qlsv.Entities.Student;
import com.example.qlsv.Models.StudentInsertModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface RoleRepository extends JpaRepository<RoleUser, Integer> {
    @Query("select u from RoleUser u where u.userId = ?1"
    )
    RoleUser getRoleUserBy(Integer idUser);

}
