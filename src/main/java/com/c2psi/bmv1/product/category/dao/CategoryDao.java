package com.c2psi.bmv1.product.category.dao;

import com.c2psi.bmv1.product.category.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    Optional<Category> findCategoryById(Long id);
    @Query("""
SELECT cat FROM Category cat WHERE (cat.catCode = :catCode AND cat.catPos.id = :posId)
""")
    Optional<Category> findCategoryByCodeInPos(@Param("catCode") String catCode, @Param("posId") Long posId);
}
