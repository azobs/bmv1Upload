package com.c2psi.bmv1.sale.backin.backindetails.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.product.article.models.Article;
import com.c2psi.bmv1.sale.backin.backin.models.Backin;
import com.c2psi.bmv1.sale.command.models.Command;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Table(name="backindetails",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"article_id", "backin_id"})})
public class Backindetails {

    @Positive(message = "The quantity return in the backin must be positive")
    @NotNull(message = "The quantity return can't be null")
    Double bidQuantity;
    String bidComment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The articleId of a backindetails can't be null")
    Article bidArticle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "backin_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The backinId of a backindetails can't be null")
    Backin bidBackin;

    @BmNotBlank
    public String getBidComment() {
        return bidComment;
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
