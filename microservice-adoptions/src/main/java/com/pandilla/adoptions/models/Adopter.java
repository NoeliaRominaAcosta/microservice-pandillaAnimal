package com.pandilla.adoptions.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Date;

@Entity
@Table(name = "adopter")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information about an animal")
public class Adopter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
