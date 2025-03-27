package com.romina.animal.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnimalDTO {
    private String name;
    private int age;
    private Date arrivalDate;
    private Date adoptedDate;
    private String healthCondition;
    private String adoptedBy;
    private Boolean dewormed;
    private Boolean castration;
    private Date birthDate;
    private Date vetVisitDate;
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;
    public String encodeImage(byte[] imageBytes) {
        return Base64.encodeBase64String(imageBytes);
    }

    public byte[] decodeImage(String base64Image) {
        return Base64.decodeBase64(base64Image);
    }

}
