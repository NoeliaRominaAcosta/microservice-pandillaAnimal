package com.pandilla.petShop.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information about sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date saleDate;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Double totalAmount;
    private PaymentMethod paymentMethod;
    private String notes;
}
