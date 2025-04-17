package com.pandilla.petShop.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sale_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information about sales details")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long saleId;
    private Long productId;
    private int quantity;
    private Double unitPrice;
}
