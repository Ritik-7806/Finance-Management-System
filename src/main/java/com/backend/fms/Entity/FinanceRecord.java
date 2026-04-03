package com.backend.fms.Entity;

import com.backend.fms.Enum.RecordType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "finance-record")
public class FinanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)              // <-- auto-increment
    private long id;

    private BigDecimal amount;                                       // only in inr

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private RecordType type ;          //INCOME OR EXPENSE

    private String category ;
    private LocalDate createdDate ;
    private String notes ;

}
