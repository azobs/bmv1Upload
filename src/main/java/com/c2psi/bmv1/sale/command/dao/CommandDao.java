package com.c2psi.bmv1.sale.command.dao;

import com.c2psi.bmv1.sale.command.models.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandDao extends JpaRepository<Command, Long>, JpaSpecificationExecutor<Command> {
    Optional<Command> findCommandById(Long id);
    @Query("""
SELECT cmd FROM Command cmd WHERE (cmd.cmdCode = :cmdCode AND cmd.cmdPos.id = :posId)
""")
    Optional<Command> findCommandByCodeInPos(@Param("cmdCode") String cmdCode, @Param("posId") Long posId);
}
