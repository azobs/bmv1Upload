package com.c2psi.bmv1.account.accountoperation.dao;

import com.c2psi.bmv1.account.accountoperation.models.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountOperationDao extends JpaRepository<AccountOperation, Long>, JpaSpecificationExecutor<AccountOperation> {
    Optional<AccountOperation> findAccountOperationById(Long id);
    @Query("""
SELECT accop FROM AccountOperation accop WHERE (accop.account.id = :accountId AND accop.operation.id = :operationId)
""")
    Optional<AccountOperation> findAccountOperationByAttributes(@Param("accountId") Long accountId,
                                                                @Param("operationId") Long operationId);
}
