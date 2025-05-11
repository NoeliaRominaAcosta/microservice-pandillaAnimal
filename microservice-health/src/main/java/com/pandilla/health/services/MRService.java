package com.pandilla.health.services;

import com.pandilla.health.dto.MedicalRecordDTO;
import com.pandilla.health.mappers.HealthMapper;
import com.pandilla.health.models.MedicalRecord;
import com.pandilla.health.repository.IMedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MRService {
    @Autowired
    HealthMapper healthMapper;
    @Autowired
    IMedicalRecordRepository iMedicalRecordRepository;

    public List<MedicalRecordDTO> getMedicalRecords(){
        List<MedicalRecord> mrs = iMedicalRecordRepository.findAll();
        return mrs.stream().map(healthMapper::medRecordToMedRecordDTO).collect(Collectors.toList());
    }
    public MedicalRecordDTO saveMRecord(MedicalRecordDTO mrDTO){
        MedicalRecord mr = healthMapper.medRecordDTOtoMedRecord(mrDTO);
        MedicalRecord savedMR = iMedicalRecordRepository.save(mr);
        return healthMapper.medRecordToMedRecordDTO(savedMR);
    }

    public Optional<MedicalRecordDTO> getById(Long id){
        Optional<MedicalRecord> optionalMR = iMedicalRecordRepository.findById(id);
        return optionalMR.map(healthMapper::medRecordToMedRecordDTO);
    }
    public MedicalRecordDTO updatedById(MedicalRecordDTO request, Long id){
        Optional<MedicalRecord> optionalMR = iMedicalRecordRepository.findById(id);
        if(optionalMR.isPresent()){
            MedicalRecord mr = optionalMR.get();
            healthMapper.updateMedRecordFromDTO(request,mr);
            MedicalRecord updatedMR = iMedicalRecordRepository.save(mr);
            return healthMapper.medRecordToMedRecordDTO(updatedMR);
        }else{
            throw new NoSuchElementException("Medical record with ID " + id + " not found");
        }
    }
    public Boolean deleteById(Long id) {
        try {
            iMedicalRecordRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}