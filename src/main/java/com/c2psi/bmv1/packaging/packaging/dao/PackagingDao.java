package com.c2psi.bmv1.packaging.packaging.dao;

import com.c2psi.bmv1.packaging.packaging.models.Packaging;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackagingDao extends JpaRepository<Packaging, Long>, JpaSpecificationExecutor<Packaging> {
    Optional<Packaging> findPackagingById(Long id);
    @Query("""
SELECT pack FROM Packaging pack WHERE (pack.packLabel = :packLabel AND pack.packFirstcolor = :packFirstColor AND 
pack.packagingProvider.id = :providerId AND pack.packagingPos.id = :posId)
""")
    Optional<com.c2psi.bmv1.packaging.packaging.models.Packaging> findPackagingByAttributes(@Param("packLabel") String packLabel,
                                                                                            @Param("packFirstColor") String packFirstColor,
                                                                                            @Param("providerId") Long providerId, @Param("posId") Long posId);
}
