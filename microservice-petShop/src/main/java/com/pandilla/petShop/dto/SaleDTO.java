package com.pandilla.petShop.dto;

import com.pandilla.petShop.models.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
    private Date saleDate;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private Double totalAmount;
    private PaymentMethod paymentMethod;
    private String notes;
}
