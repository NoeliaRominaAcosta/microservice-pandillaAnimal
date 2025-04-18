package com.pandilla.finances.repository;

import com.pandilla.finances.models.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IDonationRepository extends JpaRepository<Donation, Long> {
}
