package com.example.project3.DTO;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmployeeDTO {

    private Integer user_id ;


    @NotEmpty(message = "position cannot be empty")
    private String position;


    @NotNull(message = "salary should be not null")
    @Positive(message = "positive number only")
    private Integer salary;



}
