package com.backend.fms.DTO;

import com.backend.fms.Enum.RecordType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;


@Getter
@Setter
public class CreateRecord {

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Record type is required")
    private RecordType type;  // INCOME or EXPENSE

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Date is required")
    private LocalDate createdDate;

    private String notes;  // optional



}
