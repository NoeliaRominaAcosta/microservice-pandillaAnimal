package com.pandilla.finances.services;

import com.netflix.discovery.converters.Auto;
import com.pandilla.finances.dto.MontlyReportDTO;
import com.pandilla.finances.mappers.FinanceMapper;
import com.pandilla.finances.models.MontlyReport;
import com.pandilla.finances.repository.IMontlyReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MontlyReportService {
    @Autowired
    IMontlyReportRepository mrRepository;
    @Autowired
    FinanceMapper financeMapperManual;

    public List<MontlyReportDTO> getAllMontlyReports() {
        List<MontlyReport> reports = mrRepository.findAll();
        return reports.stream()
                .map(financeMapperManual::montlyReportToReportDTO)
                .collect(Collectors.toList());
    }

    public MontlyReportDTO saveMR(MontlyReportDTO dto) {
        MontlyReport entity = financeMapperManual.montlyReportDTOtoReport(dto);
        MontlyReport saved = mrRepository.save(entity);
        return financeMapperManual.montlyReportToReportDTO(saved);
    }


    public Optional<MontlyReportDTO> getById(Long id) {
        Optional<MontlyReport> optionalReport = mrRepository.findById(id);
        return optionalReport.map(financeMapperManual::montlyReportToReportDTO);
    }

    public MontlyReportDTO updateById(MontlyReportDTO request, Long id) {
        Optional<MontlyReport> optionalReport = mrRepository.findById(id);
        if (optionalReport.isPresent()) {
            MontlyReport report = optionalReport.get();

            report.setDate(request.getDate());
            report.setTotalExpences(request.getTotalExpences());
            report.setTotalDonations(request.getTotalDonations());
            report.setBalance(request.getBalance());
            report.setGeneratedDate(request.getGeneratedDate());

            MontlyReport updatedReport = mrRepository.save(report);

            return financeMapperManual.montlyReportToReportDTO(updatedReport);
        } else {
            throw new NoSuchElementException("Report with id " + id + " NOT FOUND");
        }
    }
    public Boolean deleteById(Long id){
        try {
            mrRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
