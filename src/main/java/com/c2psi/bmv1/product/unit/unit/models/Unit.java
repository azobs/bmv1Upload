package com.c2psi.bmv1.product.unit.unit.models;

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
@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name="unit",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"unitName", "unitAbbreviation", "pos_id"})})
public class Unit {

    @NotNull(message = "A unit name can't be null value")
    @NotEmpty(message = "A unit name can't be empty value")
    @NotBlank(message = "A unit name can't be blank value")
    @Size(min = 3, max = 30, message = "A unit name must have at least 3 and at most 30 characters")
    String unitName;
    @NotNull(message = "A unit abbreviation can't be null value")
    @NotEmpty(message = "A unit abbreviation can't be empty value")
    @NotBlank(message = "A unit abbreviation can't be blank value")
    @Size(min = 3, max = 30, message = "A unit abbreviation must have at least 3 and at most 15 characters")
    String unitAbbreviation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of a Unit can't be null")
    Pointofsale unitPos;

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
