package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.PatientDTO;
import com.mornaeldernar.api.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/patients")
@RestController
public class PatientController {
    private PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    @GetMapping
    public List<PatientDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PatientDTO find(@PathVariable("id") long id) {

        try {
            return service.findById(id);
        } catch (Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }

    @PostMapping
    public PatientDTO post(@RequestBody PatientDTO data){
        return service.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody PatientDTO data) {
        try {
            log.info("Patient {} updated",id);
            service.update(id, data);
        } catch (Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        try {
            log.info("Patient {} deleted",id);
            service.delete(id);
        } catch (Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }
}
