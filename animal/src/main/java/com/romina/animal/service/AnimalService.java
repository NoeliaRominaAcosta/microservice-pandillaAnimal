package com.romina.animal.service;

import com.romina.animal.dto.AnimalDTO;
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

    public List<AnimalDTO> getAnimals() {
        List<Animal> animals = animalRepository.findAll();
        return animals.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public AnimalDTO saveAnimal(AnimalDTO animalDTO) {
        Animal animal = convertToEntity(animalDTO);
        Animal savedAnimal = animalRepository.save(animal);
        return convertToDTO(savedAnimal);
    }

    public Optional<AnimalDTO> getById(Long id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);
        return optionalAnimal.map(this::convertToDTO);
    }

    public AnimalDTO updateById(AnimalDTO request, Long id) {
        Optional<Animal> optionalAnimal = animalRepository.findById(id);

        if (optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.get();

            animal.setName(request.getName());
            animal.setAge(request.getAge());
            animal.setAdoptedBy(request.getAdoptedBy());
            animal.setAdoptedDate(request.getAdoptedDate());
            animal.setArrivalDate(request.getArrivalDate());
            animal.setHealthCondition(request.getHealthCondition());
            animal.setDewormed(request.getDewormed());
            animal.setCastration(request.getCastration());
            animal.setBirthDate(request.getBirthDate());
            animal.setVetVisitDate(request.getVetVisitDate());
            if (request.getImage() != null) {
                animal.setImage(request.getImage());
            }

            Animal updatedAnimal = animalRepository.save(animal);
            return convertToDTO(updatedAnimal);
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

    private AnimalDTO convertToDTO(Animal animal) {
        return AnimalDTO.builder()
                .name(animal.getName())
                .age(animal.getAge())
                .arrivalDate(animal.getArrivalDate())
                .adoptedDate(animal.getAdoptedDate())
                .healthCondition(animal.getHealthCondition())
                .adoptedBy(animal.getAdoptedBy())
                .dewormed(animal.getDewormed())
                .castration(animal.getCastration())
                .birthDate(animal.getBirthDate())
                .vetVisitDate(animal.getVetVisitDate())
                .image(animal.getImage())
                .build();
    }

    private Animal convertToEntity(AnimalDTO animalDTO) {
        Animal animal = new Animal();
        animal.setName(animalDTO.getName());
        animal.setAge(animalDTO.getAge());
        animal.setArrivalDate(animalDTO.getArrivalDate());
        animal.setAdoptedDate(animalDTO.getAdoptedDate());
        animal.setHealthCondition(animalDTO.getHealthCondition());
        animal.setAdoptedBy(animalDTO.getAdoptedBy());
        animal.setDewormed(animalDTO.getDewormed());
        animal.setCastration(animalDTO.getCastration());
        animal.setBirthDate(animalDTO.getBirthDate());
        animal.setVetVisitDate(animalDTO.getVetVisitDate());
        animal.setImage(animalDTO.getImage());
        return animal;
    }
}

