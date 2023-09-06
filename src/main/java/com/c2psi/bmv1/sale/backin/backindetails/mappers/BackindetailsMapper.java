package com.c2psi.bmv1.sale.backin.backindetails.mappers;

import com.c2psi.bmv1.dto.BackindetailsDto;
import com.c2psi.bmv1.sale.backin.backindetails.models.Backindetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BackindetailsMapper {
    @Mapping(source = "bidArticle.id", target = "bidArticleId")
    @Mapping(source = "bidBackin.id", target = "bidBackinId")
    BackindetailsDto entityToDto(Backindetails backindetails);
    List<BackindetailsDto> entityToDto(List<Backindetails> backindetails);
    Backindetails dtoToEntity (BackindetailsDto backindetailsDto);
    List<Backindetails> dtoToEntity (List<BackindetailsDto> backindetailsDto);
}
