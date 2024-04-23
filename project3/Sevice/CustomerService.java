package com.example.project3.Sevice;


import com.example.project3.DTO.CustomerDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;


    public void registerCustomer(User user , CustomerDTO customerDTO){

        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setName(user.getName());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        user1.setRole("CUSTOMER");

        String hash = new BCryptPasswordEncoder().encode(user1.getPassword());

        Customer customer = new Customer();
        customer.setId(customerDTO.getUser_id());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());


        user1.setPassword(hash);
        user1.setCustomer(customer);
        customer.setUser(user1);

        customerRepository.save(customer);
        authRepository.save(user1);
    }
}
