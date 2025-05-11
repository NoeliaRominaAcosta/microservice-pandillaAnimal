package com.pandilla.finances.services;

import com.pandilla.finances.dto.ExpenseDTO;
import com.pandilla.finances.mappers.FinanceMapper;
import com.pandilla.finances.models.Expense;
import com.pandilla.finances.repository.IExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    IExpenseRepository iExpenseRepository;

    @Autowired
    FinanceMapper financeMapperManual;

    // Obtener todas las expenses
    public List<ExpenseDTO> getExpenses() {
        List<Expense> expenses = iExpenseRepository.findAll();
        return expenses.stream()
                .map(expense -> financeMapperManual.expenseToExpenseDTO(expense))
                .collect(Collectors.toList());
    }

    // Guardar una nueva expense
    public ExpenseDTO saveExpense(ExpenseDTO expenseDTO) {
        // Mapeo manual de DTO a entidad
        Expense expense = new Expense();
        expense.setDate(expenseDTO.getDate());
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setApprovedBy(expenseDTO.getApprovedBy());

        // Guardamos la expense
        Expense savedExpense = iExpenseRepository.save(expense);

        // Mapeo manual de la entidad a DTO
        return financeMapperManual.expenseToExpenseDTO(savedExpense);
    }

    // Obtener una expense por id
    public Optional<ExpenseDTO> getById(Long id) {
        Optional<Expense> optionalExpense = iExpenseRepository.findById(id);
        return optionalExpense.map(financeMapperManual::expenseToExpenseDTO);
    }

    // Actualizar una expense por id
    public ExpenseDTO updateById(ExpenseDTO request, Long id) {
        Optional<Expense> originalExpense = iExpenseRepository.findById(id);
        if (originalExpense.isPresent()) {
            Expense expense = originalExpense.get();

            // Mapeo manual de DTO a entidad (actualizando los valores)
            expense.setDate(request.getDate());
            expense.setDescription(request.getDescription());
            expense.setAmount(request.getAmount());
            expense.setCategory(request.getCategory());
            expense.setApprovedBy(request.getApprovedBy());

            // Guardamos la entidad actualizada
            Expense updatedExpense = iExpenseRepository.save(expense);

            // Mapeo manual de la entidad actualizada a DTO
            return financeMapperManual.expenseToExpenseDTO(updatedExpense);
        } else {
            throw new NoSuchElementException("Expense with id " + id + " NOT FOUND");
        }
    }

    // Eliminar una expense por id
    public Boolean deleteById(Long id) {
        try {
            iExpenseRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
