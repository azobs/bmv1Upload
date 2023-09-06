package com.c2psi.bmv1.sale.delivery.delivery.dao;

import com.c2psi.bmv1.sale.delivery.delivery.models.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryDao extends JpaRepository<Delivery, Long>, JpaSpecificationExecutor<Delivery> {
    Optional<Delivery> findDeliveryById(Long id);
    @Query("""
SELECT del FROM Delivery del WHERE (del.deliveryCode = :deliveryCode AND del.deliveryPos.id = :posId)
""")
    Optional<Delivery> findDeliveryByCodeInPos(@Param("deliveryCode") String deliveryCode,
                                               @Param("posId") Long posId);
}
