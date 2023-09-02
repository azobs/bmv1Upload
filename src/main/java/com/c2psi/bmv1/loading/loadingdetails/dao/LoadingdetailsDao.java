package com.c2psi.bmv1.loading.loadingdetails.dao;

import com.c2psi.bmv1.loading.loadingdetails.models.Loadingdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoadingdetailsDao extends JpaRepository<Loadingdetails, Long>, JpaSpecificationExecutor<Loadingdetails> {
    Optional<Loadingdetails> findLoadingdetailsById(Long id);
    @Query("""
            SELECT ld FROM Loadingdetails ld WHERE (ld.ldArticle.id = :articleId AND ld.ldLoading.id = :loadingId)
            """)
    Optional<Loadingdetails> findLoadingdetailsByArticleInLoading(@Param("articleId") Long articleId,
                                                                  @Param("loadingId") Long loadingId);
}
