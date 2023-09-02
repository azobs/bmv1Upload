package com.c2psi.bmv1.provider.provider.models;

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
@Table(name="provider",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"providerName", "providerAcronym", "pos_id"})})
public class Provider {

    @NotNull(message = "A provider name can't be null value")
    @NotEmpty(message = "A provider name can't be empty value")
    @NotBlank(message = "A provider name can't be blank value")
    @Size(min = 3, max = 30, message = "A provider name must have at least 3 and at most 30 characters")
    @Column(nullable = false)
    String providerName;
    @NotNull(message = "A provider acronym can't be null value")
    @NotEmpty(message = "A provider acronym can't be empty value")
    @NotBlank(message = "A provider acronym can't be blank value")
    @Size(min = 1, max = 15, message = "A provider acronym must have at least 1 and at most 15 characters")
    @Column(nullable = false)
    String providerAcronym;
    @Size(max = 256, message = "A provider description must have at most 256 characters")
    String providerDescription;
    @NotNull(message = "A provider balance can't be null value")
    Double providerBalance;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotNull(message = "A provider address can't be null value")
    Address providerAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of a Provider can't be null")
    Pointofsale providerPos;


    @BmNotBlank
    public String getProviderDescription() {
        return providerDescription;
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
