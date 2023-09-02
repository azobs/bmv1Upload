package com.c2psi.bmv1.packaging.packaging.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.provider.provider.models.Provider;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name="packaging",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"packLabel", "packFirstcolor", "provider_id", "pos_id"})})
public class Packaging {

    @NotNull(message = "A packaging label can't be null value")
    @NotEmpty(message = "A packaging label can't be empty value")
    @NotBlank(message = "A packaging label can't be blank value")
    @Size(min = 3, max = 30, message = "A packaging label must have at least 3 and at most 30 characters")
    @Column(nullable = false)
    String packLabel;
    @Size(max = 256, message = "A packaging description must have at most 256 characters")
    String packDescription;
    @NotNull(message = "A packaging first color can't be null value")
    @NotEmpty(message = "A packaging first color can't be empty value")
    @NotBlank(message = "A packaging first color can't be blank value")
    @Size(min = 3, max = 30, message = "A packaging first color must have at least 3 and at most 30 characters")
    @Column(nullable = false)
    String packFirstcolor;
    @NotNull(message = "A packaging price can't be null value")
    @Positive(message = "A packaging price must be positive value")
    Double packPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "provider_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The providerId of a packaging can't be null")
    Provider packagingProvider;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of a packaging can't be null")
    Pointofsale packagingPos;

    @BmNotBlank
    public String getPackDescription() {
        return packDescription;
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
