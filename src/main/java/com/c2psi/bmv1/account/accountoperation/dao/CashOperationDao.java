package com.c2psi.bmv1.account.accountoperation.dao;

import com.c2psi.bmv1.account.accountoperation.models.CashOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CashOperationDao extends JpaRepository<CashOperation, Long>, JpaSpecificationExecutor<CashOperation> {
    Optional<CashOperation> findCashOperationById(Long id);
    @Query("""
SELECT cashop FROM CashOperation cashop WHERE (cashop.operation.id = :operationId AND cashop.posConcerned.id = :posId)
""")
    Optional<CashOperation> findCashOperationByOperationAndPos(@Param("operationId") Long operationId,
                                                               @Param("posId") Long posId);
    @Query("""
SELECT cashop FROM CashOperation cashop WHERE (cashop.clientConcerned.id = :clientId AND 
cashop.operation.id = :operationId AND cashop.posConcerned.id = :posId)
""")
    Optional<CashOperation> findCashOperationByOperationOfClientInPos(@Param("clientId") Long clientId,
                                                                      @Param("operationId")Long operationId,
                                                                      @Param("posId") Long posId);

    @Query("""
SELECT cashop FROM CashOperation cashop WHERE (cashop.providerConcerned.id = :providerId AND 
cashop.operation.id = :operationId AND cashop.posConcerned.id = :posId)
""")
    Optional<CashOperation> findCashOperationByOperationOfProviderInPos(@Param("providerId") Long providerId,
                                                                        @Param("operationId") Long operationId,
                                                                        @Param("posId") Long posId);
}
