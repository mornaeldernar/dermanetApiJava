package com.mornaeldernar.api.service;

import com.mornaeldernar.api.dto.PatientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PatientService {

    Page<PatientDTO> findAll(Pageable pageable );
    Page<PatientDTO> findAllByName(String name, Pageable pageable );

    PatientDTO findById(long id) throws Exception;

    PatientDTO save(PatientDTO data);

    void update(long id, PatientDTO data) throws Exception;

    void delete(long id) throws Exception;

}
