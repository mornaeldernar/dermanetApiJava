package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.PatientDTO;
import com.mornaeldernar.api.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
@Slf4j
@RequestMapping("/patient")
@RestController
public class PatientController {
    @Autowired
    private PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> find(@PathVariable("id") long id) {

        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody PatientDTO data){
        var patient = service.save(data);
        if(patient == null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody PatientDTO data) {
        try {
            log.info("Patient {} updated",id);
            service.update(id, data);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable("id") long id){
        try {
            log.info("Patient {} deleted",id);
            service.delete(id);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
