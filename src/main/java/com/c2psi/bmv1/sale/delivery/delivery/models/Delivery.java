package com.c2psi.bmv1.sale.delivery.delivery.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.enumerations.DeliveryStateEnum;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.userbm.models.Userbm;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name="delivery",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"deliveryCode", "pos_id"})})
public class Delivery {

    @NotNull(message = "A delivery code can't be null value")
    @NotEmpty(message = "A delivery code can't be empty value")
    @NotBlank(message = "A delivery code can't be blank value")
    @Size(min = 1, max = 10, message = "A delivery code must have at least 1 and at most 10 characters")
    @Column(nullable = false)
    String deliveryCode;
    @NotNull(message = "The delivery date can't be null value")
    @PastOrPresent(message = "The delivery date of the command can't be in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    OffsetDateTime deliveryDate;
    @Size(max = 256, message = "A delivery comment must have at most 256 characters")
    String deliveryComment;
    @NotNull(message = "The delivery state can't be null value")//InEditing, Edited, Delivery
    DeliveryStateEnum deliveryState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliver_id", referencedColumnName = "id")
    Userbm deliveryDeliver;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of a delivery can't be null")
    Pointofsale deliveryPos;


    @BmNotBlank
    public String getDeliveryCode() {
        return deliveryCode;
    }

    @BmNotBlank
    public String getDeliveryComment() {
        return deliveryComment;
    }

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
