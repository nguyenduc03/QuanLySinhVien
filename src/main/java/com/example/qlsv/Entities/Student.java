package com.example.qlsv.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer student_id;
    @Column(columnDefinition = "varchar(20) not null")
    String student_name;
    @Column(columnDefinition = "varchar(10) not null")
    String student_code;
}
