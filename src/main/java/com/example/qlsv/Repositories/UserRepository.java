package com.example.qlsv.Repositories;

import com.example.qlsv.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.userName = ?1 and u.password=?2")
    User Login(String user_name, String password);

    @Query("select u from User u where u.userName = ?1 ")
    User GetUserByName(String user_name);


}
