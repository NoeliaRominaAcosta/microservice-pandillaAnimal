package com.pandilla.finances.mappers;

import com.pandilla.finances.dto.DonationDTO;
import com.pandilla.finances.dto.ExpenseDTO;
import com.pandilla.finances.dto.MontlyReportDTO;
import com.pandilla.finances.models.Donation;
import com.pandilla.finances.models.Expense;
import com.pandilla.finances.models.MontlyReport;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
public class FinanceMapper { // Mapeo manual de Donation a DonationDTO
    public DonationDTO donationToDonationDTO(Donation donation) {
        if (donation == null) return null;
        return new DonationDTO(
                donation.getId(),
                donation.getDate(),
                donation.getDonorName(),
                donation.getDonorEmail(),
                donation.getDonorPhone(),
                donation.getAmount(),
                donation.getType(),
                donation.getNotes(),
                donation.getReceiptSent()
        );
    }

    // Mapeo manual de DonationDTO a Donation
    public Donation donationDTOtoDonation(DonationDTO donationDTO) {
        if (donationDTO == null) return null;
        return new Donation(
                donationDTO.getId(),
                donationDTO.getDate(),
                donationDTO.getDonorName(),
                donationDTO.getDonorEmail(),
                donationDTO.getDonorPhone(),
                donationDTO.getAmount(),
                donationDTO.getType(),
                donationDTO.getNotes(),
                donationDTO.getReceiptSent()
        );
    }

    // Mapeo manual de Expense a ExpenseDTO
    public ExpenseDTO expenseToExpenseDTO(Expense expense) {
        if (expense == null) return null;
        return new ExpenseDTO(
                expense.getId(),
                expense.getDate(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getApprovedBy()
        );
    }

    // Mapeo manual de ExpenseDTO a Expense
    public Expense expenseDTOtoExpense(ExpenseDTO expenseDTO) {
        if (expenseDTO == null) return null;
        return new Expense(
                expenseDTO.getId(),
                expenseDTO.getDate(),
                expenseDTO.getDescription(),
                expenseDTO.getAmount(),
                expenseDTO.getCategory(),
                expenseDTO.getApprovedBy()
        );
    }

    // Mapeo manual de MontlyReport a MontlyReportDTO
    public MontlyReportDTO montlyReportToReportDTO(MontlyReport montlyReport) {
        if (montlyReport == null) return null;
        return new MontlyReportDTO(
                montlyReport.getId(),
                montlyReport.getDate(),
                montlyReport.getTotalExpences(),
                montlyReport.getTotalDonations(),
                montlyReport.getBalance(),
                montlyReport.getGeneratedDate()
        );
    }

    // Mapeo manual de MontlyReportDTO a MontlyReport
    public MontlyReport montlyReportDTOtoReport(MontlyReportDTO montlyReportDTO) {
        if (montlyReportDTO == null) return null;
        return new MontlyReport(
                montlyReportDTO.getId(),
                montlyReportDTO.getDate(),
                montlyReportDTO.getTotalExpences(),
                montlyReportDTO.getTotalDonations(),
                montlyReportDTO.getBalance(),
                montlyReportDTO.getGeneratedDate()
        );
    }

}
