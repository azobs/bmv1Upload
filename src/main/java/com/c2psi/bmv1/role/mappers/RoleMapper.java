package com.c2psi.bmv1.role.mappers;

import com.c2psi.bmv1.dto.RoleDto;
import com.c2psi.bmv1.pos.enterprise.models.Enterprise;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.role.models.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "rolePos.id", target = "rolePosId")
    @Mapping(source = "roleEnt.id", target = "roleEntId")
    RoleDto entityToDto(Role role);
    List<RoleDto> entityToDto(List<Role> role);

    Role dtoToEntity(RoleDto roleDto);
    List<Role> dtoToEntity(List<RoleDto> roleDto);
//    @Named("rolePosIdToPointofsale")
//    static Pointofsale rolePosIdToPointofsale(Long rolePosId){
//        return MapPointofsale.posIdToPointofsale(rolePosId);
//    }
//    @Named("roleEntIdToEnterprise")
//    static Enterprise roleEntIdToEnterprise(Long roleEntId){
//        return MapEnterprise.entIdToEnterprise(roleEntId);
//    }
}
