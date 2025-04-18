package com.pandilla.health.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VaccinationDTO {
    //@FeignClient(name = "microservice-animal")
    private Long animalId;
    private String vaccineName;
    private Date applicationDate;
    private Date nextDueDate;
    private String notes;
}
