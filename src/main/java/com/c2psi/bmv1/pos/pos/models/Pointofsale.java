package com.c2psi.bmv1.pos.pos.models;

import com.c2psi.bmv1.address.models.Address;
import com.c2psi.bmv1.bmapp.models.AbstractEntity;
import com.c2psi.bmv1.currency.models.Currency;
import com.c2psi.bmv1.pos.enterprise.models.Enterprise;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name="pointofsale",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"posName", "posAcronym"})})
public class Pointofsale extends AbstractEntity {
    String posName;
    String posAcronym;
    String posDescription;

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
    Currency posCurrency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ent_id", nullable = false, referencedColumnName = "id")
    Enterprise posEnterprise;
}
