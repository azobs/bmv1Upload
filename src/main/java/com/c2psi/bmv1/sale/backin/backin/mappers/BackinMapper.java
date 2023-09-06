package com.c2psi.bmv1.sale.backin.backin.mappers;

import com.c2psi.bmv1.dto.BackinDto;
import com.c2psi.bmv1.sale.backin.backin.models.Backin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BackinMapper {
    @Mapping(source = "biCommand.id", target = "biCommandId")
    @Mapping(source = "biSaler.id", target = "biSalerId")
    BackinDto entityToDto(Backin backin);
    List<BackinDto> entityToDto(List<Backin> backin);
    Backin dtoToEntity(BackinDto backinDto);
    List<Backin> dtoToEntity(List<BackinDto> backinDto);
}
