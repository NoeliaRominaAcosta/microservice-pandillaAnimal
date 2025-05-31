package com.pandilla.petShop.services;

import com.pandilla.petShop.dto.ProductDTO;
import com.pandilla.petShop.mappers.PetShopMapper;
import com.pandilla.petShop.models.Product;
import com.pandilla.petShop.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private IProductRepository repository;
    @Autowired
    private PetShopMapper mapper;

    public List<ProductDTO> getProducts(){
        List<Product> products = repository.findAll();
        return products.stream().map(mapper::productToProductDTO).collect(Collectors.toList());
    }

    public ProductDTO saveProduct(ProductDTO dto){
        Product product = mapper.productDTOtoProduct(dto);
        Product savedProduct = repository.save(product);
        return mapper.productToProductDTO(savedProduct);
    }

    public Optional<ProductDTO> getProductById(Long id){
        Optional<Product> optionalProduct = repository.findById(id);
        return optionalProduct.map(mapper::productToProductDTO);
    }

    public ProductDTO updateById(ProductDTO dto, Long id){
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            mapper.updateProductFromDTO(dto, product);
            Product updatedProduct = repository.save(product);
            return mapper.productToProductDTO(updatedProduct);
        }else{
            throw new NoSuchElementException("Product with ID " + id + " not found");
        }
    }


    public Boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}