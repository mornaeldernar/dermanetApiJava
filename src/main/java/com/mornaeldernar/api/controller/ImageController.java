package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.ImageDTO;
import com.mornaeldernar.api.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/image")
@RestController
public class ImageController {
    private ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @GetMapping
    public List<ImageDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ImageDTO find(@PathVariable("id") long id) {

        try {
            return service.findById(id);
        } catch (Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }

    @PostMapping
    public ImageDTO post(@RequestBody ImageDTO data){
        return service.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody ImageDTO data) {
        try {
            log.info("Image {} updated",id);
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
            log.info("Image {} deleted",id);
            service.delete(id);
        } catch (Exception e) {
            log.error(e.toString());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.toString());
        }
    }
}
