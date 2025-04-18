package com.pandilla.finances.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MontlyReportDTO {
    private Date date;
    private Double totalExpences;
    private Double totalDonations;
    private Double balance;
    private Date generatedDate;
}
