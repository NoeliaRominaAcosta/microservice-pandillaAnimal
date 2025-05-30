package com.pandilla.health.controllers;

import com.pandilla.health.dto.VetVisitDTO;
import com.pandilla.health.services.VeterinaryService;
import jakarta.ws.rs.Path;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/VetVisit")
public class VetVisitController {
    @Autowired
    VeterinaryService service;

    @GetMapping
    public List<VetVisitDTO> getVetVisits(){
        return this.service.getVeterinaryVisits();
    }
    @PostMapping
    public ResponseEntity<VetVisitDTO> saveVetVisit(@RequestBody VetVisitDTO dto){
        VetVisitDTO savedVetVisit = service.saveVetVisit(dto);
        return new ResponseEntity<>(savedVetVisit, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VetVisitDTO> getVetVisitById(@PathVariable Long id){
        Optional<VetVisitDTO> vetDto = service.getVetVisitById(id);
        return vetDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VetVisitDTO> updatedVetVisit(@RequestBody VetVisitDTO dto, @PathVariable("id") Long id){
        VetVisitDTO updatedDto = service.updateVetVisitById(dto, id);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedVetVisit(@PathVariable("id") Long id){
        boolean deleted = service.deleteById(id);
        String message = "Vet visit with id " + id + (deleted ? " is deleted " : " could not be deleted");
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(message);
    }

}
