package com.c2psi.bmv1.product.product.dao;

import com.c2psi.bmv1.product.product.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDao extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Optional<Product> findProductById(Long id);
    Optional<Product> findProductByProdCode(String prodCode);
}
