package com.c2psi.bmv1.loading.loadingdetails.models;

import com.c2psi.bmv1.loading.loading.models.Loading;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.product.article.models.Article;
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
@Table(name="loadingdetails",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"article_id", "loading_id"})})
public class Loadingdetails {

    @Positive(message = "The quantity taken must be positive")
    @NotNull(message = "The quantity taken in the loading can't be null")
    Double quantityTaken;
    @PositiveOrZero(message = "The quantity return must be positive or zero")
    @NotNull(message = "The quantity return in the loading can't be null")
    Double quantityReturn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The articleId of a loadingdetails can't be null")
    Article ldArticle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "loading_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The loadingId of a loadingdetails can't be null")
    Loading ldLoading;


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
