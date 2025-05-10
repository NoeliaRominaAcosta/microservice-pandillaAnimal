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
    FinanceMapper financeMapper;

    public List<ExpenseDTO> getExpenses(){
        List<Expense>expenses= iExpenseRepository.findAll();
        return expenses.stream().map(financeMapper::expenseToExpenseDTO).collect(Collectors.toList());
    }
    public ExpenseDTO saveExpense(ExpenseDTO expenseDTO){
        Expense expense = financeMapper.expenseDTOtoExpense(expenseDTO);
        Expense saveExpense = iExpenseRepository.save(expense);
        return financeMapper.expenseToExpenseDTO(saveExpense);
    }
    public Optional <ExpenseDTO> getById(Long id){
        Optional<Expense> optionalExpense = iExpenseRepository.findById(id);
        return optionalExpense.map(financeMapper::expenseToExpenseDTO);
    }
    public ExpenseDTO updateById(ExpenseDTO request, Long id){
        Optional<Expense> originalExpense = iExpenseRepository.findById(id);
        if (originalExpense.isPresent()){
            Expense expense = originalExpense.get();
            financeMapper.updateExpenseFromDTO(request, expense);
            Expense updateExpense = iExpenseRepository.save(expense);
            return financeMapper.expenseToExpenseDTO(updateExpense);
        }else{
            throw  new NoSuchElementException("Expense with id " + id + " NOT FOUND");
        }
    }
    public Boolean deleteById(Long id){
        try {
            iExpenseRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
