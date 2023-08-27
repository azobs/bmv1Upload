package com.c2psi.bmv1.product.unit.unit.dao;

import com.c2psi.bmv1.product.unit.unit.models.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UnitDao extends JpaRepository<Unit, Long>, JpaSpecificationExecutor<Unit> {
    Optional<Unit> findUnitById(Long id);
    @Query("""
SELECT unit FROM Unit unit WHERE (unit.unitName = :unitName AND unit.unitAbbreviation = :unitAbbre AND unit.unitPos.id = :posId) 
""")
    Optional<Unit> findUnitByAttributes(@Param("unitName") String unitName, @Param("unitAbbre") String unitAbbre,
                                        @Param("posId") Long posId);
}
