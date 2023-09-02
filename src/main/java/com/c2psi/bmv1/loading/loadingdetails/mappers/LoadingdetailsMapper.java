package com.c2psi.bmv1.loading.loadingdetails.mappers;

import com.c2psi.bmv1.dto.LoadingdetailsDto;
import com.c2psi.bmv1.loading.loadingdetails.models.Loadingdetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoadingdetailsMapper {
    @Mapping(source = "ldArticle.id", target = "ldArticleId")
    @Mapping(source = "ldLoading.id", target = "ldLoadingId")
    LoadingdetailsDto entityToDto(Loadingdetails loadingdetails);
    List<LoadingdetailsDto> entityToDto(List<Loadingdetails> loadingdetails);
    Loadingdetails dtoToEntity(LoadingdetailsDto loadingdetailsDto);
    List<Loadingdetails> dtoToEntity(List<LoadingdetailsDto> loadingdetailsDto);
}
