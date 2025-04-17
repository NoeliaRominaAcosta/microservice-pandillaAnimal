package com.pandilla.health.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medical_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information about medical records")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long animalId;
    private Date creationDate;
    private Date lastUpdate;
    private List<String> alergies;
    private String specificCare;
    private String specificDiet;
}
