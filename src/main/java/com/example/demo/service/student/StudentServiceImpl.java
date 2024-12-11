package com.example.demo.service.student;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.CreateStudentDto;
import com.example.demo.dto.UpdateStudentDto;
import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getStudents() {

        return new ArrayList<>(studentRepository.findAll());
    }

    @Override
    public Student addStudent(CreateStudentDto createStudentDto) {

        Optional<Student> studentOptional =  studentRepository.findStudentByEmail(createStudentDto.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email Taken");
        }

        Student student = new Student();
        student.setName(createStudentDto.getName());
        student.setEmail(createStudentDto.getEmail());
        student.setPlaceOfBirth(createStudentDto.getPlaceOfBirth());
        student.setBirthday(createStudentDto.getBirthday());

        studentRepository.save(student);

        System.out.println(createStudentDto);

        return student;
    }

    @Override
    public void deleteStudent(Long studentId) {
       boolean exists =  studentRepository.existsById(studentId);

       if (!exists) {
           throw new IllegalStateException("Student with id " + studentId + " not found");
       }

       studentRepository.deleteById(studentId);
    }

    @Override
    public Student getStudentbyId(Long studentId) {

        return studentRepository.findById(studentId).orElseThrow(() ->
            new IllegalStateException("No Student found")
        );
    }

    @Override
    @Transactional
    public void updateStudent(UpdateStudentDto updateStudentDto) {
        String name = updateStudentDto.getName();
        String email = updateStudentDto.getEmail();

        Student student =  studentRepository.findStudentsById(updateStudentDto.getId()).orElseThrow(
                () -> new IllegalStateException("Student with id" + updateStudentDto.getId() + "doesn't exist")
        );

       if(name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {
           student.setName(name);
       }

       if(email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {

           Optional<Student> studentOptional =  studentRepository.findStudentByEmail(email);
           if (studentOptional.isPresent()) {
               throw new IllegalStateException("Email Taken");
           }

           student.setEmail(email);
       }


    }
}
