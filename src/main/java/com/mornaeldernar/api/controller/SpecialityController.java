package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.SpecialityDTO;
import com.mornaeldernar.api.service.SpecialityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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
@RequestMapping("/speciality")
@RestController
public class SpecialityController {
    @Autowired
    private SpecialityService service;

    public SpecialityController(SpecialityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SpecialityDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialityDTO> find(@PathVariable("id") long id){

        try {
            return ResponseEntity.ok(service.findById(id));
        }catch(Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody SpecialityDTO data){
        var speciality = service.save(data);
        if(speciality == null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(speciality.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody SpecialityDTO data) {
        try {
            log.info("Speciality {} updated",id);
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
            log.info("Speciality {} deleted",id);
            service.delete(id);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
