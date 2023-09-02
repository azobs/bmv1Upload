package com.c2psi.bmv1.price.baseprice.dao;

import com.c2psi.bmv1.price.baseprice.models.Baseprice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasepriceDao extends JpaRepository<Baseprice, Long>, JpaSpecificationExecutor<Baseprice> {
    Optional<Baseprice> findBasepriceById(Long id);
    @Query("""
SELECT bp FROM Baseprice bp WHERE (bp.bpCode = :bpCode AND bp.bpPos.id = :posId)
""")
    Optional<Baseprice> findBasepriceByCodeInPos(@Param("bpCode") String bpCode, @Param("posId") Long posId);
}
