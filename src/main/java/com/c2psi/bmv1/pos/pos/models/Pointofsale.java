package com.c2psi.bmv1.pos.pos.models;

import com.c2psi.bmv1.address.models.Address;
import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.models.AbstractEntity;
import com.c2psi.bmv1.currency.models.Currency;
import com.c2psi.bmv1.pos.enterprise.models.Enterprise;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
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
@Table(name="pointofsale",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"posName", "posAcronym"})})
public class Pointofsale /*extends AbstractEntity*/ {
    @NotNull(message = "A pos name can't be null value")
    @NotEmpty(message = "A pos name can't be empty value")
    @NotBlank(message = "A pos name can't be blank value")
    @Size(min = 3, max = 30, message = "A pos name must have at least 3 and at most 30 characters")
    @Column(nullable = false)
    String posName;
    @NotNull(message = "A pos acronym can't be null value")
    @NotEmpty(message = "A pos acronym can't be empty value")
    @NotBlank(message = "A pos acronym can't be blank value")
    @Size(min = 1, max = 15, message = "A pos acronym must have at least 1 and at most 15 characters")
    @Column(nullable = false)
    String posAcronym;
//    @NotBlank(message = "A pos description can't be blank value")
    @Size(max = 256, message = "A pos description must have at most 256 characters")
    String posDescription;
    @NotNull(message = "A pos balance can't be null value")
    Double posBalance;

    /*********************************
     * Relation with other entities
     *********************************/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotNull(message = "A pos address can't be null value")
    Address posAddress;

    //Te relation with Account will be added after

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "currency_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "A default currency of pos can't be null value")
    Currency posCurrency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ent_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "An enterprise of pos can't be null value")
    Enterprise posEnterprise;




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    @BmNotBlank
    public String getPosDescription() {
        return posDescription;
    }
}
