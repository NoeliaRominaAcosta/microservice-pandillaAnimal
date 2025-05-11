package com.pandilla.adoptions.dto;

import com.pandilla.adoptions.models.Adopter;
import com.pandilla.adoptions.models.AdoptionType;
import com.pandilla.adoptions.models.House;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdopterDTO {
    private Long id;
    private String name;
    private Date adoptionDate;
    @Enumerated(EnumType.STRING)
    private AdoptionType adoptionType; // puede ser adopcion o transito
    private Boolean contract;
    private int dni;
    private Long phone;
    private String email;
    private String socialMedia;
    @Embedded
    private House house;
    private String dniImageURL;
    private String serviceOrContractImageURL;

}
