package com.backend.fms.Entity;

import com.backend.fms.Enum.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)              // <-- auto-increment
    private long id;

    private String username ;
    private String password ;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FinanceRecord> financeRecords;

    @Enumerated(EnumType.STRING)
    private Role role ;

}

