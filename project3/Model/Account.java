package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
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
public class Account {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull(message = "account Number should be not empty")
    @Column(columnDefinition = "int")
    private Integer accountNumber ;


    @NotNull(message = "balance should be not empty")
    @Positive(message = "balance should be positive number")
    @Column(columnDefinition = "int")
    private Integer balance;


    @AssertFalse
    @Column(columnDefinition = "boolean")
    private Boolean isActive;



    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
