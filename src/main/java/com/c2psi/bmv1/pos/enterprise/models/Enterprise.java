package com.c2psi.bmv1.pos.enterprise.models;

import com.c2psi.bmv1.bmapp.models.AbstractEntity;
import com.c2psi.bmv1.bmapp.enumerations.EntRegimeEnum;
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
@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name="enterprise",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"entName", "entAcronym"})})
public class Enterprise /*extends AbstractEntity*/ {
    @NotNull(message = "An enterprise regime can't be null value")
    EntRegimeEnum entRegime;
    @NotBlank(message = "A Niu can't be blank value")
    @Size(max = 30, message = "An enterprise social reason must have at most 30 characters")
    String entSocialreason;
    @NotBlank(message = "A Niu can't be blank value")
    @Size(max = 256, message = "An enterprise description must have at most 256 characters")
    String entDescription;
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
    @NotBlank(message = "Logo name can't be blank value")
    String entLogo;








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
}
