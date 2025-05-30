package com.pandilla.health.services;

import com.pandilla.health.dto.VetVisitDTO;
import com.pandilla.health.mappers.HealthMapper;
import com.pandilla.health.models.VetVisit;
import com.pandilla.health.repository.IVetVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VeterinaryService {
    @Autowired
    HealthMapper mapper;
    @Autowired
    IVetVisitRepository repository;

    public List<VetVisitDTO> getVeterinaryVisits(){
        List<VetVisit> vetVisit = repository.findAll();
        return vetVisit.stream().map(mapper::vetVisitToDTO).collect(Collectors.toList());
    }

    public VetVisitDTO saveVetVisit(VetVisitDTO dto){
        VetVisit vetVisit = mapper.vetVisitDTOtoVetVisit(dto);
        VetVisit savedVetVisit = repository.save(vetVisit);
        return mapper.vetVisitToDTO(savedVetVisit);
    }

    public Optional<VetVisitDTO> getVetVisitById(Long id){
        Optional<VetVisit> optionalVetVisit = repository.findById(id);
        return optionalVetVisit.map(mapper::vetVisitToDTO);
    }

    public VetVisitDTO updateVetVisitById(VetVisitDTO request, Long id){
        Optional<VetVisit> optionalVisit = repository.findById(id);
        if (optionalVisit.isPresent()){
            VetVisit vetVisit = optionalVisit.get();
            mapper.updateVetVisitToDTO(request, vetVisit);
            VetVisit updatedVetVisit = repository.save(vetVisit);
            return mapper.vetVisitToDTO(updatedVetVisit);
        }else{
            throw new NoSuchElementException("Vaccine with ID " + id + " not found");
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
