package com.example.project3.Sevice;


import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Account;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;


    public void registerEmployee(User user , EmployeeDTO employeeDTO){

        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        user1.setRole("EMPLOYEE");

        String hash = new BCryptPasswordEncoder().encode(user1.getPassword());

        Employee employee = new Employee();
        employee.setId(user.getId());
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());


        user1.setPassword(hash);
        user1.setEmployee(employee);


        employeeRepository.save(employee);
        authRepository.save(user1);
    }



//    endpoint

    public void activeAccount(Integer accountId ){

        Account account = accountRepository.findAccountById(accountId);

        account.setIsActive(Boolean.TRUE);

        accountRepository.save(account);

    }




    public List<User> userList(){
        return authRepository.findAll();
    }


    public void blockAccount(Integer accountId){
        Account account = accountRepository.findAccountById(accountId);
        account.setIsActive(Boolean.FALSE);
        accountRepository.save(account);
    }
}
