package com.c2psi.bmv1.account.operation.dao;

import com.c2psi.bmv1.account.operation.models.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationDao extends JpaRepository<Operation, Long>, JpaSpecificationExecutor<Operation> {
    Optional<Operation> findOperationById(Long id);
}
