package com.example.demo.service.student;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CreateStudentDto;
import com.example.demo.dto.UpdateStudentDto;
import com.example.demo.domain.Student;

import java.util.List;

public interface StudentService {

     List<Student> getStudents();

    void addStudent(CreateStudentDto  createStudentDto);
    void updateStudent(UpdateStudentDto updateStudentDto);
    void deleteStudent(Long studentId);

    Student getStudentbyId(Long studentId);
}
