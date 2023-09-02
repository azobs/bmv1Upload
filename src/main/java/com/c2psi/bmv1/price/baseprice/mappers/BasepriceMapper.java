package com.c2psi.bmv1.price.baseprice.mappers;

import com.c2psi.bmv1.dto.BasepriceDto;
import com.c2psi.bmv1.price.baseprice.models.Baseprice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BasepriceMapper {
    @Mapping(source = "bpPos.id", target = "bpPosId")
    BasepriceDto entityToDto(Baseprice baseprice);
    List<BasepriceDto> entityToDto(List<Baseprice> baseprice);
    Baseprice dtoToEntity(BasepriceDto basepriceDto);
    List<Baseprice> dtoToEntity(List<BasepriceDto> basepriceDto);
}
