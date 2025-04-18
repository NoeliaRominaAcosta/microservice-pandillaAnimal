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
public class SurgeryDTO {
    //@FeignClient(name = "microservice-animal")
    private Long animalId;
    private Date surgeryDate;
    private String surgeryType;
    private String veterinary;
    private String description;
    private String complications;
    private String aftercare;
    private Long cost;
    private Boolean succesful;
}
