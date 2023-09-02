package com.c2psi.bmv1.price.specialprice.mappers;

import com.c2psi.bmv1.dto.SpecialpriceDto;
import com.c2psi.bmv1.price.specialprice.models.Specialprice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialpriceMapper {
    @Mapping(source = "spBaseprice.id", target = "spBasepriceId")
    SpecialpriceDto entityToDto(Specialprice specialprice);
    List<SpecialpriceDto> entityToDto(List<Specialprice> specialprice);
    Specialprice dtoToEntity(SpecialpriceDto specialpriceDto);
    List<Specialprice> dtoToEntity(List<SpecialpriceDto> specialpriceDto);
}
