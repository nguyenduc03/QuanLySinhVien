package com.example.qlsv.Services;

import com.example.qlsv.Entities.Student;
import com.example.qlsv.Entities.StudentInfo;
import com.example.qlsv.Models.*;
import com.example.qlsv.Repositories.StudentInfoRepository;
import com.example.qlsv.Repositories.StudentRepository;
import com.example.qlsv.Services.Interface.StudentService;
import com.example.qlsv.utils.Contants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.*;

@Service
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;
    StudentInfoRepository studentInfoRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudentInfoRepository studentInfoRepository) {
        this.studentRepository = studentRepository;
        this.studentInfoRepository = studentInfoRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Transactional(rollbackFor = {SQLException.class})
//(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    @Override
    public ResultInsertStudent insertOrUpdate(StudentInsertModel studentInsertModel) {

        ResultInsertStudent resultInsertStudent = new ResultInsertStudent();
        Student student = studentInsertModel.toStudent();

        if (studentInsertModel.getStudentID() != null) {
            StudentInfo studentInfo = studentInsertModel.toStudentInfo();
            if (studentRepository.existsById(studentInsertModel.getStudentID())) {
                resultInsertStudent.setMessage(Contants.dataUpdateSuccess);
                resultInsertStudent.setDataStudent(updateStudent(student, studentInfo));
            } else {
                resultInsertStudent.setMessage(Contants.notFound);
                resultInsertStudent.setStatus(false);
            }
        } else if (checkRequiredStudent(studentInsertModel)) {
            resultInsertStudent.setMessage(Contants.insertSuccess);
            resultInsertStudent.setDataStudent(insert(student, studentInsertModel));
        } else {
            resultInsertStudent.setMessage(Contants.wrongData);
            resultInsertStudent.setStatus(false);
        }
        return resultInsertStudent;
    }

    private StudentInsertModel insert(Student student, StudentInsertModel studentInsertModel) {

        student.setStudentName(student.getStudentName().trim().toUpperCase());
        student.setStudentCode(student.getStudentCode().trim().toUpperCase());

        studentInsertModel.setStudentID(studentRepository.save(student).getStudentId());
        StudentInfo studentInfo = studentInsertModel.toStudentInfo();
        studentInfoRepository.add(studentInfo.getStudentInfoID().getStudentId(),
                studentInfo.getAddress(), studentInfo.getAverageScore(), studentInfo.getDateOfBirth());
        return new StudentInsertModel(student.getStudentId(),
                student.getStudentName(), student.getStudentCode(),
                studentInfo.getAddress(), studentInfo.getDateOfBirth(),
                studentInfo.getAverageScore());
    }

    private boolean checkRequiredStudent(StudentInsertModel studentInsertModel) {
        if (studentInsertModel.getStudentCode() == null || studentInsertModel.getStudentName() == null)
            return false;
        byte[] studentNameBytes = studentInsertModel.getStudentName().getBytes(StandardCharsets.UTF_8);
        if (studentNameBytes.length > 20 || studentNameBytes.length == 0)
            return false;
        byte[] studentCodeBytes = studentInsertModel.getStudentCode().getBytes(StandardCharsets.UTF_8);
        if (studentCodeBytes.length > 10 || studentCodeBytes.length == 0)
            return false;
        return true;
    }

    @Transactional(rollbackFor = {SQLException.class})
//(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)

    @Override
    public StudentInsertModel updateStudent(Student student, StudentInfo studentInfo) {
        Student oldStudent = studentRepository.getReferenceById(student.getStudentId());
        StudentInfo oldStudentInfo = studentInfoRepository.getStudentInfoByID(student.getStudentId());

        studentRepository.save(changeDataStudent(oldStudent, student));
        studentInfoRepository.save(changeDataStudentInfo(oldStudentInfo, studentInfo));
        return new StudentInsertModel(oldStudent.getStudentId(),
                oldStudent.getStudentName(), oldStudent.getStudentCode(),
                oldStudentInfo.getAddress(), oldStudentInfo.getDateOfBirth(),
                oldStudentInfo.getAverageScore());
    }

    private StudentInfo changeDataStudentInfo(StudentInfo oldStudentInfo, StudentInfo studentInfo) {
        if (studentInfo.getAddress() != null)
            oldStudentInfo.setAddress(studentInfo.getAddress());
        if (studentInfo.getDateOfBirth() != null)
            oldStudentInfo.setDateOfBirth(studentInfo.getDateOfBirth());
        if (studentInfo.getAverageScore() != null)
            oldStudentInfo.setAverageScore(studentInfo.getAverageScore());
        return oldStudentInfo;
    }

    private Student changeDataStudent(Student oldStudent, Student student) {
        if (student.getStudentName() != null)
            oldStudent.setStudentName(student.getStudentName().trim().toUpperCase());
        if (student.getStudentCode() != null)
            oldStudent.setStudentCode(student.getStudentCode());
        return oldStudent;
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
//(readOnly = false,isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)

    public ResultModel deleteStudent(StudentInsertModel studentInput) {
        ResultModel resultModel = new ResultModel();

        if (studentInput.getStudentID() == null) {
            resultModel.setMessage(Contants.wrongData);
            resultModel.setStatus(false);
            return resultModel;
        } else if (studentRepository.existsById(studentInput.getStudentID())) {
            studentRepository.deleteById(studentInput.getStudentID());
            StudentInfo studentInfo = studentInfoRepository.getStudentInfoByID(studentInput.getStudentID());
            studentInfoRepository.delete(studentInfo);
            resultModel.setMessage(Contants.deleteSuccess);
            resultModel.setStatus(true);
        } else {
            resultModel.setMessage(Contants.wrongData);
            resultModel.setStatus(false);
        }
        return resultModel;
    }

    @Override
    public ResultGetStudents findStudent(InfoFindStudent infoFindStudent) {
        ResultGetStudents result = new ResultGetStudents();
        if (infoFindStudent == null)
            result.setMessage(Contants.wrongData);
        else {
            if (infoFindStudent.getStudentName() != null)
                infoFindStudent.setStudentName(infoFindStudent.getStudentName().trim().toUpperCase());
            if (infoFindStudent.getDateOfBirth() == null)
                result.setMessage(Contants.invalidDate);
            else if (!checkValidDate(infoFindStudent.getDateOfBirth()))
                result.setMessage(Contants.invalidDate);
            result.setStudets(studentRepository.findStudent(infoFindStudent.getStudentCode(), infoFindStudent.getDateOfBirth(), infoFindStudent.getStudentName()));
            if (result.getStudets().size() == 0)
                result.setMessage(Contants.notFound);
            else {
                result.setMessage(Contants.findDataSuccess);
                result.setStatus(true);
            }
        }
        return result;
    }

    @Override
    public ResultGetStudents getStudents() {
        ResultGetStudents resultGetStudents = new ResultGetStudents();
        resultGetStudents.setStudets(findAllStudents());
        return resultGetStudents;
    }

    private List<StudentInsertModel> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentInsertModel> studentInsertModels = new ArrayList<>();
        for (Student student : students
        ) {
            StudentInfo studentInfo = studentInfoRepository.getStudentInfoByID(student.getStudentId());
            StudentInsertModel studentInsertModel = new StudentInsertModel(student.getStudentId(),
                    student.getStudentName(), student.getStudentCode(),
                    studentInfo.getAddress(), studentInfo.getDateOfBirth(),
                    studentInfo.getAverageScore());
            studentInsertModels.add(studentInsertModel);
        }
        return studentInsertModels;
    }


    private boolean checkValidDate(Date input) {
        Calendar calNow = Calendar.getInstance();
        Calendar calInput = Calendar.getInstance();
        calInput.setTime(input);
        int year = calInput.get(Calendar.YEAR);
        int month = calInput.get(Calendar.MONTH) + 1;
        if (year <= 0 || year > calNow.get(Calendar.YEAR))
            return false;
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth() >= calInput.get(Calendar.DATE);
    }

}
