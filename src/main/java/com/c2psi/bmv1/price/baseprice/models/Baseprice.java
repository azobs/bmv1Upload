package com.c2psi.bmv1.price.baseprice.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.currency.models.Currency;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
@Table(name="baseprice",
        uniqueConstraints = {@UniqueConstraint(
            columnNames = {"bpCode", "pos_id"})})
public class Baseprice {

    @Size(max = 20, message = "An baseprice code must have at most 20 characters")
    String bpCode;
    @Positive(message = "The purchase price must be positive")
    @NotNull(message = "The purchase price associate can't be null value")
    Double bpPurchaseprice;
    @Positive(message = "The whole price must be positive")
    Double bpWholeprice;
    @Positive(message = "The semi whole price must be positive")
    Double bpSemiwholeprice;
    @Positive(message = "The details price must be positive")
    @NotNull(message = "The details price associate can't be null value")
    Double bpDetailsprice;
    @NotNull(message = "The precompte associate can't be null value")
    Double bpPrecompte;
    @NotNull(message = "The ristourne associate can't be null value")
    Double bpRistourne;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The currency of the base price can't be null value")
    Currency bpCurrency;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The pointofsale of the base price can't be null value")
    Pointofsale bpPos;

    @BmNotBlank
    public String getBpCode() {
        return bpCode;
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
