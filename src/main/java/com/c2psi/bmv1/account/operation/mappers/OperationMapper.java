package com.c2psi.bmv1.account.operation.mappers;

import com.c2psi.bmv1.account.operation.models.Operation;
import com.c2psi.bmv1.dto.OperationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperationMapper {
    @ValueMappings({
            @ValueMapping(source = "Credit", target = "CREDIT"),
            @ValueMapping(source = "Withdrawal", target = "WITHDRAWAL"),
            @ValueMapping(source = "Change", target = "CHANGE"),
            @ValueMapping(source = "Others", target = "OTHERS")
    })
    OperationDto entityToDto(Operation operation);
    List<OperationDto> entityToDto(List<Operation> operation);
    @ValueMappings({
            @ValueMapping(source = "CREDIT", target = "Credit"),
            @ValueMapping(source = "WITHDRAWAL", target = "Withdrawal"),
            @ValueMapping(source = "CHANGE", target = "Change"),
            @ValueMapping(source = "OTHERS", target = "Others")
    })
    Operation dtoToEntity(OperationDto operationDto);
    List<Operation> dtoToEntity(List<OperationDto> operationDto);
}
