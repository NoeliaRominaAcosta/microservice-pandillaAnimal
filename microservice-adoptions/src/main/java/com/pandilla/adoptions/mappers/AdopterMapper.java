package com.pandilla.adoptions.mappers;

import com.pandilla.adoptions.dto.AdopterDTO;
import com.pandilla.adoptions.models.Adopter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
public class AdopterMapper {

    public AdopterDTO toDTO(Adopter adopter) {
        if (adopter == null) return null;

        return AdopterDTO.builder()
                .id(adopter.getId())
                .name(adopter.getName())
                .adoptionDate(adopter.getAdoptionDate())
                .adoptionType(adopter.getAdoptionType())
                .contract(adopter.getContract())
                .dni(adopter.getDni())
                .phone(adopter.getPhone())
                .email(adopter.getEmail())
                .socialMedia(adopter.getSocialMedia())
                .house(adopter.getHouse())
                .dniImageURL(adopter.getDniImageURL())
                .serviceOrContractImageURL(adopter.getServiceOrContractImageURL())
                .build();
    }

    public Adopter toEntity(AdopterDTO dto) {
        if (dto == null) return null;

        Adopter adopter = new Adopter();
        adopter.setId(dto.getId());
        adopter.setName(dto.getName());
        adopter.setAdoptionDate(dto.getAdoptionDate());
        adopter.setAdoptionType(dto.getAdoptionType());
        adopter.setContract(dto.getContract());
        adopter.setDni(dto.getDni());
        adopter.setPhone(dto.getPhone());
        adopter.setEmail(dto.getEmail());
        adopter.setSocialMedia(dto.getSocialMedia());
        adopter.setHouse(dto.getHouse());
        adopter.setDniImageURL(dto.getDniImageURL());
        adopter.setServiceOrContractImageURL(dto.getServiceOrContractImageURL());

        return adopter;
    }

    public void updateEntityFromDTO(AdopterDTO dto, Adopter adopter) {
        if (dto == null || adopter == null) return;

        adopter.setName(dto.getName());
        adopter.setAdoptionDate(dto.getAdoptionDate());
        adopter.setAdoptionType(dto.getAdoptionType());
        adopter.setContract(dto.getContract());
        adopter.setDni(dto.getDni());
        adopter.setPhone(dto.getPhone());
        adopter.setEmail(dto.getEmail());
        adopter.setSocialMedia(dto.getSocialMedia());
        adopter.setHouse(dto.getHouse());
        adopter.setDniImageURL(dto.getDniImageURL());
        adopter.setServiceOrContractImageURL(dto.getServiceOrContractImageURL());
    }

}
