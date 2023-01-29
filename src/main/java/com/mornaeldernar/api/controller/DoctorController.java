package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.DoctorDTO;
import com.mornaeldernar.api.service.DoctorService;
import jakarta.validation.Valid;
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
@RequestMapping("/doctor")
@RestController
public class DoctorController {
    @Autowired
    private DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> findAll(){

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> find(@PathVariable("id") long id) {

        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> post(@Valid @RequestBody DoctorDTO data){
        var doctor = service.save(data);
        if(doctor == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @Valid @RequestBody DoctorDTO data) {
        try {
            log.info("Doctor {} updated",id);
            service.update(id, data);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        try {
            log.info("Doctor {} deleted",id);
            service.delete(id);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
