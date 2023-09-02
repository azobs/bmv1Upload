package com.c2psi.bmv1.arrival.supplyinvoice.dao;

import com.c2psi.bmv1.arrival.supplyinvoice.models.Supplyinvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplyinvoiceDao extends JpaRepository<Supplyinvoice, Long>, JpaSpecificationExecutor<Supplyinvoice> {
    Optional<Supplyinvoice> findSupplyinvoiceById(Long id);
    @Query("""
SELECT si FROM Supplyinvoice si WHERE (si.siCode = :siCode AND si.siPos.id = :posId)
""")
    Optional<Supplyinvoice> findSupplyinvoiceByAttributes(@Param("siCode") String siCode, @Param("posId") Long posId);
    @Query("""
SELECT si FROM Supplyinvoice si WHERE (si.siCode = :siCode AND si.siPos.id = :posId AND si.siProvider.id = :providerId)
""")
    Optional<Supplyinvoice> findSupplyinvoiceByAttributes(@Param("siCode") String siCode, @Param("posId") Long posId,
                                                          @Param("providerId") Long providerId);
}
