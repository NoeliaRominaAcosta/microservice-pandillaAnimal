package com.pandilla.adoptions.services;

import com.pandilla.adoptions.dto.AdopterDTO;
import com.pandilla.adoptions.mappers.AdopterMapper;
import com.pandilla.adoptions.models.Adopter;
import com.pandilla.adoptions.repository.IAdopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class AdopterService {
    @Autowired
    IAdopterRepository adopterRepository;
    @Autowired
    AdopterMapper adopterMapper;

    public List<AdopterDTO> getAdopters(){
        List<Adopter> adopters = adopterRepository.findAll();
        return adopters.stream().map(adopterMapper::adopterToAdopterDTO).collect(Collectors.toList());
    }
    public AdopterDTO saveAdopter(AdopterDTO adopterDTO){
        Adopter adopter = adopterMapper.adopterDTOtoAdopter(adopterDTO);
        Adopter saveAdopter = adopterRepository.save(adopter);
        return adopterMapper.adopterToAdopterDTO(saveAdopter);
    }
    public Optional<AdopterDTO> getById(Long id){
        Optional<Adopter> optionalAdopter = adopterRepository.findById(id);
        return optionalAdopter.map(adopterMapper::adopterToAdopterDTO);
    }
    public AdopterDTO updateById(AdopterDTO request, Long id){
        Optional<Adopter> optionalAdopter = adopterRepository.findById(id);
        if(optionalAdopter.isPresent()){
            Adopter adopter = optionalAdopter.get();
            adopterMapper.updateAdopterFromDTO(request,adopter);
            Adopter updateAdopter = adopterRepository.save(adopter);
            return adopterMapper.adopterToAdopterDTO(updateAdopter);
        }else{
            throw new NoSuchElementException("Adopter with ID " + id + " NOT FOUND");
        }
    }
    public Boolean deleteById(Long id){
        try {
            adopterRepository.deleteById(id);
            return true;
        } catch (Exception e) {
           System.out.println(e.getMessage());
            return false;
        }
    }

}
