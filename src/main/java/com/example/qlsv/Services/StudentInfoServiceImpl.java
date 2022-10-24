package com.example.qlsv.Services;

import com.example.qlsv.Entities.StudentInfo;
import com.example.qlsv.Entities.StudentInfoID;
import com.example.qlsv.Repositories.StudentInfoRepository;
import com.example.qlsv.Services.Interface.StudentInfoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {
    StudentInfoRepository studentInfoRepository;

    public StudentInfoServiceImpl(StudentInfoRepository studentInfoRepository) {
        this.studentInfoRepository = studentInfoRepository;
    }

    @Override
    public List<StudentInfo> findAll() {
        return studentInfoRepository.findAll();
    }

    @Override
    public List<StudentInfo> findAllById(Iterable<StudentInfoID> studentInfoIDS) {
        return studentInfoRepository.findAllById(studentInfoIDS);
    }

    @Override
    public <S extends StudentInfo> List<S> saveAll(Iterable<S> entities) {
        return studentInfoRepository.saveAll(entities);
    }

    @Override
    public void flush() {
        studentInfoRepository.flush();
    }

    @Override
    public <S extends StudentInfo> S saveAndFlush(S entity) {

        return studentInfoRepository.saveAndFlush(entity);
    }

    @Override
    public StudentInfo getById(StudentInfoID studentInfoID) {
        return studentInfoRepository.getById(studentInfoID);
    }

    @Override
    public Optional<StudentInfo> findById(StudentInfoID studentInfoID) {
        return studentInfoRepository.findById(studentInfoID);
    }

    @Override
    public boolean existsById(StudentInfoID studentInfoID) {
        return studentInfoRepository.existsById(studentInfoID);
    }

    @Override
    public long count() {
        return studentInfoRepository.count();
    }

    @Override
    public void deleteById(StudentInfoID studentInfoID) {
        studentInfoRepository.deleteById(studentInfoID);
    }

    @Override
    public void delete(StudentInfo entity) {
        studentInfoRepository.delete(entity);
    }

    @Override
    public void deleteAll() {
        studentInfoRepository.deleteAll();
    }
}
