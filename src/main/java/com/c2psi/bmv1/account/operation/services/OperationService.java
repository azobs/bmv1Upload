package com.c2psi.bmv1.account.operation.services;

import com.c2psi.bmv1.dto.OperationDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofOperationDto;

import java.util.List;

public interface OperationService {
    OperationDto saveOperation(OperationDto operationDto);
    OperationDto updateOperation(OperationDto operationDto);
    Boolean deleteOperationById(Long id);
    OperationDto getOperationById(Long id);
    List<OperationDto> getListofOperation(FilterRequest filterRequest);
    PageofOperationDto getPageofOperation(FilterRequest filterRequest);
    Boolean isOperationExistWith(Long id);
}
