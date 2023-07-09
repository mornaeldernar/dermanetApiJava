package com.mornaeldernar.api.service.impl;

import com.mornaeldernar.api.dto.PatientDTO;
import com.mornaeldernar.api.entity.Patient;
import com.mornaeldernar.api.mapper.PatientMapper;
import com.mornaeldernar.api.repository.PatientRepository;
import com.mornaeldernar.api.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
public class PatientServiceImpl implements PatientService {

    private PatientMapper mapper;
    private PatientRepository repository;

    @Autowired
    public PatientServiceImpl(PatientMapper mapper, PatientRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    public Page<PatientDTO> findAll(Pageable pageable ){
        Page<Patient> patients =  repository.findAll(pageable);
        return patients.map(mapper::toDTO);
    }

    public Page<PatientDTO> findAllByName(String name, Pageable pageable ){

        Page<Patient> patients =  repository.findByNameContaining(name,pageable);
        return patients.map(mapper::toDTO);
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
        entity.setPhone(data.getPhone());
        entity.setSex(data.getSex());
        entity.setProfesion(data.getProfesion());
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
