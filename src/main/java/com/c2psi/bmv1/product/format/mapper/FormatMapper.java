package com.c2psi.bmv1.product.format.mapper;

import com.c2psi.bmv1.dto.FormatDto;
import com.c2psi.bmv1.product.format.models.Format;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FormatMapper {
    @Mapping(source = "formatPos.id", target = "formatPosId")
    FormatDto entityToDto(Format format);
    List<FormatDto> entityToDto(List<Format> format);
    Format dtoToEntity(FormatDto formatDto);
    List<Format> dtoToEntity(List<FormatDto> formatDto);
}
