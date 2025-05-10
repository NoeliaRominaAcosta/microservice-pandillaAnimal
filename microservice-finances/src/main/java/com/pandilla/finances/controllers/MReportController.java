package com.pandilla.finances.controllers;

import com.pandilla.finances.dto.MontlyReportDTO;
import com.pandilla.finances.services.MontlyReportService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Report")
public class MReportController {

    @Autowired
    MontlyReportService mrService;

    @GetMapping(path="/")
    public List<MontlyReportDTO> getReports(){
        return this.mrService.getAllMontlyReports();
    }

    @PostMapping
    public ResponseEntity<MontlyReportDTO> saveReport(@RequestBody MontlyReportDTO mrDTO){
        MontlyReportDTO saveReport = mrService.saveMR(mrDTO);
        return new ResponseEntity<>(saveReport, HttpStatus.CREATED);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<MontlyReportDTO> getMrById(@PathVariable Long id){
        Optional<MontlyReportDTO> mrDTO = this.mrService.getById(id);
        return mrDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping(path= "/{id}")
    public ResponseEntity<MontlyReportDTO> updateReport(@RequestBody MontlyReportDTO request, @PathVariable Long id){
        MontlyReportDTO updatedReport = this.mrService.updateById(request, id);
        return ResponseEntity.ok(updatedReport);
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteDonationById(@PathVariable("id") Long id){
        boolean deleted = mrService.deleteById(id);
        String message = "Report deleted with id " + id + (deleted ? " is deleted" : " could not be deleted");
        HttpStatus status = deleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(message);
    }

}