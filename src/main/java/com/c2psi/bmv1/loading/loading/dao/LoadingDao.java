package com.c2psi.bmv1.loading.loading.dao;

import com.c2psi.bmv1.loading.loading.models.Loading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoadingDao extends JpaRepository<Loading, Long>, JpaSpecificationExecutor<Loading> {
    Optional<Loading> findLoadingById(Long id);
    @Query("""
            SELECT load FROM Loading load WHERE (load.loadCode = :loadCode AND load.loadPos.id = :posId)
            """)
    Optional<Loading> findLoadingByCodeInPos(@Param("loadCode") String loadCode, @Param("posId") Long posId);
}
