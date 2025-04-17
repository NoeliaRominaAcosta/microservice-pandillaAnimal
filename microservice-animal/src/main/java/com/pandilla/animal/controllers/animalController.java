package com.pandilla.animal.controllers;


import com.pandilla.animal.dto.AnimalDTO;
import com.pandilla.animal.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/animal")
public class animalController {

    @Autowired
    AnimalService animalService;

    @GetMapping(path = "/")
    public List<AnimalDTO> getAnimals() {
        return this.animalService.getAnimals();
    }

    @PostMapping
    @Operation(summary = "Guardar un nuevo animal", description = "Guarda la información de un nuevo animal en el sistema.")
    public ResponseEntity<AnimalDTO> savePet(@RequestBody AnimalDTO animalDTO) {
        AnimalDTO savedAnimal = animalService.saveAnimal(animalDTO);
        return new ResponseEntity<>(savedAnimal, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AnimalDTO> getAnimalById(@PathVariable Long id) {
        Optional<AnimalDTO> animalDTO = this.animalService.getById(id);
        return animalDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AnimalDTO> updateAnimal(@RequestBody AnimalDTO request, @PathVariable("id") Long id) {
        AnimalDTO updatedAnimal = this.animalService.updateById(request, id);
        return ResponseEntity.ok(updatedAnimal);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteAnimalById(@PathVariable("id") Long id) {
        boolean ok = this.animalService.deleteById(id);
        if (ok) {
            return ResponseEntity.ok("Animal with id " + id + " is deleted");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERROR deleting animal with id " + id);
        }
    }
}
