package com.pandilla.health.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "vet_visit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information about vet visits")
public class VetVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long animalId;
    private Date visitDate;
    private String vetName;
    private String diagnosis;
    private String treatment;
    private Double cost;
    private String notes;


}
