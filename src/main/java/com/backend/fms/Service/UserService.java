package com.backend.fms.Service;

import com.backend.fms.Config.JwtService;
import com.backend.fms.DTO.LoginRequest;
import com.backend.fms.DTO.UpdateUser;
import com.backend.fms.Entity.User;
import com.backend.fms.Enum.Role;
import com.backend.fms.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired private UserRepository userRepository ;
    @Autowired private PasswordEncoder passwordEncoder ;
    @Autowired private AuthenticationManager authenticationManager ;
    @Autowired private JwtService jwtService ;

    public String login(LoginRequest cur) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            cur.getUsername(),
                            cur.getPassword()
                    )
            );

            User user = userRepository.findByUsername(cur.getUsername());
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            return jwtService.generateToken(user);

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public void saveNewUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.VIEWER);
        userRepository.save(user) ;
    }

    public void saveNewAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user) ;
    }

    public void saveNewAnalyst(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ANALYST);
        userRepository.save(user) ;
    }

    private User getCurrentUser() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByUsername(username);
    }

    public void updateUser(UpdateUser dto) {

        User user = getCurrentUser();

        // verify old password
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // update data
        user.setUsername(dto.getNewUsername());
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));

        userRepository.save(user);
    }

    public void deleteMyAccount() {
        User user = getCurrentUser();
        userRepository.delete(user);
    }


    public List<User> allUsers(){
        return userRepository.findAll() ;
    }

}
