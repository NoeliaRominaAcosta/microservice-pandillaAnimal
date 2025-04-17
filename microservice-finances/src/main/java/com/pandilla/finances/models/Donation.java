package com.pandilla.finances.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="donation")
@Schema(description = "Information about an donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String donorName;
    private String donorEmail;
    private String donorPhone;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private DonationType type;
    private String notes;
    private Boolean receiptSent;

}
