package com.pandilla.finances.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pandilla.finances.models.ExpenseCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ExpenseDTO {
    @JsonProperty("id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private Date date;
    private String description;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;
    private String approvedBy;
}
