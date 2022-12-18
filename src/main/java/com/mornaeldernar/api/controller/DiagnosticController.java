package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.DiagnosticDTO;
import com.mornaeldernar.api.service.DiagnosticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/diagnostic")
@RestController
public class DiagnosticController {
    private DiagnosticService service;

    public DiagnosticController(DiagnosticService service) {
        this.service = service;
    }

    @GetMapping
    public List<DiagnosticDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DiagnosticDTO find(@PathVariable("id") long id){
        try {
            return service.findById(id);
        }catch(Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }

    @PostMapping
    public DiagnosticDTO post(@RequestBody DiagnosticDTO data){
        return service.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody DiagnosticDTO data) {
        try {
            log.info("Diagnostic {} updated",id);
            service.update(id, data);
        } catch (Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        try {
            log.info("Diagnostic {} deleted",id);
            service.delete(id);
        } catch (Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }
}
