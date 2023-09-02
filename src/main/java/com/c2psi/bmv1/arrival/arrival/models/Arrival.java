package com.c2psi.bmv1.arrival.arrival.models;

import com.c2psi.bmv1.arrival.supplyinvoice.models.Supplyinvoice;
import com.c2psi.bmv1.bmapp.enumerations.ArrivalNatureEnum;
import com.c2psi.bmv1.bmapp.enumerations.ArrivalTypeEnum;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.product.article.models.Article;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name="supplyinvoice",
        uniqueConstraints = {@UniqueConstraint(
                        columnNames = {"article_id", "si_id"})})
public class Arrival {

    @NotNull(message = "The delivery quantity of the arrival can't be null value")
    @Positive(message = "The delivery quantity must be positive value")
    Double deliveryQuantity;
    @NotNull(message = "The arrival date can't be null value")
    @PastOrPresent(message = "The arrival date of the arrival can't be in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    OffsetDateTime arrivalDate;
    @PositiveOrZero(message = "The arrival unit price must be positive value or zero")
    @NotNull(message = "The unit price of the arrival can't be null value")
    Double arrivalUnitprice;
    @NotNull(message = "The arrival type can't be null value")//STANDARD, DIVERS
    ArrivalTypeEnum arrivalType;
    @NotNull(message = "The arrival nature can't be null value")//CASH, COVER, DAMAGE
    ArrivalTypeEnum arrivalNature;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The articleId of an arrival can't be null")
    Article arrivalArticle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "si_id", referencedColumnName = "id")
    Supplyinvoice arrivalSi;


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
