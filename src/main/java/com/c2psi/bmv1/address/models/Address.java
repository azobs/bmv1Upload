package com.c2psi.bmv1.address.models;

import com.c2psi.bmv1.abstracts.models.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name="address")
public class Address extends AbstractEntity {
    @NotEmpty(message = "A phone number can't be empty value")
    @NotBlank(message = "A phone number can't be blank value")
    @Size(min = 9, max = 13, message = "A phone number must have at least 9 and at most 13 characters")
    String numtel1;
    @NotEmpty(message = "A phone number can't be empty value")
    @NotBlank(message = "A phone number can't be blank value")
    @Size(min = 9, max = 13, message = "A phone number must have at least 9 and at most 13 characters")
    String numtel2;
    @NotEmpty(message = "A phone number can't be empty value")
    @NotBlank(message = "A phone number can't be blank value")
    @Size(min = 9, max = 13, message = "A phone number must have at least 9 and at most 13 characters")
    String numtel3;
    @NotEmpty(message = "A quarter name can't be empty value")
    @NotBlank(message = "A quarter name can't be blank value")
    @Size(max = 30, message = "A quarter name must have at most 30 characters")
    String quarter;
    @NotEmpty(message = "A country name can't be empty value")
    @NotBlank(message = "A country name can't be blank value")
    @Size(max = 30, message = "A country name must have at most 30 characters")
    String country;
    @NotEmpty(message = "A town name can't be empty value")
    @NotBlank(message = "A town name can't be blank value")
    @Size(max = 30, message = "A town name must have at most 30 characters")
    String town;
    String localisation;
    @NotEmpty(message = "An email can't be empty value")
    @NotBlank(message = "An email can't be blank value")
    @Size(max = 30, message = "An email must have at most 30 characters")
    @Column(unique = true)
    String email;
}
