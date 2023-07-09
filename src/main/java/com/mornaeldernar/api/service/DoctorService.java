package com.mornaeldernar.api.service;


import com.mornaeldernar.api.dto.DoctorDTO;

import java.util.List;


public interface DoctorService {
    
    List<DoctorDTO> findAll();

    DoctorDTO findById(long id) throws Exception;

    DoctorDTO save(DoctorDTO data);

    void update(long id, DoctorDTO data) throws Exception;

    void delete(long id) throws Exception;

}
