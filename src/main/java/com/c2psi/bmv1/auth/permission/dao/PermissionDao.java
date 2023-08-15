package com.c2psi.bmv1.auth.permission.dao;

import com.c2psi.bmv1.auth.permission.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionDao extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {
    Optional<Permission> findPermissionById(Long id);
    Optional<Permission> findPermissionByPermissionName(String permissionName);
}
