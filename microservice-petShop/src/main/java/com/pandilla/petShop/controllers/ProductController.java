package com.pandilla.petShop.controllers;

import com.pandilla.petShop.dto.ProductDTO;
import com.pandilla.petShop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/")
    public List<ProductDTO> getProducts(){
        return service.getProducts();
    }
    @PostMapping("/")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody  ProductDTO dto){
        ProductDTO saveDto = service.saveProduct(dto);
        return new ResponseEntity<>(saveDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id){
        Optional<ProductDTO> productDTO = service.getProductById(id);
        return productDTO.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProductById(@RequestBody ProductDTO dto, @PathVariable("id") Long id){
        ProductDTO updatedProduct = service.updateById(dto,id);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deletedSaleById(@PathVariable("id") Long id){
        boolean deleted = service.deleteById(id);
        String message = "Product deleted with id " + id + (deleted ? " is deleted" : " could not be deleted");
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(message);
    }
}