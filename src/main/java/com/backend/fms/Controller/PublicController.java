package com.backend.fms.Controller;

import com.backend.fms.DTO.LoginRequest;
import com.backend.fms.Entity.User;
import com.backend.fms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("public")
public class PublicController {

    @Autowired
    private UserService userService ;

    @GetMapping("health-check")
    public ResponseEntity<String> healthCheck(){
        return ResponseEntity.status(HttpStatus.OK).body("Application is working properly") ;
    }

    @PostMapping("signup")
    public ResponseEntity<String> signUp(@RequestBody User user){
        userService.saveNewUser(user) ;
        return ResponseEntity.status(HttpStatus.CREATED).body("User Registered Successfully") ;
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String token = userService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @GetMapping("logout")
    public ResponseEntity<String> logOut(){
        return ResponseEntity.status(HttpStatus.OK).body("Logout Successfully") ;
    }
}

