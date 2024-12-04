package com.example.demo.controller;


import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CreateStudentDto;
import com.example.demo.dto.UpdateStudentDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.service.student.StudentService;
import com.example.demo.domain.Student;
import com.example.demo.utils.Utils;
import org.apache.tomcat.util.http.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;


    @Autowired
    private StudentController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/")
    public ApiResponse<ArrayList<StudentDto>> getStudents() {

        ArrayList<StudentDto> students = studentService.getStudents().stream().map(
                student -> {
                    StudentDto studentDto = new StudentDto();
                    studentDto.setName(student.getName());
                    studentDto.setEmail(student.getEmail());
                    studentDto.setBirthday(student.getBirthday());
                    studentDto.setAge(student.getAge());
                    return studentDto;
                }


        ).collect(Collectors.toCollection(ArrayList::new));

        return Utils.wrapInApiResponse(students);

    }

    @GetMapping("/{studentId}")
    public ApiResponse<StudentDto> getStudent(@PathVariable("studentId") Long studentId){

        Student student = studentService.getStudentbyId(studentId);
        StudentDto studentDto = new ModelMapper().map(student, StudentDto.class);


        return Utils.wrapInApiResponse(studentDto);
    }

    @PostMapping
    private void addStudent(@RequestBody CreateStudentDto createStudentDto) {
        studentService.addStudent(createStudentDto);


    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping("{studentId}")
    public void updateStudent(UpdateStudentDto updateStudentDto) {
        studentService.updateStudent(updateStudentDto);
    }


}
