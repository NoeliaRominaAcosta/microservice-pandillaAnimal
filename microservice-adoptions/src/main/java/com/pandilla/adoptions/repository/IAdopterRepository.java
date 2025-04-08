package com.pandilla.adoptions.repository;

import com.pandilla.adoptions.models.Adopter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdopterRepository extends JpaRepository<Adopter, Long> {
}
