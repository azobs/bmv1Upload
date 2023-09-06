package com.c2psi.bmv1.client.client.dao;

import com.c2psi.bmv1.client.client.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientDao extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
    Optional<Client> findClientById(Long id);
    @Query("""
SELECT clt FROM Client clt WHERE (clt.clientCni = :clientCni AND clt.clientPos.id = :posId)
""")
    Optional<Client> findClientByClientCniInPos(@Param("clientCni") String clientCni, @Param("posId") Long posId);
    @Query("""
SELECT clt FROM Client clt WHERE (clt.clientName = :clientName AND clt.clientOthername = :clientOthername 
AND clt.clientPos.id = :posId)
""")
    Optional<Client> findClientByFullname(@Param("clientName") String clientName,
                                          @Param("clientOthername") String clientOthername,
                                          @Param("posId") Long posId);
}
