package com.c2psi.bmv1.sale.command.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.enumerations.CommandStateEnum;
import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.loading.loading.models.Loading;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.sale.delivery.delivery.models.Delivery;
import com.c2psi.bmv1.sale.saleinvoice.models.Saleinvoice;
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
@Table(name="command",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"cmdCode", "pos_id"})})
public class Command {
    @NotNull(message = "A command code can't be null value")
    @NotEmpty(message = "A command code can't be empty value")
    @NotBlank(message = "A command code can't be blank value")
    @Size(min = 1, max = 30, message = "A command code must have at least 1 and at most 30 characters")
    @Column(nullable = false)
    String cmdCode;
    @NotNull(message = "The command date can't be null value")
    @PastOrPresent(message = "The command date of the command can't be in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    OffsetDateTime cmdDate;
    String cmdComment;
    @NotNull(message = "The command state can't be null value")//InEditing, Edited, InDelivery, Delivery
    CommandStateEnum cmdState;
    @NotNull(message = "The command nature can't be null value")//CASH, COVER, DAMAGE
    CommandStateEnum cmdNature;

    @BmNotBlank
    public String getCmdComment() {
        return cmdComment;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", referencedColumnName = "id")
    Delivery cmdDelivery;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loading_id", referencedColumnName = "id")
    Loading cmdLoading;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The clientId of a command can't be null")
    Client cmdClient;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "saler_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The salerId of a command can't be null")
    Userbm cmdSaler;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "saleinvoice_id", referencedColumnName = "id")
    Saleinvoice cmdSaleinvoice;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of a command can't be null")
    Pointofsale cmdPos;


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
