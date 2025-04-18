package com.pandilla.finances.repository;

import com.pandilla.finances.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, Long> {
}
