package com.c2psi.bmv1.pos.enterprise.dao;

import com.c2psi.bmv1.pos.enterprise.models.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnterpriseDao extends JpaRepository<Enterprise, Long>, JpaSpecificationExecutor<Enterprise> {
    Optional<Enterprise> findEnterpriseById(Long id);
    Optional<Enterprise> findEnterpriseByEntNiu(String entNiu);
    @Query("""
SELECT ent FROM Enterprise ent WHERE (ent.entName = :entName AND ent.entAcronym = :entAcronym)
""")
    Optional<Enterprise> findEnterpriseByFullname(@Param("entName") String entName, @Param("entAcronym") String entAcronym);
}
