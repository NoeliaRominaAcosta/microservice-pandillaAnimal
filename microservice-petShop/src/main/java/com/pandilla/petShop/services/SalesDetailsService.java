package com.pandilla.petShop.services;

import com.pandilla.petShop.dto.SaleDetailDTO;
import com.pandilla.petShop.mappers.PetShopMapper;
import com.pandilla.petShop.models.SaleDetail;
import com.pandilla.petShop.repository.ISaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalesDetailsService {
    @Autowired
    private PetShopMapper mapper;
    @Autowired
    private ISaleDetailRepository repository;

    public List<SaleDetailDTO> getSalesDetails(){
        List<SaleDetail> saleDetails = repository.findAll();
        return saleDetails.stream().map(mapper::saleDetailToDetailDTO).collect(Collectors.toList());
    }

    public SaleDetailDTO saveSaleDetail(SaleDetailDTO dto){
        SaleDetail detail = mapper.saleDetailDTOtoDetail(dto);
        SaleDetail savedDetail = repository.save(detail);
        return mapper.saleDetailToDetailDTO(savedDetail);
    }
    public Optional<SaleDetailDTO> getDetailsById(Long id){
        Optional<SaleDetail> optionalDetail = repository.findById(id);
        return optionalDetail.map(mapper::saleDetailToDetailDTO);
    }

    public SaleDetailDTO updateSaleDetails(SaleDetailDTO dto, Long id){
        Optional<SaleDetail> optionalDetail = repository.findById(id);
        if (optionalDetail.isPresent()){
            SaleDetail detail = optionalDetail.get();
            mapper.updateSaleDetailFromDTO(dto, detail);
            SaleDetail updatedSaleDetail = repository.save(detail);
            return mapper.saleDetailToDetailDTO(updatedSaleDetail);
        }else{
            throw new NoSuchElementException("Sale Details with ID " + id + " not found");

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