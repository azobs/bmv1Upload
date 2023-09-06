package com.c2psi.bmv1.client.clientspecialprice.dao;

import com.c2psi.bmv1.client.clientspecialprice.models.ClientSpecialprice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientSpecialpriceDao extends JpaRepository<ClientSpecialprice, Long>,
        JpaSpecificationExecutor<ClientSpecialprice> {
    Optional<ClientSpecialprice> findClientSpecialpriceById(Long id);
    @Query("""
SELECT cltsp FROM ClientSpecialprice cltsp WHERE (cltsp.article.id = :articleId AND cltsp.client.id = :clientId 
AND cltsp.specialprice.id = :specialpriceId)
""")
    Optional<ClientSpecialprice> findClientSpecialpriceByAttributes(@Param("clientId") Long clientId,
                                                                    @Param("specialpriceId") Long specialpriceId,
                                                                    @Param("articleId") Long articleId);
}
