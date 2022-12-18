package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.SpecialityDTO;
import com.mornaeldernar.api.service.SpecialityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/speciality")
@RestController
public class SpecialityController {
    private SpecialityService service;

    public SpecialityController(SpecialityService service) {
        this.service = service;
    }

    @GetMapping
    public List<SpecialityDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public SpecialityDTO find(@PathVariable("id") long id){

        try {
            return service.findById(id);
        }catch(Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }

    @PostMapping
    public SpecialityDTO post(@RequestBody SpecialityDTO data){
        return service.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody SpecialityDTO data) {
        try {
            log.info("Speciality {} updated",id);
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
            log.info("Speciality {} deleted",id);
            service.delete(id);
        } catch (Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }
}
