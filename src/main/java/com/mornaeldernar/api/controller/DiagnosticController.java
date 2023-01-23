package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.DiagnosticDTO;
import com.mornaeldernar.api.service.DiagnosticService;
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
@RequestMapping("/diagnostic")
@RestController
public class DiagnosticController {
    @Autowired
    private DiagnosticService service;

    public DiagnosticController(DiagnosticService service) {
        this.service = service;
    }

    @GetMapping
    public List<DiagnosticDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticDTO> find(@PathVariable("id") long id){
        try {
            return ResponseEntity.ok(service.findById(id));
        }catch(Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody DiagnosticDTO data){
        var diagnostic = service.save(data);
        if(diagnostic == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(diagnostic.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody DiagnosticDTO data) {
        try {
            log.info("Diagnostic {} updated",id);
            service.update(id, data);
        } catch (Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable("id") long id) {
        try {
            log.info("Diagnostic {} deleted",id);
            service.delete(id);
        } catch (Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
        return ResponseEntity.noContent().build();
    }
}
