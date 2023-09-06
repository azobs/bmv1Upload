package com.c2psi.bmv1.sale.backin.backin.dao;

import com.c2psi.bmv1.sale.backin.backin.models.Backin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BackinDao extends JpaRepository<Backin, Long>, JpaSpecificationExecutor<Backin> {
    Optional<Backin> findBackinById(Long id);
    @Query("""
SELECT backin FROM Backin backin WHERE backin.biCommand.id = :commandId
""")
    Optional<Backin> findBackinOfCommand(@Param("commandId") Long commandId);
}
