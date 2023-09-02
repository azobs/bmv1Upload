package com.c2psi.bmv1.loading.packagingdetails.mappers;

import com.c2psi.bmv1.dto.PackagingdetailsDto;
import com.c2psi.bmv1.loading.packagingdetails.models.Packagingdetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PackagingdetailsMapper {
    @Mapping(source = "pdLoading.id", target = "pdLoadingId")
    @Mapping(source = "pdPackaging.id", target = "pdPackagingId")
    PackagingdetailsDto entityToDto(Packagingdetails packagingdetails);
    List<PackagingdetailsDto> entityToDto(List<Packagingdetails> packagingdetails);
    Packagingdetails dtoToEntity(PackagingdetailsDto packagingdetailsDto);
    List<Packagingdetails> dtoToEntity(List<PackagingdetailsDto> packagingdetailsDto);
}
