package com.backend.fms.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUser {
    private String password;      // old password
    private String newUsername;
    private String newPassword;
}

