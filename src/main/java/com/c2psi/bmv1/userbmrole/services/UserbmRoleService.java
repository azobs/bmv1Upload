package com.c2psi.bmv1.userbmrole.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofUserbmRoleDto;
import com.c2psi.bmv1.dto.UserbmRoleDto;

import java.util.List;

public interface UserbmRoleService {
    UserbmRoleDto saveUserbmRole(UserbmRoleDto userbmroleDto);
    UserbmRoleDto updateUserbmRole(UserbmRoleDto userbmroleDto);
    Boolean deleteUserbmRoleById(Long id);
    UserbmRoleDto getUserbmRoleById(Long id);
    List<UserbmRoleDto> getListofUserbmRole(FilterRequest filterRequest);
    PageofUserbmRoleDto getPageofUserbmRole(FilterRequest filterRequest);
    Boolean isUserbmRoleExistWithId(Long userbmRoleId);
}
