package com.c2psi.bmv1.product.unit.unit.mapper;

import com.c2psi.bmv1.dto.UnitDto;
import com.c2psi.bmv1.product.unit.unit.models.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnitMapper {
    @Mapping(source = "unitPos.id", target = "unitPosId")
    UnitDto entityToDto(Unit unit);
    List<UnitDto> entityToDto(List<Unit> unit);
    Unit dtoToEntity(UnitDto unitDto);
    List<Unit> dtoToEntity(List<UnitDto> unitDto);
}
