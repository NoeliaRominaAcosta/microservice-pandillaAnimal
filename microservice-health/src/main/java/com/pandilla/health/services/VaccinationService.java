package com.pandilla.health.services;

import com.pandilla.health.dto.VaccinationDTO;
import com.pandilla.health.mappers.HealthMapper;
import com.pandilla.health.models.Vaccination;
import com.pandilla.health.repository.IVaccinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VaccinationService {
    @Autowired
    HealthMapper healthMapper;
    @Autowired
    IVaccinationRepository repository;

    public List<VaccinationDTO> getVaccines(){
        List<Vaccination> vaccination = repository.findAll();
        return vaccination.stream().map(healthMapper::vacToVacDTO).collect(Collectors.toList());
    }

    public VaccinationDTO saveVaccine(VaccinationDTO dto){
        Vaccination vac = healthMapper.vaccinationDTOtoVaccination(dto);
        Vaccination savedVac = repository.save(vac);
        return healthMapper.vacToVacDTO(savedVac);
    }

    public Optional<VaccinationDTO> getVacById(Long id){
        Optional<Vaccination> optionalVac = repository.findById(id);
        return optionalVac.map(healthMapper::vacToVacDTO);
    }

    public VaccinationDTO updateById(VaccinationDTO request, Long id) {
        Optional<Vaccination> optionalVac = repository.findById(id);
        if (optionalVac.isPresent()) {
            Vaccination vac = optionalVac.get();
            healthMapper.updateVacFromDTO(request, vac);
            Vaccination updatedVac = repository.save(vac);
            return healthMapper.vacToVacDTO(updatedVac);
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
