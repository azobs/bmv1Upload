package com.c2psi.bmv1.sale.backin.backindetails.dao;

import com.c2psi.bmv1.sale.backin.backindetails.models.Backindetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BackindetailsDao extends JpaRepository<Backindetails, Long>, JpaSpecificationExecutor<Backindetails> {
    Optional<Backindetails> findBackindetailsById(Long id);
    @Query("""
SELECT bid FROM Backindetails bid WHERE (bid.bidArticle.id = :articleId AND bid.bidBackin.id = :backinId)
""")
    Optional<Backindetails> findBackindetailsByAttributes(@Param("articleId") Long articleId, @Param("backinId") Long backinId);
}
