package com.c2psi.bmv1.inventory.inventory.dao;

import com.c2psi.bmv1.inventory.inventory.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InventoryDao extends JpaRepository<Inventory, Long>, JpaSpecificationExecutor<Inventory> {
    Optional<Inventory> findInventoryById(Long id);
    @Query("""
SELECT inv FROM Inventory inv WHERE (inv.invCode = :invCode AND inv.invPos.id = :posId)
""")
    Optional<Inventory> findInventoryByCodeInPos(@Param("invCode") String invCode, @Param("posId") Long posId);

}
