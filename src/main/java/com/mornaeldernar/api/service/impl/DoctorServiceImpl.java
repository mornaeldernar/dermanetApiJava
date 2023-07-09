package com.mornaeldernar.api.service.impl;


import com.mornaeldernar.api.dto.DoctorDTO;
import com.mornaeldernar.api.entity.Doctor;
import com.mornaeldernar.api.mapper.DoctorMapper;
import com.mornaeldernar.api.repository.DoctorRepository;
import com.mornaeldernar.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorMapper mapper;
    private DoctorRepository repository;

    @Autowired
    public DoctorServiceImpl(DoctorMapper mapper, DoctorRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<DoctorDTO> findAll(){
        List<Doctor> entities = repository.findAll();
        return entities.stream().map(mapper::toDTO).toList();
    }

    public DoctorDTO findById(long id) throws Exception {
        Optional<Doctor> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró el diagnostico con id ");
        }
        return mapper.toDTO(entity.get());
    }

    public DoctorDTO save(DoctorDTO data) {
        Doctor entity = mapper.toEntity(data);
        return mapper.toDTO(repository.save(entity));
    }

    public void update(long id, DoctorDTO data) throws Exception{
        Optional<Doctor> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró el doctor con id ");
        }
        data.setId(entity.get().getId());
        repository.save(mapper.toEntity(data));
    }
    public void delete(long id) throws Exception {
        Optional<Doctor> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró el doctor con el id "+id);
        }
        repository.deleteById(id);
    }
}
