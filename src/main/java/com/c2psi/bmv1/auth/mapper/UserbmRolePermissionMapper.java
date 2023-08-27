package com.c2psi.bmv1.auth.mapper;

import com.c2psi.bmv1.dto.UserbmRolePermissionDto;
import com.c2psi.bmv1.auth.models.UserbmRolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserbmRolePermissionMapper {
    @Mapping(source = "userbmRole.id", target = "userbmroleId")
    @Mapping(source = "permission.id", target = "permissionId")
    UserbmRolePermissionDto entityToDto(UserbmRolePermission userbmRolePermission);
    List<UserbmRolePermissionDto> entityToDto(List<UserbmRolePermission> userbmRolePermission);
    UserbmRolePermission dtoToEntity(UserbmRolePermissionDto userbmRolePermissionDto);
    List<UserbmRolePermission> dtoToEntity(List<UserbmRolePermissionDto> userbmRolePermissionDto);
}
