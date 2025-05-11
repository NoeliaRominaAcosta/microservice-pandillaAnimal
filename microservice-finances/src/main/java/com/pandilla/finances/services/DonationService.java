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
    FinanceMapper financeMapperManual;

    // Obtener todas las donaciones
    public List<DonationDTO> getDonations() {
        List<Donation> donations = iDonationRepository.findAll();
        return donations.stream()
                .map(financeMapperManual::donationToDonationDTO)
                .collect(Collectors.toList());
    }

    // Guardar una nueva donación
    public DonationDTO saveDonation(DonationDTO donationDTO) {
        // Mapeo manual de DTO a entidad
        Donation donation = new Donation();
        donation.setDate(donationDTO.getDate());
        donation.setDonorName(donationDTO.getDonorName());
        donation.setDonorEmail(donationDTO.getDonorEmail());
        donation.setDonorPhone(donationDTO.getDonorPhone());
        donation.setAmount(donationDTO.getAmount());
        donation.setType(donationDTO.getType());
        donation.setNotes(donationDTO.getNotes());
        donation.setReceiptSent(donationDTO.getReceiptSent());

        // Guardamos la donación
        Donation savedDonation = iDonationRepository.save(donation);

        // Mapeo manual de la entidad a DTO
        return financeMapperManual.donationToDonationDTO(savedDonation);
    }

    // Obtener una donación por id
    public Optional<DonationDTO> getById(Long id) {
        Optional<Donation> optionalDonation = iDonationRepository.findById(id);
        return optionalDonation.map(financeMapperManual::donationToDonationDTO);
    }

    // Actualizar una donación por id
    public DonationDTO updateById(DonationDTO request, Long id) {
        Optional<Donation> originalDonation = iDonationRepository.findById(id);
        if (originalDonation.isPresent()) {
            Donation donation = originalDonation.get();

            // Mapeo manual de DTO a entidad (actualizando los valores)
            donation.setDate(request.getDate());
            donation.setDonorName(request.getDonorName());
            donation.setDonorEmail(request.getDonorEmail());
            donation.setDonorPhone(request.getDonorPhone());
            donation.setAmount(request.getAmount());
            donation.setType(request.getType());
            donation.setNotes(request.getNotes());
            donation.setReceiptSent(request.getReceiptSent());

            // Guardamos la entidad actualizada
            Donation updatedDonation = iDonationRepository.save(donation);

            // Mapeo manual de la entidad actualizada a DTO
            return financeMapperManual.donationToDonationDTO(updatedDonation);
        } else {
            throw new NoSuchElementException("Donation with id " + id + " NOT FOUND");
        }
    }

    // Eliminar una donación por id
    public Boolean deleteById(Long id) {
        try {
            iDonationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
