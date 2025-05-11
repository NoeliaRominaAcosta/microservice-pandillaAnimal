package com.pandilla.health.controllers;

import com.pandilla.health.dto.SurgeryDTO;
import com.pandilla.health.services.SurgeryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Surgery")
public class SurgeryController {

    @Autowired
    SurgeryService service;

    @GetMapping(path = "/")
    public List<SurgeryDTO> getSurgeries(){
        return this.service.getSurgeries();
    }
    @PostMapping
    public ResponseEntity<SurgeryDTO> saveMR(@RequestBody SurgeryDTO dto){
        SurgeryDTO saveMRecord = service.saveSurgery(dto);
        return new ResponseEntity<>(saveMRecord, HttpStatus.CREATED);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<SurgeryDTO> getSurgerybyId(@PathVariable Long id){
        Optional<SurgeryDTO> mrDTO = this.service.getSurgeryById(id);
        return mrDTO.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<SurgeryDTO> updatSurgery(@RequestBody SurgeryDTO dto, @PathVariable("id") Long id){
        SurgeryDTO updatedDTO = this.service.updatedById(dto, id);
        return ResponseEntity.ok(updatedDTO);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteSurgeryById(@PathVariable("id") Long id){
        boolean deleted = service.deleteById(id);
        String message = "Surgery deleted with id " + id + (deleted ? " is deleted" : " could not be deleted");
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(message);
    }

}