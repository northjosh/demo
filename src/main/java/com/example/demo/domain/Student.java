package com.example.demo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;


@Entity
@Table
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Student {
    @Id
    @SequenceGenerator(name = "student_name", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;
    private String name;
    private LocalDate birthday;
    private String email;

    @CreatedDate
    private LocalDateTime createdAt;


    @Transient
    private Integer age;

//    public Student() {
//    }

//    public Student(Long id, String name, LocalDate birthday,String email) {
//        this.id = id;
//        this.name = name;
//        this.birthday = birthday;
//        this.email = email;
//    }
//
    public Student(String email, LocalDate birthday, String name) {
        this.email = email;
        this.birthday = birthday;
        this.name = name;
    }

//    public Long getId() {
//        return id;
//    }

//    public String getName() {
//        return name;
//    }

    public Integer getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

//    public LocalDate getBirthday() {
//        return birthday;
//    }

//    public String getEmail() {
//        return email;
//    }

}
