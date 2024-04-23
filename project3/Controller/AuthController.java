package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.Model.User;
import com.example.project3.Sevice.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;




    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId){

        authService.delete(userId);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
    }


    @GetMapping("/get_all_user")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(200).body(authService.getAllUser());
    }


    @PutMapping("/update_user")
    public ResponseEntity updateUser(@AuthenticationPrincipal User userId, @RequestBody @Valid User user){

        authService.updateUser(userId.getId() , user);
        return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));

    }



    @GetMapping("/get")
    public ResponseEntity getUserById(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(authService.getUserById(user.getId()));
    }


}
