package com.example.demo.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
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

    @NotBlank
    private String name;

    @NotBlank
    private LocalDate birthday;

    @NotBlank
    private String email;

    private String placeOfBirth;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @Transient
    private Integer age;


    public Student(String email, LocalDate birthday, String name) {
        this.email = email;
        this.birthday = birthday;
        this.name = name;
    }


    public Integer getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }


}
