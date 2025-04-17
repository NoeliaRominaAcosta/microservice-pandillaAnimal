package com.pandilla.animal.mappers;

import com.pandilla.animal.dto.AnimalDTO;
import com.pandilla.animal.models.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    AnimalDTO animalToAnimalDTO(Animal animal);

    Animal animalDTOtoAnimal(AnimalDTO animalDTO);

    void updateAnimalFromDTO(AnimalDTO animalDTO, @MappingTarget Animal animal);

}
