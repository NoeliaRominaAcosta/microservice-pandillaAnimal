package com.romina.animal.service;

import com.romina.animal.dto.AnimalDTO;
import com.romina.animal.mappers.AnimalMapper;
import com.romina.animal.models.Animal;
import com.romina.animal.repository.IAnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {
    @Autowired
    IAnimalRepository animalRepository;
    @Autowired
    AnimalMapper animalMapper; // Inject the mapper

    public List<AnimalDTO> getAnimals() {
        List<Animal> animals = animalRepository.findAll();
        return animals.stream().map(animalMapper::animalToAnimalDTO).collect(Collectors.toList());
    }

    public AnimalDTO saveAnimal(AnimalDTO animalDTO) {
        Animal animal = animalMapper.animalDTOtoAnimal(animalDTO);
        Animal savedAnimal = animalRepository.save(animal);
        return animalMapper.animalToAnimalDTO(savedAnimal);
    }

    public Optional<AnimalDTO> getById(Long id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        return optionalAnimal.map(animalMapper::animalToAnimalDTO);
    }

    public AnimalDTO updateById(AnimalDTO request, Long id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);

        if (optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.get();
            // Use the mapper to update the entity fields from the DTO
            animalMapper.updateAnimalFromDTO(request, animal);
            Animal updatedAnimal = animalRepository.save(animal);
            return animalMapper.animalToAnimalDTO(updatedAnimal);
        } else {
            throw new NoSuchElementException("Animal with ID " + id + " not found");
        }
    }

    public Boolean deleteById(Long id) {
        try {
            animalRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}