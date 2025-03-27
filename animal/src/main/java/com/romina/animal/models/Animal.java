package com.romina.animal.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;

import java.util.Date;

@Entity
@Table(name = "animal")
@Data
@Schema(description = "Information about an animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    //esto será consumido de la api de health
    /*
    @ElementCollection
    @CollectionTable(name = "pet_illness_reference", joinColumns = @JoinColumn(name = "pet_id"))
    @Column(name = "illness_id")
    private Set<Long> illnessIds = new HashSet<>();
    */


}
