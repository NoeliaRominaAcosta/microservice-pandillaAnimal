package com.pandilla.finances.dto;

import com.pandilla.finances.models.DonationType;
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
public class DonationDTO {
    private Date date;
    private String donorName;
    private String donorEmail;
    private String donorPhone;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private DonationType type;
    private String notes;
    private Boolean receiptSent;
}
