package com.c2psi.bmv1.sale.saleinvoice.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.enumerations.PaimentMethodEnum;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
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
@Table(name="saleinvoice",
        uniqueConstraints = {@UniqueConstraint(
                        columnNames = {"siCode", "pos_id"})})
public class Saleinvoice {

    @NotNull(message = "A saleinvoice code can't be null value")
    @NotEmpty(message = "A saleinvoice code can't be empty value")
    @NotBlank(message = "A saleinvoice code can't be blank value")
    @Size(min = 3, max = 30, message = "A saleinvoice code must have at least 3 and at most 30 characters")
    @Column(nullable = false)
    String siCode;
    @Size(max = 256, message = "A sale invoice comment must have at most 256 characters")
    String siComment;
    @NotNull(message = "The invoicing date can't be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @PastOrPresent(message = "The invoicing date of the saleinvoice can't be in the future")
    OffsetDateTime siInvoicingdate;
    @Positive(message = "The total colis to delivery in a sale invoice must be positive value")
    @NotNull(message = "The total number of colis can't be null")
    Double siTotalcolis;
    @Positive(message = "The expected amount to paid for a sale invoice must be positive value")
    @NotNull(message = "The expected amount of the saleinvoice can't be null")
    Double siExpectedamount;
    @PositiveOrZero(message = "The amount paid to paid for a sale invoice must be positive value or zero")
    @NotNull(message = "The amount paid of the saleinvoice can't be null")
    Double siPaidamount;
    @NotNull(message = "A paiement method of a sale invoice can't be null value")
    PaimentMethodEnum siPaymentmethod;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of a saleinvoice can't be null")
    Pointofsale siPos;

    @BmNotBlank
    public String getSiComment() {
        return siComment;
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
