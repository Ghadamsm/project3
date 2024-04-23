package com.example.project3.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {


    @Id
    private Integer id ;


    @NotEmpty(message = "phone number should be not empty")
    //@Pattern(regexp = "^05.*",message = "salary must start with 05")
    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;




    @OneToOne
    @MapsId
    private User user ;


    @OneToMany(cascade = CascadeType.ALL , mappedBy = "customer")
    private Set<Account> accountSet;

}
