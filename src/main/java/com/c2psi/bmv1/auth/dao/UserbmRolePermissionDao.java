package com.c2psi.bmv1.auth.dao;

import com.c2psi.bmv1.auth.models.UserbmRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserbmRolePermissionDao extends JpaRepository<UserbmRolePermission, Long>,
        JpaSpecificationExecutor<UserbmRolePermission> {
    Optional<UserbmRolePermission> findUserbmRolePermissionById(Long ubmRolePermId);

    @Query("""
SELECT ubmRolePerm FROM UserbmRolePermission ubmRolePerm WHERE (ubmRolePerm.userbmRole.id = :ubmRoleId AND 
ubmRolePerm.permission.id = :permissionId)
""")
    Optional<UserbmRolePermission> findByUserbmroleAndPermission(@Param("ubmRoleId") Long ubmRoleId,
                                                                 @Param("permissionId") Long permissionId);



    @Query("""
SELECT ubmRolePerm FROM UserbmRolePermission ubmRolePerm WHERE (ubmRolePerm.userbmRole.id = :ubmRoleId)
""")
    Optional<List<UserbmRolePermission>> findPermissionofUserbmRole(@Param("ubmRoleId") Long ubmRoleId);
}
