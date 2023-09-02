package com.c2psi.bmv1.loading.loading.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
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
@Table(name="loading",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"loadCode", "pos_id"})})
public class Loading {

    @Size(max = 15, message = "An Inventory comment must have at most 15 characters")
    String loadCode;
    @PastOrPresent(message = "The loading date can't be in the future")
    @NotNull(message = "The loading date can't be null")
    OffsetDateTime loadDate;
    @PastOrPresent(message = "The loading return date can't be in the future")
    OffsetDateTime loadreturnDate;
    @Positive(message = "The expected amount of the loading must be positive")
    @NotNull(message = "The exepected amount of the loading can't be null")
    Double loadExpectedamount;
    @PositiveOrZero(message = "The paiement amount of the loading must be positive or null")
    @NotNull(message = "The paiement amount of the loading can't be null")
    Double loadPaidamount;
    @PositiveOrZero(message = "The remise amount of the loading must be positive or null")
    @NotNull(message = "The remise amount of the loading can't be null")
    Double loadRemise;
    @NotNull(message = "The load state of the loading can't be null")
    Boolean loadOpen;
    @Size(max = 1024, message = "An Inventory comment must have at most 1024 characters")
    String loadReport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userbm_manager_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The userbmId which is the manager of the loading can't be null")
    Userbm loadManager;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userbm_saler_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The userbmId which is the saler of the loading can't be null")
    Userbm loadSaler;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of a loading can't be null")
    Pointofsale loadPos;


    @BmNotBlank
    public String getLoadCode() {
        return loadCode;
    }

    @BmNotBlank
    public String getLoadReport() {
        return loadReport;
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
