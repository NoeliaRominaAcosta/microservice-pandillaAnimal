package com.pandilla.petShop.controllers;

import com.pandilla.petShop.dto.SaleDTO;
import com.pandilla.petShop.models.Sale;
import com.pandilla.petShop.services.SalesService;
import jakarta.ws.rs.Path;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Sales")
public class SalesController {

    @Autowired
    SalesService service;

    @GetMapping("/")
    public List<SaleDTO> getSales(){
        return service.getSales();
    }

    @PostMapping("/")
    public ResponseEntity<SaleDTO> saveSale(@RequestBody SaleDTO dto){
        SaleDTO saveDto = service.saveSale(dto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable("id") Long id){
        Optional<SaleDTO> saleDto = service.getSalesById(id);
        return saleDto.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<SaleDTO> updateById(@RequestBody SaleDTO dto, @PathVariable("id") Long id){
        SaleDTO updatedDto = service.updatedById(dto, id);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deletedSaleById(@PathVariable("id") Long id){
        boolean deleted = service.deleteById(id);
        String message = "Sale deleted with id " + id + (deleted ? " is deleted" : " could not be deleted");
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(message);
    }

}