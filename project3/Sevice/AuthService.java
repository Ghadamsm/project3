package com.example.project3.Sevice;


import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;




    public void delete(Integer userId ) {
       User user = authRepository.findUserById(userId);
       authRepository.delete(user);
    }



    public List<User> getAllUser(){
        return authRepository.findAll();
    }


    public void updateUser(Integer userId , User user){
        User user1 = authRepository.findUserById(userId);

        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        authRepository.save(user1);
    }


    public User getUserById(Integer userId){
        return authRepository.findUserById(userId);
    }



}
