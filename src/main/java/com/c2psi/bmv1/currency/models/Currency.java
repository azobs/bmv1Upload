package com.c2psi.bmv1.currency.models;

import com.c2psi.bmv1.bmapp.models.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name="currency",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"currencyName", "currencyAbbreviation"})})
public class Currency extends AbstractEntity {
    @NotNull(message = "A currency name can't be null value")
    @NotEmpty(message = "A currency name can't be empty value")
    @NotBlank(message = "A currency name can't be blank value")
    @Size(min = 3, max = 30, message = "A currency name must have at least 3 and at most 30 characters")
    String currencyName;
    @NotNull(message = "A currency abbreviation can't be null value")
    @NotEmpty(message = "A currency abbreviation can't be empty value")
    @NotBlank(message = "A currency abbreviation can't be blank value")
    @Size(min = 1, max = 10, message = "A currency abbreviation must have at least 1 and at most 10 characters")
    String currencyAbbreviation;
}
