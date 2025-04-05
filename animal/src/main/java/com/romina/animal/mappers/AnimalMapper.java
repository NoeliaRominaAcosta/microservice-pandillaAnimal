package com.romina.animal.mappers;

import com.romina.animal.dto.AnimalDTO;
import com.romina.animal.models.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    AnimalDTO animalToAnimalDTO(Animal animal);

    Animal animalDTOtoAnimal(AnimalDTO animalDTO);

    void updateAnimalFromDTO(AnimalDTO animalDTO, @MappingTarget Animal animal);

}
