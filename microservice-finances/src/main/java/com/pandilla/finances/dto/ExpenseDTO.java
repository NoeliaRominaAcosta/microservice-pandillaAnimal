package com.pandilla.finances.dto;

import com.pandilla.finances.models.ExpenseCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseDTO {
    private Date date;
    private String description;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;
    private String approvedBy;
}
