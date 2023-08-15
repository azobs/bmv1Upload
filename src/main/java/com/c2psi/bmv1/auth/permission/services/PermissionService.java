package com.c2psi.bmv1.auth.permission.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofPermissionDto;
import com.c2psi.bmv1.dto.PermissionDto;

import java.util.List;

public interface PermissionService {
    PermissionDto savePermission(PermissionDto roleDto);
    PermissionDto updatePermission(PermissionDto roleDto);
    Boolean deletePermissionById(Long id);
    PermissionDto getPermissionById(Long id);
    PermissionDto getPermissionByName(String permName);
    List<PermissionDto> getListofPermission(FilterRequest filterRequest);
    PageofPermissionDto getPageofPermission(FilterRequest filterRequest);
}
