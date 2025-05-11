package com.pandilla.finances.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class MontlyReportDTO {
    @JsonProperty("id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    private Date date;
    private Double totalExpences;
    private Double totalDonations;
    private Double balance;
    private Date generatedDate;
}
