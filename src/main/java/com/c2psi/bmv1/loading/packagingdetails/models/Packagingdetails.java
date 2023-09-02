package com.c2psi.bmv1.loading.packagingdetails.models;

import com.c2psi.bmv1.loading.loading.models.Loading;
import com.c2psi.bmv1.packaging.packaging.models.Packaging;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
@Table(name="packagingdetails",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"packaging_id", "loading_id"})})
public class Packagingdetails {

    @Positive(message = "The number of packaging used must be positive")
    @NotNull(message = "The number of packaging used can't be null")
    Integer packagenumberUsed;
    @NotNull(message = "The number of packaging return can't be null")
    @PositiveOrZero(message = "The number of packaging returned must be positive or zero")
    Integer packagenumberReturn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loading_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The loadingId of a packagingdetails can't be null")
    Loading pdLoading;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "packaging_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The packagingId of a packagingdetails can't be null")
    Packaging pdPackaging;

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
