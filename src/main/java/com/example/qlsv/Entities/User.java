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
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int user_id;
    @Column(columnDefinition = "varchar(20) not null")
    String user_name;
    @Column(columnDefinition = "varchar(20) not null")
    String password;
}
