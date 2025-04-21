package com.pandilla.adoptions.controllers;

import com.pandilla.adoptions.dto.AdopterDTO;
import com.pandilla.adoptions.models.Adopter;
import com.pandilla.adoptions.services.AdopterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adopter")
public class adopterController {
    @Autowired
    AdopterService adopterService;

    @GetMapping(path = "/")
    public List<AdopterDTO> getAdopters() {
        return this.adopterService.getAdopters();
    }
    @PostMapping
    @Operation(summary = "Guardar un nuevo animal", description = "Guarda la información de un nuevo animal en el sistema.")
    public ResponseEntity<AdopterDTO> saveAdopter(@RequestBody AdopterDTO adopterDTO){
        AdopterDTO savedAdopter = adopterService.saveAdopter(adopterDTO);
         return new ResponseEntity<>(savedAdopter, HttpStatus.CREATED);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<AdopterDTO> getAdopterById(@PathVariable Long id){
        Optional<AdopterDTO> adopterDTO = this.adopterService.getById(id);
        return adopterDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<AdopterDTO> updateAdopter(@RequestBody AdopterDTO request, @PathVariable("id") Long id){
        AdopterDTO updatedAdopter = this.adopterService.updateById(request, id);
        return ResponseEntity.ok(updatedAdopter);
    }
    @DeleteMapping(path = "/{id}")
        public ResponseEntity<String> deleteAnimalById(@PathVariable("id") Long id){
        boolean deleted = adopterService.deleteById(id);

        String message = "Adopter with id " + id + (deleted ? " is deleted" : " could not be deleted");
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(message);
    }
}

