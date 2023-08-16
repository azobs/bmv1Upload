package com.c2psi.bmv1.pos.pos.dao;

import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PointofsaleDao extends JpaRepository<Pointofsale, Long>, JpaSpecificationExecutor<Pointofsale> {
    Optional<Pointofsale> findPointofsaleById(Long id);
    @Query("""
SELECT pos FROM Pointofsale pos WHERE (pos.posName = :posName AND pos.posAcronym = :posAcronym)
""")
    Optional<Pointofsale> findPointofsaleByFullname(@Param("posName") String posName, @Param("posAcronym") String posAcronym);

    @Query("""
SELECT pos FROM Pointofsale pos WHERE pos.posEnterprise.id = :entId
""")
    Optional<List<Pointofsale>> findPosListofEnterprise(@Param("entId") Long entId);
}
