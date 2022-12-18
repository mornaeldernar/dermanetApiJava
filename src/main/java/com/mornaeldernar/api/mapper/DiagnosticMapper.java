package com.mornaeldernar.api.mapper;

import com.mornaeldernar.api.dto.DiagnosticDTO;
import com.mornaeldernar.api.entity.Diagnostic;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DiagnosticMapper {
    DiagnosticDTO toDTO(Diagnostic data);
    Diagnostic toEntity(DiagnosticDTO data);
}
