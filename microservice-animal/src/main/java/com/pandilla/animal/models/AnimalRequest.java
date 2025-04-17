package com.pandilla.animal.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Schema(description = "Objeto para la creación de un nuevo animal")
public class AnimalRequest {

    @Schema(description = "Nombre del animal", example = "Firulais")
    private String name;

    @Schema(description = "Edad del animal", example = "3")
    private int age;

    @Schema(description = "Fecha de llegada al refugio", example = "2024-01-15")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;

    @Schema(description = "Fecha de adopción (opcional)", example = "2024-03-10", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date adoptedDate;

    @Schema(description = "Fecha de la última visita veterinaria (opcional)", example = "2024-03-01", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date vetVisitDate;

    @Schema(description = "Fecha de nacimiento (opcional)", example = "2022-03-16", nullable = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Schema(description = "Condición de salud del animal", example = "Saludable")
    private String healthCondition;

    @Schema(description = "¿Está desparasitado? (opcional)", example = "true", nullable = true)
    private Boolean dewormed;

    @Schema(description = "¿Está castrado? (opcional)", example = "false", nullable = true)
    private Boolean castration;

    @Schema(description = "Imagen del animal (multipart file)")
    private String image;
}
