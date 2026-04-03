package com.backend.fms.Controller;

import com.backend.fms.Entity.User;
import com.backend.fms.Repository.UserRepository;
import com.backend.fms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {


    @Autowired UserRepository userRepository ;
    @Autowired UserService userService ;


    @PostMapping("new-admin")
    public ResponseEntity<String> saveNewAdmin(@RequestBody User user){
        userService.saveNewAdmin(user) ;
        return ResponseEntity.status(HttpStatus.CREATED).body("New Admin Registered Successfully") ;
    }

    @PostMapping("new-analyst")
    public ResponseEntity<String> saveNewAnalyst(@RequestBody User user){
        userService.saveNewAnalyst(user); ;
        return ResponseEntity.status(HttpStatus.CREATED).body("New Analyst Registered Successfully") ;
    }


    @DeleteMapping("delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userRepository.deleteById(userId);
        return ResponseEntity.ok("User is deleted successfully") ;
    }


    @GetMapping("all-users")
    public ResponseEntity<List<User>> allUsers(){
        return ResponseEntity.ok(userService.allUsers()) ;
    }

}

