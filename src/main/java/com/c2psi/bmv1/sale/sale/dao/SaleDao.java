package com.c2psi.bmv1.sale.sale.dao;

import com.c2psi.bmv1.sale.sale.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleDao extends JpaRepository<Sale, Long>, JpaSpecificationExecutor<Sale> {
    Optional<Sale> findSaleById(Long id);
    @Query("""
            SELECT sale FROM Sale sale WHERE (sale.saleArticle.id = :articleId AND sale.saleCommand.id = :commandId)
            """)
    Optional<Sale> findSaleAboutArticleInCommand(Long articleId, Long commandId);
}
