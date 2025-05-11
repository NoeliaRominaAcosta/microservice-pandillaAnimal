package com.pandilla.health.services;

import com.pandilla.health.dto.SurgeryDTO;
import com.pandilla.health.mappers.HealthMapper;
import com.pandilla.health.models.Surgery;
import com.pandilla.health.repository.ISurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SurgeryService {
    @Autowired
    HealthMapper healthMapper;
    @Autowired
    ISurgeryRepository repository;

    public List<SurgeryDTO> getSurgeries(){
        List<Surgery> surgery = repository.findAll();
        return surgery.stream().map(healthMapper::surgeryToSurgeryDTO).collect(Collectors.toList());
    }

    public SurgeryDTO saveSurgery(SurgeryDTO dto){
        Surgery surgery = healthMapper.surgeryDTOtoSurgery(dto);
        Surgery savedSurgery = repository.save(surgery);
        return healthMapper.surgeryToSurgeryDTO(savedSurgery);
    }
    public Optional<SurgeryDTO> getSurgeryById(Long id){
        Optional<Surgery> optionalSurgery = repository.findById(id);
        return optionalSurgery.map(healthMapper::surgeryToSurgeryDTO);
    }
    public SurgeryDTO updatedById(SurgeryDTO request, Long id){
        Optional<Surgery> optionalSurgery = repository.findById(id);
        if(optionalSurgery.isPresent()){
            Surgery surgery = optionalSurgery.get();
            healthMapper.updateSurgeryFromDTO(request, surgery);
            Surgery updatedSurgery = repository.save(surgery);
            return healthMapper.surgeryToSurgeryDTO(updatedSurgery);
        }else{
            throw new NoSuchElementException("Surgery with ID " + id + " not found");

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