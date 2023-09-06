package com.c2psi.bmv1.sale.saleinvoice.dao;

import com.c2psi.bmv1.sale.saleinvoice.models.Saleinvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleinvoiceDao extends JpaRepository<Saleinvoice, Long>, JpaSpecificationExecutor<Saleinvoice> {
    Optional<Saleinvoice> findSaleinvoiceById(Long id);
    @Query("""
SELECT saleinv FROM Saleinvoice saleinv WHERE (saleinv.siCode = :siCode AND saleinv.siPos.id = :posId)
""")
    Optional<Saleinvoice> findSaleinvoiceByCodeInPos(@Param("siCode") String siCode, @Param("posId") Long posId);
}
