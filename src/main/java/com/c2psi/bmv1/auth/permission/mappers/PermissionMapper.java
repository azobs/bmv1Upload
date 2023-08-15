package com.c2psi.bmv1.auth.permission.mappers;

import com.c2psi.bmv1.auth.permission.models.Permission;
import com.c2psi.bmv1.dto.PermissionDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDto entityToDto(Permission permission);
    List<PermissionDto> entityToDto(List<Permission> permission);
    Permission dtoToEntity(PermissionDto permissionDto);
    List<Permission> dtoToEntity(List<PermissionDto> permissionDto);
}
