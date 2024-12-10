package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -2894937892525684L;

    @NotBlank
    private String name;

    @NotBlank
    private LocalDate birthday;

    @NotBlank
    private String email;

    @NotBlank
    private String placeOfBirth;



}
