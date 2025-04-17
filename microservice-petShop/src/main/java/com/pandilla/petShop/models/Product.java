package com.pandilla.petShop.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Information about products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private int stock;
    private Category category;
    private Boolean active;
    @Column(name = "product_image", columnDefinition = "TEXT")
    private String productImage;
    public String encodeImage(byte[] imageBytes) {
        return Base64.encodeBase64String(imageBytes);
    }

    public byte[] decodeImage(String base64Image) {
        return Base64.decodeBase64(base64Image);
    }


}
