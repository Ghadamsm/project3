package com.example.project3.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employee {


    @Id
    private Integer id ;


    @NotEmpty(message = "position cannot be empty")
    @Column(columnDefinition = "varchar(40) not null")
    private String position;


    @NotNull(message = "salary should be not null")
    @Positive(message = "positive number only")
    @Column(columnDefinition = "int not null")
    private Integer salary;




    @OneToOne
    @MapsId
    private User user ;


}
