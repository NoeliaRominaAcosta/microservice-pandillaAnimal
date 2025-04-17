package com.pandilla.health.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "surgery")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information about surgeries")
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
