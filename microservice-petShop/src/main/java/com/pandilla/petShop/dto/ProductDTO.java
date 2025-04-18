package com.pandilla.petShop.dto;

import com.pandilla.petShop.models.Category;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
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
