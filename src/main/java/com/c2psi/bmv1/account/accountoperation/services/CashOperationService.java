package com.c2psi.bmv1.account.accountoperation.services;

import com.c2psi.bmv1.dto.CashOperationDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofCashOperationDto;

import java.util.List;

public interface CashOperationService {
    CashOperationDto saveCashOperation(CashOperationDto accountOperationDto);
    CashOperationDto updateCashOperation(CashOperationDto accountOperationDto);
    Boolean deleteCashOperationById(Long id);
    CashOperationDto getCashOperationById(Long id);
    List<CashOperationDto> getListofCashOperation(FilterRequest filterRequest);
    PageofCashOperationDto getPageofCashOperation(FilterRequest filterRequest);
    Boolean isCashOperationExistWith(Long id);
}
