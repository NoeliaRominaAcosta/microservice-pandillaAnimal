package com.pandilla.health.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordDTO {
    private Long animalId;
    private Date creationDate;
    private Date lastUpdate;
    private List<String> alergies;
    private String specificCare;
    private String specificDiet;
}
