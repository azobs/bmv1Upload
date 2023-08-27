package com.c2psi.bmv1.auth.services;

import com.c2psi.bmv1.auth.models.ExtendedUser;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.UserbmRolePermissionDto;

import java.util.List;

public interface UserbmRolePermissionService {
    UserbmRolePermissionDto saveUserbmRolePermission(UserbmRolePermissionDto ubmRolePermDto);
    Boolean deleteUserbmRolePermissionById(Long id);
    UserbmRolePermissionDto getUserbmRolePermissionById(Long id);
    UserbmRolePermissionDto getUserbmRolePermissionByUserbmRoleAndPermission(Long userbmRoleId, Long permId);
    List<UserbmRolePermissionDto> getListofUserbmRolePermission(FilterRequest filterRequest);
    Boolean isUserbmRolePermissionExistWithId(Long userbmRolePermId);
    List<UserbmRolePermissionDto> getPermissionofUserbmRole(Long userbmRoleId);
    ExtendedUser loadUserbmbyUsername(String username);
}
