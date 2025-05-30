package com.pandilla.petShop.services;

import com.pandilla.petShop.dto.SaleDTO;
import com.pandilla.petShop.mappers.PetShopMapper;
import com.pandilla.petShop.models.Sale;
import com.pandilla.petShop.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalesService {
    @Autowired
    PetShopMapper mapper;
    @Autowired
    ISaleRepository saleRepository;

    public List<SaleDTO> getSales(){
        List<Sale> sale = saleRepository.findAll();
        return sale.stream().map(mapper::saleToSaleDTO).collect(Collectors.toList());
    }

    public SaleDTO saveSale(SaleDTO dto){
        Sale sale = mapper.saleDTOtoSale(dto);
        Sale savedSale = saleRepository.save(sale);
        return mapper.saleToSaleDTO(savedSale);
    }

    public Optional<SaleDTO> getSalesById(Long id){
        Optional<Sale> optionalSale = saleRepository.findById(id);
        return optionalSale.map(mapper::saleToSaleDTO);
    }

    public SaleDTO updatedById(SaleDTO dto, Long id){
        Optional<Sale> optionalSale = saleRepository.findById(id);
        if (optionalSale.isPresent()){
            Sale sale = optionalSale.get();
            mapper.updateSaleFromDTO(dto,sale);
            Sale updatedSale = saleRepository.save(sale);
            return mapper.saleToSaleDTO(updatedSale);
        }else{
            throw new NoSuchElementException("Sale with ID " + id + " not found");

        }
    }
    public Boolean deleteById(Long id) {
        try {
            saleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}