package com.mornaeldernar.api.mapper;

import com.mornaeldernar.api.dto.ImageDTO;
import com.mornaeldernar.api.entity.Image;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ImageMapper {
    ImageDTO toDTO(Image data);
    Image toEntity(ImageDTO data);
}
