package com.c2psi.bmv1.account.account.services;

import com.c2psi.bmv1.dto.AccountDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofAccountDto;

import java.util.List;

public interface AccountService {
    AccountDto saveAccount(AccountDto accountDto);
    AccountDto updateAccount(AccountDto accountDto);
    Boolean deleteAccountById(Long id);
    AccountDto getAccountById(Long id);
    List<AccountDto> getListofAccount(FilterRequest filterRequest);
    PageofAccountDto getPageofAccount(FilterRequest filterRequest);
    Boolean isAccountExistWith(Long id);
}
