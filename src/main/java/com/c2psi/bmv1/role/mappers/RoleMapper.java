package com.c2psi.bmv1.role.mappers;

import com.c2psi.bmv1.dto.RoleDto;
import com.c2psi.bmv1.role.models.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto entityToDto(Role role);
    List<RoleDto> entityToDto(List<Role> role);
    Role dtoToEntity(RoleDto roleDto);
    List<Role> dtoToEntity(List<RoleDto> roleDto);
}
