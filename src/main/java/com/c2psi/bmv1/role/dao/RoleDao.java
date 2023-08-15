package com.c2psi.bmv1.role.dao;

import com.c2psi.bmv1.bmapp.enumerations.RoleTypeEnum;
import com.c2psi.bmv1.role.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {
    Optional<Role> findRoleById(Long id);

    Optional<Role> findRoleByRoleNameAndRoleType(String roleName, RoleTypeEnum roleType);
}
