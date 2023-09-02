package com.c2psi.bmv1.product.article.dao;

import com.c2psi.bmv1.product.article.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleDao extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
    Optional<Article> findArticleById(Long id);

    @Query("""
SELECT art FROM Article art WHERE (art.artCode = :artCode AND art.artPos.id = :posId)
""")
    Optional<Article> findArticleByCodeInPos(@Param("artCode") String artCode, @Param("posId") Long posId);

    @Query("""
SELECT art FROM Article art WHERE (art.artPf.id = :pfId AND art.artUnit.id = :unitId AND art.artBaseprice.id = :bpId)
""")
    Optional<Article> findArticleByPfUnitAndBpInPos(Long pfId, Long unitId, Long bpId);
}
