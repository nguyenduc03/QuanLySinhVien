package com.example.qlsv.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoFindStudent {
    String studentCode;
    String studentName;
    Date dateOfBirth;
}
