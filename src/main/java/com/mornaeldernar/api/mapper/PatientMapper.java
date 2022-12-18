package com.mornaeldernar.api.mapper;

import com.mornaeldernar.api.dto.PatientDTO;
import com.mornaeldernar.api.entity.Patient;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface PatientMapper {
    PatientDTO toDTO(Patient data);
    Patient toEntity(PatientDTO data);
}
