package com.example.demo.service.student;

import com.example.demo.domain.Student;
import com.example.demo.dto.CreateStudentDto;
import com.example.demo.dto.UpdateStudentDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.example.demo.util.GlobalConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceIT {

    @Autowired
    private StudentService fixture;
    private Student savedStudent;


    @BeforeEach
    void setUp() {
        System.out.println("Runs before each test.");
        savedStudent = fixture.addStudent(getStudent());

    }

    @AfterEach
    void tearDown() {
        System.out.println("Runs after each test.");

        fixture.deleteStudent(savedStudent.getId());
    }

    @Test
    void getStudents() {

        List<Student> all = fixture.getStudents();

        assertEquals(all.get(2).getName(), savedStudent.getName());
    }

    @Test
    void addStudent() {

        CreateStudentDto stud = new CreateStudentDto();
        stud.setName(USER_NAME);
        stud.setEmail("josh@res.com");
        stud.setBirthday(USER_DOB);
        stud.setPlaceOfBirth(USER_POB);

        Student created = fixture.addStudent(stud);


        assertEquals(USER_NAME, created.getName());

    }

    @Test
    void addStudentIfEmailExists() {

        CreateStudentDto stud = new CreateStudentDto();
        stud.setName(USER_NAME);
        stud.setEmail(USER_EMAIL);
        stud.setBirthday(USER_DOB);
        stud.setPlaceOfBirth(USER_POB);


        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                fixture.addStudent(stud)
        );


        assertEquals("Email Taken", exception.getMessage());

    }

    @Test
    void updateStudent() {

        UpdateStudentDto upStud = new UpdateStudentDto();

        upStud.setId(savedStudent.getId());
        upStud.setName("Joshiro");
        upStud.setEmail("new@New.com");

        fixture.updateStudent(upStud);
        Student updated = fixture.getStudentbyId(savedStudent.getId());

        assertEquals(updated.getName(), upStud.getName());

    }

    @Test
    void updateStudentIdNotExist() {

        UpdateStudentDto upStud = new UpdateStudentDto();

        Long id = 89L;

        upStud.setId(id);
        upStud.setName("Joshiro");
        upStud.setEmail("new@New.com");


       IllegalStateException exception = assertThrows(IllegalStateException.class, () -> fixture.updateStudent(upStud));


        assertEquals("Student with id" + id + "doesn't exist", exception.getMessage());

    }

    @Test
    void updateStudentWithTakenEmail() {

        UpdateStudentDto upStud = new UpdateStudentDto();

        upStud.setId(savedStudent.getId());
        upStud.setEmail("Mamush");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> fixture.updateStudent(upStud));

        assertEquals("Email Taken", exception.getMessage());

    }

    @Test
    void deleteStudentIfFound() {

        fixture.deleteStudent(1L);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            fixture.getStudentbyId(1L);

        });

        assertEquals("No Student found", exception.getMessage());

    }

    @Test
    void deleteStudentIfNotFound() {
        Long studentId = 46L;


        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            fixture.deleteStudent(studentId);
        });

        assertEquals("Student with id " + studentId + " not found", exception.getMessage());

    }

    @Test
    void getStudentByIdIfFound() {

        Student found = fixture.getStudentbyId(savedStudent.getId());

        assertEquals(savedStudent.getName(), found.getName());


    }

    @Test
    void getStudentByIdIfNotFound() {

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            fixture.getStudentbyId(46L);

        });

        assertEquals("No Student found", exception.getMessage());
    }


    private CreateStudentDto getStudent() {

        CreateStudentDto stud = new CreateStudentDto();
        stud.setName(USER_NAME);
        stud.setEmail(USER_EMAIL);
        stud.setBirthday(USER_DOB);
        stud.setPlaceOfBirth(USER_POB);

        return stud;

    }

}