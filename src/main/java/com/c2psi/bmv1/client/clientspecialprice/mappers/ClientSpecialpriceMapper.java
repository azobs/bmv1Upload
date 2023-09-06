package com.c2psi.bmv1.client.clientspecialprice.mappers;

import com.c2psi.bmv1.client.clientspecialprice.models.ClientSpecialprice;
import com.c2psi.bmv1.dto.ClientSpecialpriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientSpecialpriceMapper {
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "specialprice.id", target = "specialpriceId")
    @Mapping(source = "article.id", target = "articleId")
    ClientSpecialpriceDto entityToDto(ClientSpecialprice clientSpecialprice);
    List<ClientSpecialpriceDto> entityToDto(List<ClientSpecialprice> clientSpecialprice);
    ClientSpecialprice dtoToEntity(ClientSpecialpriceDto clientSpecialpriceDto);
    List<ClientSpecialprice> dtoToEntity(List<ClientSpecialpriceDto> clientSpecialpriceDto);
}
