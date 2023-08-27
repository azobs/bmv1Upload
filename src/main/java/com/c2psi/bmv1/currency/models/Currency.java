package com.c2psi.bmv1.currency.models;

import com.c2psi.bmv1.bmapp.models.AbstractEntity;
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
@Table(name="currency",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"currencyName", "currencyAbbreviation"})})
public class Currency /*extends AbstractEntity*/ {
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
