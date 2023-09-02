package com.c2psi.bmv1.packaging.packaging.mappers;

import com.c2psi.bmv1.dto.PackagingDto;
import com.c2psi.bmv1.packaging.packaging.models.Packaging;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackagingMapper {
    @Mapping(source = "packagingProvider.id", target = "packagingProviderId")
    @Mapping(source = "packagingPos.id", target = "packagingPosId")
    PackagingDto entityToDto(Packaging packaging);
    List<PackagingDto> entityToDto(List<Packaging> packaging);
    Packaging dtoToEntity(PackagingDto packagingDto);
    List<Packaging> dtoToEntity(List<PackagingDto> packagingDto);
}
