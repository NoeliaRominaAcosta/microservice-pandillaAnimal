package com.pandilla.finances.repository;

import com.pandilla.finances.models.MontlyReport;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IMontlyReportRepository extends JpaRepository<MontlyReport, Long> {
}
