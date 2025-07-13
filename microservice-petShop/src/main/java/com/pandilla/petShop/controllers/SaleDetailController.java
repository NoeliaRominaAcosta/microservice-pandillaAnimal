package com.pandilla.petShop.controllers;

import com.pandilla.petShop.dto.SaleDetailDTO;
import com.pandilla.petShop.models.SaleDetail;
import com.pandilla.petShop.services.SalesDetailsService;
import com.pandilla.petShop.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/SalesDetails")
public class SaleDetailController {
    @Autowired
    private SalesDetailsService service;

    @GetMapping("/")
    public List<SaleDetailDTO> getDetails(){
        return service.getSalesDetails();
    }
    @PostMapping("/")
    public ResponseEntity<SaleDetailDTO> saveSaleDetails(@RequestBody SaleDetailDTO dto){
        SaleDetailDTO saveDTO = service.saveSaleDetail(dto);
        return new ResponseEntity<>(saveDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDetailDTO> getDetailsByid(@PathVariable("id") Long id){
        Optional<SaleDetailDTO> saleDto = service.getDetailsById(id);
        return saleDto.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDetailDTO> updateById(@RequestBody SaleDetailDTO dto, @PathVariable("id") Long id){
        SaleDetailDTO updatedDTO = service.updateSaleDetails(dto,id);
        return ResponseEntity.ok(updatedDTO);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSaleDetail(@PathVariable("id") Long id){
        boolean deleted = service.deleteById(id);
        String message = "Sale detail deleted with id " + id + (deleted ? " is deleted" : " could not be deleted");
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(message);
    }
}
