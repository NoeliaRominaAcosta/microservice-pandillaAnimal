package com.pandilla.health.mappers;

import com.pandilla.health.dto.MedicalRecordDTO;
import com.pandilla.health.dto.SurgeryDTO;
import com.pandilla.health.dto.VaccinationDTO;
import com.pandilla.health.dto.VetVisitDTO;
import com.pandilla.health.models.MedicalRecord;
import com.pandilla.health.models.Surgery;
import com.pandilla.health.models.Vaccination;
import com.pandilla.health.models.VetVisit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface HealthMapper {
    MedicalRecordDTO medRecordToMedRecordDTO(MedicalRecord medicalRecord);
    MedicalRecord medRecordDTOtoMedRecord(MedicalRecordDTO medRecordDTO);
    void updateMedRecordFromDTO(MedicalRecordDTO medRecordDTO, @MappingTarget MedicalRecord medicalRecord);

    SurgeryDTO surgeryToSurgeryDTO(Surgery surgery);
    Surgery surgeryDTOtoSurgery(SurgeryDTO surgeryDTO);
    void updateSurgeryFromDTO(SurgeryDTO surgeryDTO, @MappingTarget Surgery surgery);

    VaccinationDTO vacToVacDTO(Vaccination vaccination);
    Vaccination vaccinationDTOtoVaccination(VaccinationDTO vaccinationDTO);
    void updateVacFromDTO(VaccinationDTO vaccinationDTO, @MappingTarget Vaccination vaccination);

    VetVisitDTO vetVisitToDTO(VetVisit vetVisit);
    VetVisit vetVisitDTOtoVetVisit(VetVisitDTO vetVisitDTO);
    void updateVetVisitToDTO(VetVisitDTO vetVisitDTO, @MappingTarget VetVisit vetVisit);

}
