package com.mornaeldernar.api.service.impl;

import com.mornaeldernar.api.dto.DiagnosticDTO;
import com.mornaeldernar.api.entity.Diagnostic;
import com.mornaeldernar.api.mapper.DiagnosticMapper;
import com.mornaeldernar.api.repository.DiagnosticRepository;
import com.mornaeldernar.api.service.DiagnosticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DiagnosticServiceImpl implements DiagnosticService {

    private DiagnosticMapper mapper;
    private DiagnosticRepository repository;

    @Autowired
    public DiagnosticServiceImpl(DiagnosticMapper mapper, DiagnosticRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<DiagnosticDTO> findAll(){
        List<Diagnostic> entities = repository.findAll();

        return entities.stream().map(mapper::toDTO).toList();
    }

    public DiagnosticDTO findById(long id) throws Exception {
        Optional<Diagnostic> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró el diagnostico con id ");
        }
        return mapper.toDTO(entity.get());
    }

    public DiagnosticDTO save(DiagnosticDTO data) {
        Diagnostic entity = mapper.toEntity(data);
        return mapper.toDTO(repository.save(entity));
    }

    public void update(long id, DiagnosticDTO data) throws Exception{
        Optional<Diagnostic> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró el diagnostico con id ");
        }
        data.setId(entity.get().getId());
        repository.save(mapper.toEntity(data));
    }
    public void delete(long id) throws Exception {
        Optional<Diagnostic> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró el diagnostico con el id "+id);
        }
        repository.deleteById(id);
    }
}
