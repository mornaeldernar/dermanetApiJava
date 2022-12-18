package com.mornaeldernar.api.service.impl;

import com.mornaeldernar.api.dto.PatientDTO;
import com.mornaeldernar.api.entity.Image;
import com.mornaeldernar.api.entity.Patient;
import com.mornaeldernar.api.mapper.PatientMapper;
import com.mornaeldernar.api.repository.PatientRepository;
import com.mornaeldernar.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientMapper mapper;
    private PatientRepository repository;

    @Autowired
    public PatientServiceImpl(PatientMapper mapper, PatientRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<PatientDTO> findAll(){
        List<Patient> patients = repository.findAll();
        return patients.stream().map(mapper::toDTO).toList();
    }

    public PatientDTO findById(long id) throws Exception {
        Optional<Patient> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró el diagnostico con id ");
        }
        return mapper.toDTO(entity.get());
    }

    public PatientDTO save(PatientDTO data) {
        Patient entity = mapper.toEntity(data);
        return mapper.toDTO(repository.save(entity));
    }

    public void update(long id, PatientDTO data) throws Exception{
        Optional<Patient> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró el paciente con id ");
        }
        data.setId(entity.get().getId());
        repository.save(mapper.toEntity(data));
    }
    public void delete(long id) throws Exception {
        Optional<Patient> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontro el paciente con el id "+id);
        }
        repository.deleteById(id);
    }
}
