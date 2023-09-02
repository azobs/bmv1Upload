package com.c2psi.bmv1.arrival.arrival.dao;

import com.c2psi.bmv1.arrival.arrival.models.Arrival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArrivalDao extends JpaRepository<Arrival, Long>, JpaSpecificationExecutor<Arrival> {
    Optional<Arrival> findArrivalById(Long id);
    @Query("""
SELECT arrival FROM Arrival arrival WHERE (arrival.arrivalArticle.id = :articleId AND arrival.arrivalSi.id = :siId)
""")
    Optional<Arrival> findArrivalByArticleInSInvoice(@Param("articleId") Long articleId, @Param("siId") Long siId);
}
