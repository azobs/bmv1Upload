package com.c2psi.bmv1.provider.provider.mappers;

import com.c2psi.bmv1.dto.ProviderDto;
import com.c2psi.bmv1.provider.provider.models.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProviderMapper {
    @Mapping(source = "providerPos.id", target = "providerPosId")
    ProviderDto entityToDto(Provider provider);
    List<ProviderDto> entityToDto(List<Provider> provider);
    Provider dtoToEntity(ProviderDto providerDto);
    List<Provider> dtoToEntity(List<ProviderDto> providerDto);
}
