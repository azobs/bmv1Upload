package com.c2psi.bmv1.price.specialprice.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.price.baseprice.models.Baseprice;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Table(name="specialprice",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"spCode", "bp_id"})})
public class Specialprice {

    @Size(max = 20, message = "A special price code must have at most 20 characters")
    String spCode;
    @Positive(message = "The special whole price must be positive")
    Double spWholeprice;
    @Positive(message = "The special semi whole price must be positive")
    Double spSemiwholeprice;
    @Positive(message = "The special details price must be positive")
    @NotNull(message = "The special details price associate can't be null value")
    Double spDetailsprice;
    @NotNull(message = "The special ristourne associate can't be null value")
    Double spRistourne;
    @NotNull(message = "The special precompte associate can't be null value")
    Double spPrecompte;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bp_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The baseprice of the special price can't be null value")
    Baseprice spBaseprice;



    @BmNotBlank
    public String getSpCode() {
        return spCode;
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
