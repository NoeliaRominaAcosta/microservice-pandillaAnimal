package com.pandilla.finances.mappers;

import com.pandilla.finances.dto.DonationDTO;
import com.pandilla.finances.dto.ExpenseDTO;
import com.pandilla.finances.dto.MontlyReportDTO;
import com.pandilla.finances.models.Donation;
import com.pandilla.finances.models.Expense;
import com.pandilla.finances.models.MontlyReport;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FinanceMapper {
    ExpenseDTO expenseToExpenseDTO(Expense expense);
    Expense expenseDTOtoExpense(ExpenseDTO expenseDTO);
    void updateExpenseFromDTO(ExpenseDTO expenseDTO, @MappingTarget Expense expense);

    DonationDTO donationToDonationDTO(Donation donation);
    Donation donationDTOtoDonation(DonationDTO donationDTO);
    void updateDonationFromDTO(DonationDTO donationDTO, @MappingTarget Donation donation);

    MontlyReportDTO montlyReportToReportDTO(MontlyReport montlyReport);
    MontlyReport montlyReportDTOtoReport(MontlyReportDTO montlyReportDTO);
    void updateMontlyReportFromDTO(MontlyReportDTO reportDTO, @MappingTarget MontlyReport montlyReport);

}
