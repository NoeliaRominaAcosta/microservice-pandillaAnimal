package com.pandilla.health.repository;

import com.pandilla.health.models.VetVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVetVisitRepository extends JpaRepository<VetVisit, Long> {
}
