package com.c2psi.bmv1.arrival.supplyinvoice.mappers;

import com.c2psi.bmv1.arrival.supplyinvoice.models.Supplyinvoice;
import com.c2psi.bmv1.dto.SupplyinvoiceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SupplyinvoiceMapper {
    @ValueMappings({
            @ValueMapping(source = "Cash", target = "CASH"),
            @ValueMapping(source = "Momo", target = "MOMO"),
            @ValueMapping(source = "Om", target = "OM")
    })
    @Mapping(source = "siPos.id", target = "siPosId")
    @Mapping(source = "siProvider.id", target = "siProviderId")
    SupplyinvoiceDto entityToDto(Supplyinvoice supplyinvoice);
    List<SupplyinvoiceDto> entityToDto(List<Supplyinvoice> supplyinvoice);
    @ValueMappings({
            @ValueMapping(source = "CASH", target = "Cash"),
            @ValueMapping(source = "MOMO", target = "Momo"),
            @ValueMapping(source = "OM", target = "Om")
    })
    Supplyinvoice dtoToEntity(SupplyinvoiceDto supplyinvoiceDto);
    List<Supplyinvoice> dtoToEntity(List<SupplyinvoiceDto> supplyinvoiceDto);
}
