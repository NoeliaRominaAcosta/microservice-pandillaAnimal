package com.pandilla.health.controllers;

import com.pandilla.health.dto.MedicalRecordDTO;
import com.pandilla.health.services.MRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/MedicalRecords")
public class MedRecordController {
    @Autowired
    MRService service;

    @GetMapping(path = "/")
    public List<MedicalRecordDTO> getMedRecords(){
        return this.service.getMedicalRecords();
    }
    @PostMapping
    public ResponseEntity<MedicalRecordDTO> saveMR(@RequestBody MedicalRecordDTO medRecDTO){
        MedicalRecordDTO saveMRecord = service.saveMRecord(medRecDTO);
        return new ResponseEntity<>(saveMRecord, HttpStatus.CREATED);
    }
    @GetMapping(path="/{id}")
    public ResponseEntity<MedicalRecordDTO> getMRById(@PathVariable Long id){
        Optional<MedicalRecordDTO> mrDTO = this.service.getById(id);
        return mrDTO.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<MedicalRecordDTO> updateMR(@RequestBody MedicalRecordDTO dto, @PathVariable("id") Long id){
        MedicalRecordDTO updatedDTO = this.service.updatedById(dto, id);
        return ResponseEntity.ok(updatedDTO);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteMRById(@PathVariable("id") Long id){
        boolean deleted = service.deleteById(id);
        String message = "Med record deleted with id " + id + (deleted ? " is deleted" : " could not be deleted");
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(message);
    }

}
