package com.backend.fms.Repository;

import com.backend.fms.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username) ;

}
