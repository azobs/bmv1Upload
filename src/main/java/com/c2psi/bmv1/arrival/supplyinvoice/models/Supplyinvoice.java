package com.c2psi.bmv1.arrival.supplyinvoice.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.enumerations.PaimentMethodEnum;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.provider.provider.models.Provider;
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
@Table(name="supplyinvoice",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"siCode", "pos_id", "provider_id"}),
                @UniqueConstraint(
                        columnNames = {"siCode", "pos_id"})})
public class Supplyinvoice {

    @Size(max = 10, message = "A supply invoice code must have at most 10 characters")
    String siCode;
    @Size(max = 256, message = "A supply invoice comment must have at most 256 characters")
    String siComment;
    @Size(max = 50, message = "A supply invoice picture name must have at most 50 characters")
    String siPicture;
    @NotNull(message = "The delivery date can't be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @PastOrPresent(message = "The delivery date of the supplyinvoice can't be in the future")
    OffsetDateTime siDeliverydate;
    @NotNull(message = "The invoicing date can't be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @PastOrPresent(message = "The invoicing date of the supplyinvoice can't be in the future")
    OffsetDateTime siInvoicingdate;
    @Positive(message = "The total colis to delivery in a supply invoice must be positive value")
    @NotNull(message = "The total number of colis can't be null")
    Double siTotalcolis;
    @Positive(message = "The expected amount to paid for a supply invoice must be positive value")
    @NotNull(message = "The expected amount of the supplyinvoice can't be null")
    Double siExpectedamount;
    @PositiveOrZero(message = "The amount paid for a supply invoice must be positive value or zero")
    @NotNull(message = "The amount really paid of the supplyinvoice can't be null")
    Double siPaidamount;
    @NotNull(message = "A paiement method of a supply invoice can't be null value")
    PaimentMethodEnum siPaymentmethod;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of a supplyinvoice can't be null")
    Pointofsale siPos;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    Provider siProvider;


    @BmNotBlank
    public String getSiCode() {
        return siCode;
    }

    @BmNotBlank
    public String getSiComment() {
        return siComment;
    }

    @BmNotBlank
    public String getSiPicture() {
        return siPicture;
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
