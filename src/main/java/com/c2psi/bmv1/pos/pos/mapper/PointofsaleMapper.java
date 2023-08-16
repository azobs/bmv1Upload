package com.c2psi.bmv1.pos.pos.mapper;

import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.pos.enterprise.models.Enterprise;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PointofsaleMapper {
    @Mapping(source = "posEnterprise.id", target = "posEnterpriseId")
    PointofsaleDto entityToDto(Pointofsale pointofsale);
    List<PointofsaleDto> entityToDto(List<Pointofsale> pointofsale);
//    @Mapping(source = "posEnterpriseId", target = "posEnterprise", qualifiedByName = "entIdToEnterprise")
    Pointofsale dtoToEntity(PointofsaleDto pointofsaleDto);
    List<Pointofsale> dtoToEntity(List<PointofsaleDto> pointofsaleDto);
//    @Named("entIdToEnterprise")
//    static Enterprise entIdToEnterprise(Long entId){
//        return MapEnterprise.entIdToEnterprise(entId);
//    }
}
