package com.c2psi.bmv1.userbmrole.dao;

import com.c2psi.bmv1.userbmrole.models.UserbmRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserbmRoleDao extends JpaRepository<UserbmRole, Long>, JpaSpecificationExecutor<UserbmRole> {
    Optional<UserbmRole> findUserbmRoleById(Long id);
    @Query("""
SELECT ubr FROM UserbmRole ubr WHERE (ubr.userbm.id = :userbmId AND ubr.role.id = :roleId)
""")
    Optional<UserbmRole> findUserbmRoleByUserbmAndRole(@Param("userbmId") Long userbmId, @Param("roleId") Long roleId);

    @Query("""
SELECT ubr FROM UserbmRole ubr WHERE (ubr.userbm.id = :userbmId)
""")
    Optional<List<UserbmRole>> findAllRoleofUserbm (@Param("userbmId") Long userbmId);
}
