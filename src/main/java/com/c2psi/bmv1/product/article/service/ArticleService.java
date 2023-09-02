package com.c2psi.bmv1.product.article.service;

import com.c2psi.bmv1.bmapp.enumerations.DirectionOfMvt;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofArticleDto;
import com.c2psi.bmv1.dto.ArticleDto;

import java.util.List;

public interface ArticleService {
    ArticleDto saveArticle(ArticleDto articleDto);
    ArticleDto updateArticle(ArticleDto articleDto);
    ArticleDto updateQteInStockofArticle(Long articleId, Double qteInMvt, DirectionOfMvt directionOfMvt);
    Boolean deleteArticleById(Long id);
    ArticleDto getArticleById(Long id);
    List<ArticleDto> getListofArticle(FilterRequest filterRequest);
    PageofArticleDto getPageofArticle(FilterRequest filterRequest);
    Boolean isArticleExistWith(Long id);
}
