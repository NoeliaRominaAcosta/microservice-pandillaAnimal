package com.pandilla.adoptions.mappers;

import com.pandilla.adoptions.dto.AdopterDTO;
import com.pandilla.adoptions.models.Adopter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdopterMapper {


    AdopterDTO adopterToAdopterDTO(Adopter adopter);

    Adopter adopterDTOtoAdopter (AdopterDTO adopterDTO);

    void updateAdopterFromDTO (AdopterDTO adopterDTO, @MappingTarget Adopter adopter);
}
