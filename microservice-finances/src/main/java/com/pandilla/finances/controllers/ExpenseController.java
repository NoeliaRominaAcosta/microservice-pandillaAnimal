package com.pandilla.finances.controllers;

import com.pandilla.finances.dto.ExpenseDTO;
import com.pandilla.finances.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Expense")
public class ExpenseController {
    @Autowired
    ExpenseService expenseService;

        @GetMapping(path = "/")
        public List<ExpenseDTO> getExpenses(){
            return this.expenseService.getExpenses();
        }

        @PostMapping
        public ResponseEntity<ExpenseDTO> saveExpense(@RequestBody ExpenseDTO expenseDTO){
             ExpenseDTO saveExpense = expenseService.saveExpense(expenseDTO);
            return new ResponseEntity<>(saveExpense, HttpStatus.CREATED);
        }

        @PutMapping(path = "/{id}")
        public ResponseEntity<ExpenseDTO> updateExpense(@RequestBody ExpenseDTO request, @PathVariable("id") Long id){
            ExpenseDTO updatedExpense = this.expenseService.updateById(request, id);
            return ResponseEntity.ok(updatedExpense);
        }

        @GetMapping(path = "/{id}")
        public ResponseEntity<ExpenseDTO> getExpenseById(@PathVariable Long id){
            Optional<ExpenseDTO> expenseDTO = this.expenseService.getById(id);
            return expenseDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping(path = "/{id}")
        public ResponseEntity<String> deleteDonationById(@PathVariable("id") Long id){
            boolean deleted = expenseService.deleteById(id);
            String message = "Expense deleted with id " + id + (deleted ? " is deleted" : " could not be deleted");
            HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(message);
        }
}