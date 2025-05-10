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
    FinanceMapper financeMapper;

    public List<MontlyReportDTO> getAllMontlyReports(){
        List<MontlyReport> reports = mrRepository.findAll();
        return reports.stream().map(financeMapper::montlyReportToReportDTO).collect(Collectors.toList());
    }

    public MontlyReportDTO saveMR(MontlyReportDTO montlyReportDTO){
        MontlyReport mr = financeMapper.montlyReportDTOtoReport(montlyReportDTO);
        MontlyReport saveMr = mrRepository.save(mr);
        return financeMapper.montlyReportToReportDTO(saveMr);
    }
    public Optional<MontlyReportDTO> getById(Long id){
        Optional<MontlyReport> optionalReport = mrRepository.findById(id);
        return optionalReport.map(financeMapper::montlyReportToReportDTO);
    }

    public MontlyReportDTO updateById(MontlyReportDTO request, Long id){
        Optional<MontlyReport> optionalDonation = mrRepository.findById(id);
        if (optionalDonation.isPresent()){
            MontlyReport donation = optionalDonation.get();
            financeMapper.updateMontlyReportFromDTO(request, donation);
            MontlyReport updateReport = mrRepository.save(donation);
            return financeMapper.montlyReportToReportDTO(updateReport);
        }else{
            throw  new NoSuchElementException("Report with id " + id + " NOT FOUND");
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
