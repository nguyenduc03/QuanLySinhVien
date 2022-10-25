package com.example.qlsv.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userId;
    @Size(min = 2, message = "user name should have at least 2 characters")
    @Column(columnDefinition = "varchar(20) not null")
    String userName;
    @NotEmpty
    @Size(min = 8, message = "password should have at least 8 characters")
    @Column(columnDefinition = "varchar(20) not null")
    String password;
}
