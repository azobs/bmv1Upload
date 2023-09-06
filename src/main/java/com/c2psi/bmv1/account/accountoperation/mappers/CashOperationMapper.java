package com.c2psi.bmv1.account.accountoperation.mappers;

import com.c2psi.bmv1.account.accountoperation.models.CashOperation;
import com.c2psi.bmv1.dto.CashOperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CashOperationMapper {
    @Mapping(source = "operation.id", target = "operationId")
    @Mapping(source = "posConcerned.id", target = "posConcernedId")
    @Mapping(source = "clientConcerned.id", target = "clientConcernedId")
    @Mapping(source = "providerConcerned.id", target = "providerConcernedId")
    CashOperationDto entityToDto(CashOperation cashOperation);
    List<CashOperationDto> entityToDto(List<CashOperation> cashOperation);
    CashOperation dtoToEntity(CashOperationDto cashOperationDto);
    List<CashOperation> dtoToEntity(List<CashOperationDto> cashOperationDto);
}
