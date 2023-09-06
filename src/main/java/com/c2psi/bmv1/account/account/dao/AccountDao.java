package com.c2psi.bmv1.account.account.dao;

import com.c2psi.bmv1.account.account.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDao extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {
    Optional<Account> findAccountById(Long id);
    @Query("""
SELECT acc FROM Account acc WHERE (acc.accountArticle.id = :articleId AND acc.accountClient.id = :clientId 
AND acc.accountPos.id = :posId)
""")
    Optional<Account> findAccountofArticleAndClientInPos(@Param("articleId") Long articleId, @Param("clientId") Long clientId,
                                                         @Param("posId") Long posId);
    @Query("""
SELECT acc FROM Account acc WHERE (acc.accountArticle.id = :articleId AND acc.accountProvider.id = :providerId 
AND acc.accountPos.id = :posId)
""")
    Optional<Account> findAccountofArticleAndProviderInPos(@Param("articleId") Long articleId, @Param("providerId") Long providerId,
                                                           @Param("posId") Long posId);
    @Query("""
SELECT acc FROM Account acc WHERE (acc.accountPackaging.id = :packagingId AND acc.accountClient.id = :clientId 
AND acc.accountPos.id = :posId)
""")
    Optional<Account> findAccountofPackagingAndClientInPos(@Param("packagingId") Long packagingId, @Param("clientId") Long clientId,
                                                           @Param("posId") Long posId);
    @Query("""
SELECT acc FROM Account acc WHERE (acc.accountPackaging.id = :packagingId AND acc.accountProvider.id = :providerId 
AND acc.accountPos.id = :posId)
""")
    Optional<Account> findAccountofPackagingAndProviderInPos(@Param("packagingId") Long packagingId, @Param("providerId") Long providerId,
                                                             @Param("posId") Long posId);
    @Query("""
SELECT acc FROM Account acc WHERE (acc.accountArticle.id = :articleId AND acc.accountPos.id = :posId)
""")
    Optional<Account> findAccountofArticleOfPos(@Param("articleId") Long articleId, @Param("posId") Long posId);
    @Query("""
SELECT acc FROM Account acc WHERE (acc.accountPackaging.id = :packagingId AND acc.accountPos.id = :posId)
""")
    Optional<Account> findAccountofPackagingOfPos(@Param("packagingId") Long packagingId, @Param("posId") Long posId);
}
