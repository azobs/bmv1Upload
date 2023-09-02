package com.c2psi.bmv1.product.pf.dao;

import com.c2psi.bmv1.product.pf.models.Productformated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductformatedDao extends JpaRepository<Productformated, Long>, JpaSpecificationExecutor<Productformated> {
    Optional<Productformated> findProductformatedById(Long id);

    @Query("""
SELECT pf FROM Productformated pf WHERE (pf.pfFormat.id = :formatId AND pf.pfProduct.id = :productId)
""")
    Optional<Productformated> findProductformatedByAttributes(@Param("productId") Long productId,
                                                              @Param("formatId") Long formatId);
}
