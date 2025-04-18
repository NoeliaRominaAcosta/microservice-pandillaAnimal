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
public class VetVisitDTO {
    //@FeignClient(name = "microservice-animal")
    private Long animalId;
    private Date visitDate;
    private String vetName;
    private String diagnosis;
    private String treatment;
    private Double cost;
    private String notes;
}
