package com.c2psi.bmv1.product.unit.unitconversion.mapper;

import com.c2psi.bmv1.dto.UnitconversionDto;
import com.c2psi.bmv1.product.unit.unitconversion.models.Unitconversion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnitconversionMapper {
    @Mapping(source = "unitSource.id", target = "unitSourceId")
    @Mapping(source = "unitDestination.id", target = "unitDestinationId")
    UnitconversionDto entityToDto(Unitconversion unitconversion);
    List<UnitconversionDto> entityToDto(List<Unitconversion> unitconversion);
    Unitconversion dtoToEntity(UnitconversionDto unitconversionDto);
    List<Unitconversion> dtoToEntity(List<UnitconversionDto> unitconversionDto);
}
