package com.c2psi.bmv1.account.accountoperation.mappers;

import com.c2psi.bmv1.account.accountoperation.models.AccountOperation;
import com.c2psi.bmv1.dto.AccountOperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountOperationMapper {
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "operation.id", target = "operationId")
    AccountOperationDto entityToDto(AccountOperation accountOperation);
    List<AccountOperationDto> entityToDto(List<AccountOperation> accountOperation);
    AccountOperation dtoToEntity(AccountOperationDto accountOperationDto);
    List<AccountOperation> dtoToEntity(List<AccountOperationDto> accountOperationDto);
}
