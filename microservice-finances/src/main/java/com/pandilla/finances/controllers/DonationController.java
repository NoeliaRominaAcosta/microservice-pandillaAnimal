package com.pandilla.finances.controllers;

import com.pandilla.finances.dto.DonationDTO;
import com.pandilla.finances.models.Donation;
import com.pandilla.finances.services.DonationService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/Donation")
    public class DonationController {
        @Autowired
        DonationService donationService;

        @GetMapping(path ="/")
        public List<DonationDTO> getDonations(){
            return this.donationService.getDonations();
        }

        @PostMapping
        public ResponseEntity<DonationDTO> saveDonation(@RequestBody DonationDTO donationDTO){
        DonationDTO saveDonation = donationService.saveDonation(donationDTO);
        return new ResponseEntity<>(saveDonation, HttpStatus.CREATED);
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<DonationDTO> getDonationsById(@PathVariable Long id){
        Optional<DonationDTO> donationDTO = this.donationService.getById(id);
        return donationDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<DonationDTO> updateDonation(@RequestBody DonationDTO request, @PathVariable("id") Long id){
        DonationDTO updatedDonation = this.donationService.updateById(request, id);
        return ResponseEntity.ok(updatedDonation);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDonationById(@PathVariable("id") Long id){
        boolean deleted = donationService.deleteById(id);
        String message = "Donation deleted with id " + id + (deleted ? " is deleted" : " could not be deleted");
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(message);
    }
}
