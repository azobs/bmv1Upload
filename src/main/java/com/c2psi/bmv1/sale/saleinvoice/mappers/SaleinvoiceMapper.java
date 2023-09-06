package com.c2psi.bmv1.sale.saleinvoice.mappers;

import com.c2psi.bmv1.dto.SaleinvoiceDto;
import com.c2psi.bmv1.sale.saleinvoice.models.Saleinvoice;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleinvoiceMapper {
    @ValueMappings({
            @ValueMapping(source = "Cash", target = "CASH"),
            @ValueMapping(source = "Momo", target = "MOMO"),
            @ValueMapping(source = "Om", target = "OM")
    })
    SaleinvoiceDto entityToDto(Saleinvoice saleinvoice);
    List<SaleinvoiceDto> entityToDto(List<Saleinvoice> saleinvoice);
    @ValueMappings({
            @ValueMapping(source = "CASH", target = "Cash"),
            @ValueMapping(source = "MOMO", target = "Momo"),
            @ValueMapping(source = "OM", target = "Om")
    })
    Saleinvoice dtoToEntity(SaleinvoiceDto saleinvoiceDto);
    List<Saleinvoice> dtoToEntity(List<SaleinvoiceDto> saleinvoiceDto);
}
