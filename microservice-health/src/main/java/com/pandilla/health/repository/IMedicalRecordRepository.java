package com.pandilla.health.repository;

import com.pandilla.health.models.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IMedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
}
