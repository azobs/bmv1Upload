package com.c2psi.bmv1.client.client.models;

import com.c2psi.bmv1.address.models.Address;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
//@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Builder
@Entity
@Table(name="client",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"clientName", "clientOthername", "pos_id"}),
                @UniqueConstraint(
                        columnNames = {"clientCni", "pos_id"})})
public class Client {
    @NotNull(message = "A client name can't be null value")
    @NotEmpty(message = "A client name can't be empty value")
    @NotBlank(message = "A client name can't be blank value")
    @Size(min = 3, max = 30, message = "A client name must have at least 3 and at most 30 characters")
    @Column(nullable = false)
    String clientName;
    @Size(max = 30, message = "A client othername must have at most 30 characters")
    String clientOthername;
    @Size(max = 17, message = "A client othername must have at most 17 characters")
    String clientCni;
    @NotNull(message = "A client balance can't be null value")
    Double clientBalance;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotNull(message = "A client address can't be null value")
    Address clientAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of a client can't be null")
    Pointofsale clientPos;


    @BmNotBlank
    public String getClientOthername() {
        return clientOthername;
    }

    @BmNotBlank
    public String getClientCni() {
        return clientCni;
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
