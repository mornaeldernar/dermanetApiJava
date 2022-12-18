package com.mornaeldernar.api.service;


import com.mornaeldernar.api.dto.ImageDTO;

import java.util.List;
import java.util.Optional;


public interface ImageService {
    
    List<ImageDTO> findAll();

    ImageDTO findById(long id) throws Exception;

    ImageDTO save(ImageDTO data);

    void update(long id, ImageDTO data) throws Exception;

    void delete(long id) throws Exception;

}
