package com.pandilla.finances.services;

import com.pandilla.finances.dto.DonationDTO;
import com.pandilla.finances.mappers.FinanceMapper;
import com.pandilla.finances.models.Donation;
import com.pandilla.finances.repository.IDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DonationService {
    @Autowired
    IDonationRepository iDonationRepository;
    @Autowired
    FinanceMapper financeMapper;

    public List<DonationDTO> getDonations(){
        List<Donation> donations = iDonationRepository.findAll();
        return donations.stream().map(financeMapper::donationToDonationDTO).collect(Collectors.toList());
    }

    public DonationDTO saveDonation(DonationDTO donationDTO){
        Donation donation = financeMapper.donationDTOtoDonation(donationDTO);
        Donation saveDonation = iDonationRepository.save(donation);
        return financeMapper.donationToDonationDTO(saveDonation);
    }

    public Optional <DonationDTO> getById(Long id){
        Optional<Donation> optionalDonation = iDonationRepository.findById(id);
        return optionalDonation.map(financeMapper::donationToDonationDTO);
    }

    public DonationDTO updateById(DonationDTO request, Long id){
        Optional<Donation> optionalDonation = iDonationRepository.findById(id);
        if (optionalDonation.isPresent()){
            Donation donation = optionalDonation.get();
            financeMapper.updateDonationFromDTO(request, donation);
            Donation updateDonation = iDonationRepository.save(donation);
            return financeMapper.donationToDonationDTO(updateDonation);
        }else{
            throw  new NoSuchElementException("Donation with id " + id + " NOT FOUND");
        }
    }

    public Boolean deleteById(Long id){
        try {
            iDonationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
