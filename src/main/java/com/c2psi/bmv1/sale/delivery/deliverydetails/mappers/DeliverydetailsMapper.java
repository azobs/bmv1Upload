package com.c2psi.bmv1.sale.delivery.deliverydetails.mappers;

import com.c2psi.bmv1.dto.DeliverydetailsDto;
import com.c2psi.bmv1.sale.delivery.deliverydetails.models.Deliverydetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliverydetailsMapper {
    @Mapping(source = "ddPackaging.id", target = "ddPackagingId")
    @Mapping(source = "ddDelivery.id", target = "ddDeliveryId")
    DeliverydetailsDto entityToDto(Deliverydetails deliveryDetails);
    List<DeliverydetailsDto> entityToDto(List<Deliverydetails> deliveryDetails);
    Deliverydetails dtoToEntity(DeliverydetailsDto deliverydetailsDto);
    List<Deliverydetails> dtoToEntity(List<DeliverydetailsDto> deliverydetailsDto);
}
