package com.c2psi.bmv1.account.accountoperation.services;

import com.c2psi.bmv1.dto.AccountOperationDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofAccountOperationDto;

import java.util.List;

public interface AccountOperationService {
    AccountOperationDto saveAccountOperation(AccountOperationDto accountOperationDto);
    AccountOperationDto updateAccountOperation(AccountOperationDto accountOperationDto);
    Boolean deleteAccountOperationById(Long id);
    AccountOperationDto getAccountOperationById(Long id);
    List<AccountOperationDto> getListofAccountOperation(FilterRequest filterRequest);
    PageofAccountOperationDto getPageofAccountOperation(FilterRequest filterRequest);
    Boolean isAccountOperationExistWith(Long id);
}
