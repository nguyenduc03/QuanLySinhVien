package com.example.qlsv.Services.Interface;

import com.example.qlsv.Entities.StudentInfo;
import com.example.qlsv.Entities.StudentInfoID;

import java.util.List;
import java.util.Optional;

public interface StudentInfoService {

    List<StudentInfo> findAll();

    List<StudentInfo> findAllById(Iterable<StudentInfoID> studentInfoIDS);

    <S extends StudentInfo> List<S> saveAll(Iterable<S> entities);

    void flush();

    <S extends StudentInfo> S saveAndFlush(S entity);

    StudentInfo getById(StudentInfoID studentInfoID);

    Optional<StudentInfo> findById(StudentInfoID studentInfoID);

    boolean existsById(StudentInfoID studentInfoID);

    long count();

    void deleteById(StudentInfoID studentInfoID);

    void delete(StudentInfo entity);

    void deleteAll();
}
