package com.pandilla.health.repository;

import com.pandilla.health.models.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IVaccinationRepository extends JpaRepository<Vaccination, Long> {
}
