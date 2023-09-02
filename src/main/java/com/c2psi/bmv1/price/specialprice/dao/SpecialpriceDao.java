package com.c2psi.bmv1.price.specialprice.dao;

import com.c2psi.bmv1.price.specialprice.models.Specialprice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialpriceDao extends JpaRepository<Specialprice, Long>, JpaSpecificationExecutor<Specialprice> {
    Optional<Specialprice> findSpecialpriceById(Long id);
    @Query("""
SELECT sp FROM Specialprice  sp WHERE (sp.spCode = :spCode AND sp.spBaseprice.id = :bpId)
""")
    Optional<Specialprice> findSpecialpriceBySpCodeForBaseprice(@Param("spCode") String spCode, @Param("bpId") Long bpId);
}
