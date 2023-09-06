package com.c2psi.bmv1.account.accountoperation.models;

import com.c2psi.bmv1.account.operation.models.Operation;
import com.c2psi.bmv1.bmapp.enumerations.AmountLocationEnum;
import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.provider.provider.models.Provider;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Table(name="cashoperation",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"operation_id", "pos_id"}),
                @UniqueConstraint(
                        columnNames = {"client_id", "operation_id", "pos_id"}),
                @UniqueConstraint(
                        columnNames = {"provider_id", "operation_id", "pos_id"})})
public class CashOperation {

    @Positive(message = "The amount in mouvement must be positive")
    @NotNull(message = "The amount in mouvement can't be null")
    Double amountinMvt;
    @NotNull(message = "The amount source location can't be null")
    AmountLocationEnum amountSource;
    @NotNull(message = "The amount destination location can't be null")
    AmountLocationEnum amountDestination;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "operation_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The operationId of an cashaccount can't be null")
    Operation operation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of an account can't be null")
    Pointofsale posConcerned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    Client clientConcerned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    Provider providerConcerned;



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
