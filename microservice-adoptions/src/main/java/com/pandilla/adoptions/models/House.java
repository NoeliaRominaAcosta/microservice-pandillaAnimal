package com.pandilla.adoptions.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class House {
    private String location; // provincia, localidad, direccion, numero
    private Boolean protection; // si tiene red en el balcon, ventqanas, etc
    private Boolean kids;
    private int familyMembers;
    private int anotherPets; // si tiene otras mascotas
    private List<String> imageURLs; // Lista para guardar las URLs de las imágenes

}
