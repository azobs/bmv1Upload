package com.c2psi.bmv1.pos.enterprise.models;

import com.c2psi.bmv1.bmapp.models.AbstractEntity;
import com.c2psi.bmv1.bmapp.enumerations.EntRegimeEnum;
import jakarta.persistence.Column;
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
@Table(name="enterprise",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"entName", "entAcronym"})})
public class Enterprise extends AbstractEntity {
    @NotNull(message = "An enterprise regime can't be null value")
    EntRegimeEnum entRegime;
    @NotEmpty(message = "A Niu can't be empty value")
    @NotBlank(message = "A Niu can't be blank value")
    @Size(max = 30, message = "An enterprise social reason must have at most 30 characters")
    String entSocialreason;
    @NotEmpty(message = "A Niu can't be empty value")
    @NotBlank(message = "A Niu can't be blank value")
    @Size(max = 256, message = "An enterprise description must have at most 256 characters")
    String entDescription;
    @NotEmpty(message = "A Niu can't be empty value")
    @NotBlank(message = "A Niu can't be blank value")
    @Size(min = 3, max = 15, message = "A Niu must have at least 3 and at most 15 characters")
    @Column(unique = true)
    String entNiu;
    @NotNull(message = "An enterprise name can't be null value")
    @NotEmpty(message = "An enterprise name can't be empty value")
    @NotBlank(message = "An enterprise name can't be blank value")
    @Size(min = 2, max = 30, message = "An enterprise name must have at least 3 and at most 30 characters")
    String entName;
    @NotNull(message = "An enterprise acronym can't be null value")
    @NotEmpty(message = "An enterprise acronym can't be empty value")
    @NotBlank(message = "An enterprise acronym can't be blank value")
    @Size(min = 1, max = 15, message = "An enterprise acronym must have at least 3 and at most 15 characters")
    String entAcronym;
    @NotEmpty(message = "Logo name can't be empty value")
    @NotBlank(message = "Logo name can't be blank value")
    String entLogo;
}
