package com.c2psi.bmv1.product.format.dao;

import com.c2psi.bmv1.product.format.models.Format;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormatDao extends JpaRepository<Format, Long>, JpaSpecificationExecutor<Format> {
    Optional<Format> findFormatById(Long id);
    @Query("""
SELECT format FROM Format format WHERE (format.formatName = :formatName AND format.formatPos.id = :posId)
""")
    Optional<Format> findFormatByAttributes(@Param("formatName") String formatName, @Param("posId") Long posId);
}
