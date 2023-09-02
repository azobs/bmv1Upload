package com.c2psi.bmv1.product.article.mappers;

import com.c2psi.bmv1.dto.ArticleDto;
import com.c2psi.bmv1.product.article.models.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    @Mapping(source = "artPf.id", target = "artPfId")
    @Mapping(source = "artUnit.id", target = "artUnitId")
    @Mapping(source = "artPos.id", target = "artPosId")
    @Mapping(source = "artBaseprice.id", target = "artBasepriceId")
    ArticleDto entityToDto(Article article);
    List<ArticleDto> entityToDto(List<Article> article);
    Article dtoToEntity(ArticleDto articleDto);
    List<Article> dtoToEntity(List<ArticleDto> articleDto);
}
