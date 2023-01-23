package com.mornaeldernar.api.controller;

import com.mornaeldernar.api.dto.ImageDTO;
import com.mornaeldernar.api.service.ImageService;
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
@RequestMapping("/image")
@RestController
public class ImageController {
    @Autowired
    private ImageService service;

    public ImageController(ImageService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> findAll(){

        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImageDTO> find(@PathVariable("id") long id) {

        try {
            return ResponseEntity.ok(service.findById(id));
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> post(@RequestBody ImageDTO data){
        var image = service.save(data);
        if(image == null) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(image.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody ImageDTO data) {
        try {
            log.info("Image {} updated",id);
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
            log.info("Image {} deleted",id);
            service.delete(id);
        } catch (Exception e) {
            log.error(e.toString());
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
