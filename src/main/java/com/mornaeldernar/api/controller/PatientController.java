package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.PatientDTO;
import com.mornaeldernar.api.service.PatientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<Page<PatientDTO>> findAll(@RequestParam(required = false) String name, @RequestParam int page, @RequestParam int size){
        PageRequest pr = PageRequest.of(page,size);
        if (name == null) {
            return ResponseEntity.ok(service.findAll(pr));
        } else {
            return ResponseEntity.ok(service.findAllByName(name,pr));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> find(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            log.error(e.toString()+" id: "+id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> post(@Valid @RequestBody PatientDTO data){
        var patient = service.save(data);
        if(patient == null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable("id") long id,@Valid  @RequestBody PatientDTO data) {
        try {
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
