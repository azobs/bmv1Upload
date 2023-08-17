package com.c2psi.bmv1.userbmrole.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.UserbmRolePermissionDto;

import java.util.List;

public interface UserbmRolePermissionService {
    UserbmRolePermissionDto saveUserbmRolePermission(UserbmRolePermissionDto ubmRolePermDto);
    Boolean deleteUserbmRolePermissionById(Long id);
    UserbmRolePermissionDto getUserbmRolePermissionById(Long id);
    List<UserbmRolePermissionDto> getListofUserbmRolePermission(FilterRequest filterRequest);
}
