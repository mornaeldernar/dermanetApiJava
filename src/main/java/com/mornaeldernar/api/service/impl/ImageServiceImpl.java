package com.mornaeldernar.api.service.impl;


import com.mornaeldernar.api.dto.ImageDTO;
import com.mornaeldernar.api.entity.Doctor;
import com.mornaeldernar.api.entity.Image;
import com.mornaeldernar.api.mapper.ImageMapper;
import com.mornaeldernar.api.repository.ImageRepository;
import com.mornaeldernar.api.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageMapper mapper;
    private ImageRepository repository;

    @Autowired
    public ImageServiceImpl(ImageMapper mapper, ImageRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<ImageDTO> findAll(){
        List<Image> entities = repository.findAll();
        return entities.stream().map(mapper::toDTO).toList();
    }

    public ImageDTO findById(long id) throws Exception {
        Optional<Image> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró el diagnostico con id ");
        }
        return mapper.toDTO(entity.get());
    }

    public ImageDTO save(ImageDTO data) {
        Image entity = mapper.toEntity(data);
        return mapper.toDTO(repository.save(entity));
    }

    public void update(long id, ImageDTO data) throws Exception{
        Optional<Image> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró la imagen con id ");
        }
        data.setId(entity.get().getId());
        repository.save(mapper.toEntity(data));
    }
    public void delete(long id) throws Exception {
        Optional<Image> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró la imagen con el id "+id);
        }
        repository.deleteById(id);
    }
}
