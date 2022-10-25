package com.example.qlsv.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "role_user")
public class RoleUser {
    @Id
    @NotNull
    @Column(columnDefinition = "varchar(20) not null")
    String code;
    @NotNull
    @Column(columnDefinition = "varchar(25) not null")
    String nameOfRole;
    @NotNull
    Integer userId;

}
