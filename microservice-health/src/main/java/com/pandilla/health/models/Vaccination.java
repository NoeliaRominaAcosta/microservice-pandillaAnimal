package com.pandilla.health.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "vaccine")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information about vaccines")
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long animalId;
    private String vaccineName;
    private Date applicationDate;
    private Date nextDueDate;
    private String notes;
}
