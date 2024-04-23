package com.example.project3.Controller;


import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.User;
import com.example.project3.Sevice.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;



    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user , @RequestBody @Valid EmployeeDTO employeeDTO) {

        employeeService.registerEmployee(user , employeeDTO);

        return ResponseEntity.status(200).body(new ApiResponse("employee added successfully"));

    }


//    endpoint

    @PutMapping("/active_account")
    public ResponseEntity activeAccount(@AuthenticationPrincipal User user , Integer accountId ){
        employeeService.activeAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("account activated"));
    }



    @GetMapping("/list_users")
    public ResponseEntity listUsers(){
        return ResponseEntity.status(200).body(employeeService.userList());

    }


    @PutMapping("/block/{accountId}")
    public ResponseEntity blockAccount(@AuthenticationPrincipal User user ,@PathVariable Integer accountId ){
        employeeService.blockAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("account blocked"));
    }
}
