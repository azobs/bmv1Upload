package com.c2psi.bmv1.pos.pos.mapper;

import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PointofsaleMapper {
    PointofsaleDto entityToDto(Pointofsale pointofsale);
    List<PointofsaleDto> entityToDto(List<Pointofsale> pointofsale);
    Pointofsale dtoToEntity(PointofsaleDto pointofsaleDto);
    List<Pointofsale> dtoToEntity(List<PointofsaleDto> pointofsaleDto);
}
