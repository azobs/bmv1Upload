package com.c2psi.bmv1.pos.enterprise.mappers;

import com.c2psi.bmv1.dto.EnterpriseDto;
import com.c2psi.bmv1.pos.enterprise.models.Enterprise;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {
    @ValueMappings({
            @ValueMapping(source = "GRP", target = "GRP"),
            @ValueMapping(source = "IL", target = "IL"),
            @ValueMapping(source = "SA", target = "SA"),
            @ValueMapping(source = "SARL", target = "SARL"),
            @ValueMapping(source = "SI", target = "SI")
    })
    EnterpriseDto entityToDto(Enterprise enterprise);
    @ValueMappings({
            @ValueMapping(source = "GRP", target = "GRP"),
            @ValueMapping(source = "IL", target = "IL"),
            @ValueMapping(source = "SA", target = "SA"),
            @ValueMapping(source = "SARL", target = "SARL"),
            @ValueMapping(source = "SI", target = "SI")
    })
    Enterprise dtoToEntity(EnterpriseDto enterpriseDto);

    List<EnterpriseDto> entityToDto(List<Enterprise> enterprise);
    List<Enterprise> dtoToEntity(List<EnterpriseDto> enterpriseDto);
}
