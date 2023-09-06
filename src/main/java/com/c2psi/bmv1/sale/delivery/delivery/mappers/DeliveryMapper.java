package com.c2psi.bmv1.sale.delivery.delivery.mappers;

import com.c2psi.bmv1.dto.DeliveryDto;
import com.c2psi.bmv1.sale.delivery.delivery.models.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

import java.util.List;
@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    @ValueMappings({
            @ValueMapping(source = "InEditing", target = "INEDITING"),
            @ValueMapping(source = "Edited", target = "EDITED"),
            @ValueMapping(source = "Delivery", target = "DELIVERY")
    })
    DeliveryDto entityToDto(Delivery delivery);
    List<DeliveryDto> entityToDto(List<Delivery> delivery);
    @ValueMappings({
            @ValueMapping(source = "INEDITING", target = "InEditing"),
            @ValueMapping(source = "EDITED", target = "Edited"),
            @ValueMapping(source = "DELIVERY", target = "Delivery")
    })
    Delivery dtoToEntity(DeliveryDto deliveryDto);
    List<Delivery> dtoToEntity(List<DeliveryDto> deliveryDto);
}
