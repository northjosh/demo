package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -1944955285920732398L;

    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private LocalDate birthday;

    @NotBlank
    @Email
    private String email;

    private String placeOfBirth;




}
