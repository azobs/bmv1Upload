package com.c2psi.bmv1.provider.provider.dao;

import com.c2psi.bmv1.provider.provider.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProviderDao extends JpaRepository<Provider, Long>, JpaSpecificationExecutor<Provider> {
    Optional<Provider> findProviderById(Long id);
    @Query("""
SELECT provider FROM Provider provider WHERE (provider.providerName = :providerName AND 
provider.providerAcronym = :providerAcronym AND provider.providerPos.id = :posId)
""")
    Optional<Provider> findProviderByNameAndAcronymInPos(@Param("providerName") String providerName,
                                                         @Param("providerAcronym") String providerAcronym,
                                                         @Param("posId") Long posId);
}
