package com.c2psi.bmv1.auth.token.dao;

import com.c2psi.bmv1.auth.token.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenDao extends JpaRepository<Token, Long>, JpaSpecificationExecutor<Token> {
    Optional<Token> findTokenById(Long id);
    Optional<Token> findTokenByTokenValue(String tokenValue);
    @Query("""
SELECT t FROM Token t WHERE t.userbm.id = :userbmId AND (t.expired = false OR t.revoked = false)
""")
    List<Token> findAllValidTokenListByUser(Long userbmId);
}
