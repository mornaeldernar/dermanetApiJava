package com.mornaeldernar.api.service;



import com.mornaeldernar.api.dto.SpecialityDTO;

import java.util.List;
import java.util.Optional;


public interface SpecialityService {
    
    List<SpecialityDTO> findAll();

    SpecialityDTO findById(long id) throws Exception;

    SpecialityDTO save(SpecialityDTO data);

    void update(long id, SpecialityDTO data) throws Exception;

    void delete(long id) throws Exception;

}
