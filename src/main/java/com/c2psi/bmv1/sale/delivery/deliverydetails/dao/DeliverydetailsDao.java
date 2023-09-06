package com.c2psi.bmv1.sale.delivery.deliverydetails.dao;

import com.c2psi.bmv1.sale.delivery.deliverydetails.models.Deliverydetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliverydetailsDao extends JpaRepository<Deliverydetails, Long>, JpaSpecificationExecutor<Deliverydetails> {
    Optional<Deliverydetails> findDeliverydetailsById(Long id);
    @Query("""
SELECT dd FROM Deliverydetails dd WHERE (dd.ddDelivery.id = :deliveryId AND dd.ddPackaging.id = :packagingId)
""")
    Optional<Deliverydetails> findDeliverydetailsByPackagingInDelivery(@Param("packagingId") Long packagingId,
                                                                       @Param("deliveryId") Long deliveryId);
}
