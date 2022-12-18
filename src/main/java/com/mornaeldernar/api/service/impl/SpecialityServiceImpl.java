package com.mornaeldernar.api.service.impl;

import com.mornaeldernar.api.dto.SpecialityDTO;
import com.mornaeldernar.api.entity.Patient;
import com.mornaeldernar.api.entity.Speciality;
import com.mornaeldernar.api.mapper.SpecialityMapper;
import com.mornaeldernar.api.repository.SpecialityRepository;
import com.mornaeldernar.api.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialityServiceImpl implements SpecialityService {

    private SpecialityMapper mapper;
    private SpecialityRepository repository;

    @Autowired
    public SpecialityServiceImpl(SpecialityMapper mapper, SpecialityRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<SpecialityDTO> findAll(){
        List<Speciality> entities = repository.findAll();
        return entities.stream().map(mapper::toDTO).toList();
    }

    public SpecialityDTO findById(long id) throws Exception {
        Optional<Speciality> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró el diagnostico con id ");
        }
        return mapper.toDTO(entity.get());
    }

    public SpecialityDTO save(SpecialityDTO data) {
        Speciality entity = mapper.toEntity(data);
        return mapper.toDTO(repository.save(entity));
    }

    public void update(long id, SpecialityDTO data) throws Exception{
        Optional<Speciality> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró la especialidad con id ");
        }
        data.setId(entity.get().getId());
        repository.save(mapper.toEntity(data));
    }
    public void delete(long id) throws Exception {
        Optional<Speciality> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new Exception("No se encontró la especialidad con el id "+id);
        }
        repository.deleteById(id);
    }
}
