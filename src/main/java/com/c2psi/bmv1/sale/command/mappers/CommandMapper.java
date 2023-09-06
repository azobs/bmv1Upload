package com.c2psi.bmv1.sale.command.mappers;

import com.c2psi.bmv1.dto.CommandDto;
import com.c2psi.bmv1.sale.command.models.Command;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommandMapper {
    @ValueMappings({
            @ValueMapping(source = "InEditing", target = "INEDITING"),
            @ValueMapping(source = "Edited", target = "EDITED"),
            @ValueMapping(source = "InDelivery", target = "INDELIVERY"),
            @ValueMapping(source = "Delivery", target = "DELIVERY"),
            @ValueMapping(source = "Cash", target = "CASH"),
            @ValueMapping(source = "Cover", target = "COVER"),
            @ValueMapping(source = "Damage", target = "DAMAGE")
    })
    @Mapping(source = "cmdDelivery.id", target = "cmdDeliveryId")
    @Mapping(source = "cmdLoading.id", target = "cmdLoadingId")
    @Mapping(source = "cmdClient.id", target = "cmdClientId")
    @Mapping(source = "cmdSaler.id", target = "cmdSalerId")
    @Mapping(source = "cmdSaleinvoice.id", target = "cmdInvoiceId")
    @Mapping(source = "cmdPos.id", target = "cmdPosId")
    CommandDto entityToDto(Command command);
    List<CommandDto> entityToDto(List<Command> command);
    @ValueMappings({
            @ValueMapping(source = "INEDITING", target = "InEditing"),
            @ValueMapping(source = "EDITED", target = "Edited"),
            @ValueMapping(source = "INDELIVERY", target = "InDelivery"),
            @ValueMapping(source = "DELIVERY", target = "Delivery"),
            @ValueMapping(source = "CASH", target = "Cash"),
            @ValueMapping(source = "COVER", target = "Cover"),
            @ValueMapping(source = "DAMAGE", target = "Damage")
    })
    Command dtoToEntity(CommandDto commandDto);
    List<Command> dtoToEntity(List<CommandDto> commandDto);
}
