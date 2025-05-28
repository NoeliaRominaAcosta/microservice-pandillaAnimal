package com.pandilla.health.controllers;

import com.pandilla.health.dto.VaccinationDTO;
import com.pandilla.health.services.VaccinationService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Vaccination")
public class VaccinationController {

    @Autowired
    VaccinationService service;

    @GetMapping(path = "/")
    public List<VaccinationDTO> getVaccines(){
        return this.service.getVaccines();
    }
    @PostMapping
    public ResponseEntity<VaccinationDTO> saveVaccine(@RequestBody VaccinationDTO dto){
        VaccinationDTO savedVac = service.saveVaccine(dto);
        return new ResponseEntity<>(savedVac, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<VaccinationDTO> getVacById(@PathVariable Long id){
        Optional<VaccinationDTO> vacDTO = this.service.getVacById(id);
        return vacDTO.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<VaccinationDTO> updateVac(@RequestBody VaccinationDTO vacDto, @PathVariable("id") Long id){
        VaccinationDTO updatedDTO = this.service.updateById(vacDto,id);
        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteVacById(@PathVariable("id") Long id){
        boolean deleted = service.deleteById(id);
        String message = "Vaccine deleted with id " + id + (deleted ? " is deleted " : " could not be deleted");
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(message);
    }
}

