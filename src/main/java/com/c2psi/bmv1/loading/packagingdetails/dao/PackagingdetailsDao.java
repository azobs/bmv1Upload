package com.c2psi.bmv1.loading.packagingdetails.dao;

import com.c2psi.bmv1.loading.packagingdetails.models.Packagingdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PackagingdetailsDao extends JpaRepository<Packagingdetails, Long>, JpaSpecificationExecutor<Packagingdetails> {
    Optional<Packagingdetails> findPackagingdetailsById(Long id);
    @Query("""
SELECT pd FROM Packagingdetails pd WHERE (pd.pdPackaging.id = :packagingId AND pd.pdLoading.id = :loadingId)
""")
    Optional<Packagingdetails> findPackagingdetailsByPackagingInLoading(@Param("packagingId") Long packagingId,
                                                                        @Param("loadingId") Long loadingId);
}
