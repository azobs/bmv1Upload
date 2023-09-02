package com.c2psi.bmv1.product.article.controllers;

import com.c2psi.bmv1.api.ArticleApi;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.ArticleDto;
import com.c2psi.bmv1.product.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ArticleController implements ArticleApi {
    final ArticleService articleService;

    @Override
    public ResponseEntity<Boolean> deleteArticleById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        Boolean deleted = articleService.deleteArticleById(id);
        log.info("Article deleted successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Article deleted successfully");
        map.put("data", deleted);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArticleDto> getArticleById(Long id) {
        Map<String, Object> map = new LinkedHashMap<>();
        ArticleDto articleDto = articleService.getArticleById(id);
        log.info("Article found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Article found successfully");
        map.put("data", articleDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ArticleDto>> getArticleList(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<ArticleDto> articleDtoList = articleService.getListofArticle(filterRequest);
        log.info("Article list found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Article list found successfully");
        map.put("data", articleDtoList);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PageofArticleDto> getArticlePage(FilterRequest filterRequest) {
        Map<String, Object> map = new LinkedHashMap<>();
        PageofArticleDto pageofArticleDto = articleService.getPageofArticle(filterRequest);
        log.info("Article page found successfully");

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Article page found successfully");
        map.put("data", pageofArticleDto);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArticleDto> saveArticle(ArticleDto articleDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ArticleDto articleDtoSaved = articleService.saveArticle(articleDto);
        log.info("Entity Article saved successfully {}", articleDtoSaved);

        map.clear();
        map.put("status", HttpStatus.CREATED);
        map.put("message", "Article created successfully");
        map.put("data", articleDtoSaved);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ArticleDto> updateArticle(ArticleDto articleDto) {
        Map<String, Object> map = new LinkedHashMap<>();
        ArticleDto articleDtoUpdated = articleService.updateArticle(articleDto);
        log.info("Entity Article updated successfully {}", articleDtoUpdated);

        map.clear();
        map.put("status", HttpStatus.OK);
        map.put("message", "Article updated successfully");
        map.put("data", articleDtoUpdated);
        map.put("cause", "RAS");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
