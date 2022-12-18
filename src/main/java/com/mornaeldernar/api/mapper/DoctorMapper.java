package com.mornaeldernar.api.mapper;

import com.mornaeldernar.api.dto.DoctorDTO;
import com.mornaeldernar.api.entity.Doctor;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DoctorMapper {
    DoctorDTO toDTO(Doctor data);
    Doctor toEntity(DoctorDTO data);
}
