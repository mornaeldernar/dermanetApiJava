package com.mornaeldernar.api.service;

import com.mornaeldernar.api.dto.PatientDTO;

import java.util.List;
import java.util.Optional;


public interface PatientService {
    
    List<PatientDTO> findAll();

    PatientDTO findById(long id) throws Exception;

    PatientDTO save(PatientDTO data);

    void update(long id, PatientDTO data) throws Exception;

    void delete(long id) throws Exception;

}
