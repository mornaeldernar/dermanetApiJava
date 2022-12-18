package com.mornaeldernar.api.service;


import com.mornaeldernar.api.dto.DiagnosticDTO;

import java.util.List;
import java.util.Optional;


public interface DiagnosticService {
    
    List<DiagnosticDTO> findAll();

    DiagnosticDTO findById(long id) throws Exception;

    DiagnosticDTO save(DiagnosticDTO data);

    void update(long id, DiagnosticDTO data) throws Exception;

    void delete(long id) throws Exception;

}
