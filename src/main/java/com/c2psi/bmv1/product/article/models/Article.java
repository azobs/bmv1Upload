package com.c2psi.bmv1.product.article.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.price.baseprice.models.Baseprice;
import com.c2psi.bmv1.product.pf.models.Productformated;
import com.c2psi.bmv1.product.unit.unit.models.Unit;
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
@Table(name="article",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"artCode", "pos_id"}),
                @UniqueConstraint(
                        columnNames = {"pf_id", "unit_id", "bp_id"})})
public class Article {

    @Size(max = 10, message = "An article code must have at most 10 characters")
    String artCode;
    @NotNull(message = "An article name can't be null value")
    @NotEmpty(message = "An article name can't be empty value")
    @NotBlank(message = "An article name can't be blank value")
    @Size(min = 3, max = 30, message = "An article name must have at least 3 and at most 30 characters")
    @Column(nullable = false)
    String artName;
    @Size(max = 20, message = "An article name must have at most 20 characters")
    String artShortname;
    @Size(max = 256, message = "An article name must have at most 256 characters")
    String artDescription;
    @PositiveOrZero(message = "The threshold must be positive value or zero")
    Double artThreshold;
    @PositiveOrZero(message = "The threshold must be positive value or zero")
    Double artLowlimitwholesale;
    @PositiveOrZero(message = "The low limit of semi whole sale must be positive value or zero")
    Double artlowlimitSemiwholesale;
    @PositiveOrZero(message = "The quantity in stock must be positive value or zero")
    Double artQuantityinstock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pf_id", referencedColumnName = "id")
    Productformated artPf;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "unit_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The unitId of a Article can't be null")
    Unit artUnit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bp_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The baseprice of an article can't be null value")
    Baseprice artBaseprice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of a Article can't be null")
    Pointofsale artPos;


    @BmNotBlank
    public String getArtCode() {
        return artCode;
    }

    @BmNotBlank
    public String getArtShortname() {
        return artShortname;
    }

    @BmNotBlank
    public String getArtDescription() {
        return artDescription;
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
