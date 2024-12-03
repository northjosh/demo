package com.example.demo.config;

import com.example.demo.repository.StudentRepository;
import com.example.demo.domain.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student josh = new Student(
                    "Josh",
                    LocalDate.of(2000, 5,23),
                    "josh@josh.com"

            );

            Student mamush =  new Student(
                    "Mamush",
                    LocalDate.of(1998, 9,10),
                    "mamush@josh.com"

            );

            studentRepository.saveAll(List.of(josh, mamush));
        };
    }
 }
