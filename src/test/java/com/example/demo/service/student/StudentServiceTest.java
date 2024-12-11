package com.example.demo.service.student;

import com.example.demo.domain.Student;
import com.example.demo.dto.CreateStudentDto;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.demo.util.GlobalConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class StudentServiceTest {

    @Mock
    private StudentRepository mockedStudentRepo;

    @InjectMocks
    private StudentService fixture;

    @BeforeEach
    void setUp() {
        mockedStudentRepo = Mockito.mock(StudentRepository.class);
        when(mockedStudentRepo.findAll()).thenReturn(List.of(getStudent()));
        when(mockedStudentRepo.findById(USER_ID)).thenReturn(Optional.of(getStudent()));
        when(mockedStudentRepo.findStudentByEmail(getStudent().getEmail())).thenReturn(Optional.of(getStudent()));
        when(mockedStudentRepo.save(Mockito.isA(Student.class))).thenReturn(getStudent());
        when(mockedStudentRepo.existsById(getStudent().getId())).thenReturn(true);
        doNothing().when(mockedStudentRepo).deleteById(any(Long.class));

        fixture  = new StudentServiceImpl(mockedStudentRepo);
    }

    @Test
    void getStudents() {

        List<Student> students = fixture.getStudents();

        assertEquals(1, students.size());
        assertEquals("TEST_USER", students.get(0).getName());


        verify(mockedStudentRepo, atMost(3)).findAll();
    }

    @Test
    void addStudent_ifEmailNotTaken() {

        CreateStudentDto createStudentDto = new CreateStudentDto("Josh", LocalDate.now(), "joh@josh.com", "Accra");

       Student student =  fixture.addStudent(createStudentDto);

        assertEquals("Josh", student.getName());
        assertEquals("Accra", student.getPlaceOfBirth());
        assertEquals(0, student.getAge());

        verify(mockedStudentRepo, atMost(2)).save(student);

    }
    @Test
    void addStudent_ifEmailTaken() {

        ModelMapper modelMapper = new ModelMapper();
        CreateStudentDto createStudentDto = modelMapper.map(getStudent(), CreateStudentDto.class);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            fixture.addStudent(createStudentDto);
        });

        assertEquals("Email Taken", exception.getMessage());

    }

    @Test
    void deleteStudent() {
        fixture.deleteStudent(getStudent().getId());

        verify(mockedStudentRepo, atMost(2)).findById(any(Long.class));
        verify(mockedStudentRepo, atMost(2)).deleteById(any(Long.class));

    }

    @Test
    void deleteStudentWhenIdDoesNotExist() {
        Long id = 33L;
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
             fixture.deleteStudent(id);
        });

        assertEquals("Student with id " + id + " not found", exception.getMessage());


        verify(mockedStudentRepo, atMost(2)).findById(any(Long.class));

    }

    @Test
    void getStudentByIdWhenIdExistsReturnsStudent() {

        Student student = fixture.getStudentbyId(getStudent().getId());

        assertNotNull(student);
        assertEquals("TEST_USER", student.getName());
        verify(mockedStudentRepo, times(1)).findById(getStudent().getId());
    }

    @Test
    void getStudentByIdWhenIdDoesNotExists() {

       IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            fixture.getStudentbyId(234L);
       });

       assertEquals("No Student found", exception.getMessage());
    }

//    @Test
//    void updateStudent() {
//
//
//    }

    private Student getStudent(){
        Student student = new Student();

        student.setId(USER_ID);
        student.setName(USER_NAME);
        student.setEmail(USER_EMAIL);
        student.setBirthday(USER_DOB);
        student.setPlaceOfBirth(USER_POB);

        return student;
    }

}