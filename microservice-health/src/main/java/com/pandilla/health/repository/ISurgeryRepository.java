package com.pandilla.health.repository;

import com.pandilla.health.models.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface ISurgeryRepository extends JpaRepository<Surgery, Long> {
}
