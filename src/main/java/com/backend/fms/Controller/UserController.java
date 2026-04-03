package com.backend.fms.Controller;

import com.backend.fms.DTO.CreateRecord;
import com.backend.fms.DTO.UpdateUser;
import com.backend.fms.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired private UserService userService;


    @PostMapping("update")
    public ResponseEntity<String> updateUser(@RequestBody UpdateUser dto){
        userService.updateUser(dto);
        return ResponseEntity.ok("User updated successfully");
    }

    @PostMapping("delete")
    public ResponseEntity<String> deleteUser(){
        userService.deleteMyAccount();
        return ResponseEntity.ok("User deleted successfully");
    }
}
