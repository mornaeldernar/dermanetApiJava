package com.mornaeldernar.api.mapper;

import com.mornaeldernar.api.dto.SpecialityDTO;
import com.mornaeldernar.api.entity.Speciality;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SpecialityMapper {
    SpecialityDTO toDTO(Speciality data);
    Speciality toEntity(SpecialityDTO data);
}
