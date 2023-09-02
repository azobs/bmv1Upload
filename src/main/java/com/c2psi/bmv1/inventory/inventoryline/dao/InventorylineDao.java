package com.c2psi.bmv1.inventory.inventoryline.dao;

import com.c2psi.bmv1.inventory.inventoryline.models.Inventoryline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventorylineDao extends JpaRepository<Inventoryline, Long>, JpaSpecificationExecutor<Inventoryline> {
    Optional<Inventoryline> findInventorylineById(Long id);
    @Query("""
SELECT invline FROM Inventoryline invline WHERE (invline.inventory.id = :inventoryId AND invline.invlineArticle.id = :articleId)
""")
    Optional<Inventoryline> findInventorylineOfArticleInInventory(Long articleId, Long inventoryId);
}
