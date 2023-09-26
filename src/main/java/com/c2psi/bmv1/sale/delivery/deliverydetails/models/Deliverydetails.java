package com.c2psi.bmv1.sale.delivery.deliverydetails.models;

import com.c2psi.bmv1.packaging.packaging.models.Packaging;
import com.c2psi.bmv1.sale.delivery.delivery.models.Delivery;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name="deliverydetails",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"ddpackaging_id", "dddelivery_id"})})
public class Deliverydetails {

    @Positive(message = "The package used must be positive")
    @NotNull(message = "The package used can't for deleverydetails can't be null")
    Integer packageUsed;
    @PositiveOrZero(message = "The package returned must be positive or Zero")
    @NotNull(message = "The package returned can't for deleverydetails can't be null")
    Integer packageReturn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ddpackaging_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The packagingId of a delivery can't be null")
    Packaging ddPackaging;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dddelivery_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The deliveryId of a delivery can't be null")
    Delivery ddDelivery;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
    @SequenceGenerator(name="id_generator", sequenceName = "id_seq", allocationSize=50)
    @Column(updatable = false)
    private Long id;
    @CreatedDate
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "creationDate", nullable = false, updatable = false)
    private LocalDateTime creationDate;
    @LastModifiedDate
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "lastModifiedDate")
    private LocalDateTime lastModifiedDate;
}
