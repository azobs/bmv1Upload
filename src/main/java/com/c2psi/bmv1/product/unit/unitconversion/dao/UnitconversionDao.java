package com.c2psi.bmv1.product.unit.unitconversion.dao;

import com.c2psi.bmv1.product.unit.unitconversion.models.Unitconversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitconversionDao extends JpaRepository<Unitconversion, Long>,
        JpaSpecificationExecutor<Unitconversion> {

    Optional<Unitconversion> findUnitconversionById(Long id);

    @Query("""
SELECT uconv FROM Unitconversion uconv WHERE (uconv.unitSource.id = :unitSourceId AND 
uconv.unitDestination.id = :unitDestinationId)
""")
    Optional<Unitconversion> findUnitconversionByAttributes(@Param("unitSourceId") Long unitSourceId,
                                                            @Param("unitDestinationId") Long unitDestinationId);
}
