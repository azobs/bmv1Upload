package com.c2psi.bmv1.loading.loading.mappers;

import com.c2psi.bmv1.dto.LoadingDto;
import com.c2psi.bmv1.loading.loading.models.Loading;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoadingMapper {
    @Mapping(source = "loadPos.id", target = "loadPosId")
    @Mapping(source = "loadSaler.id", target = "loadSalerId")
    @Mapping(source = "loadManager.id", target = "loadManagerId")
    LoadingDto entityToDto(Loading loading);
    List<LoadingDto> entityToDto(List<Loading> loading);
    Loading dtoToEntity(LoadingDto loadingDto);
    List<Loading> dtoToEntity(List<LoadingDto> loadingDto);
}
