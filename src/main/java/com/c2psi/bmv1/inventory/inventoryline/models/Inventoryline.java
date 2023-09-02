package com.c2psi.bmv1.inventory.inventoryline.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.inventory.inventory.models.Inventory;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.product.article.models.Article;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
@Table(name="inventoryline",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"inventory_id", "article_id"})})
public class Inventoryline {

    @Size(max = 256, message = "An Inventory line comment must have at most 256 characters")
    String invlineComment;
    @PositiveOrZero(message = "The real quantity in stock must be positive or zero")
    @NotNull(message = "The real quantity in stock can't be null")
    Double realqteinStock;
    @PositiveOrZero(message = "The logic quantity (quantity that is suppose to be) in stock must be positive or zero")
    @NotNull(message = "The logic quantity in stock can't be null")
    Double logicqteinStock;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventory_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The invId of an inventoryline can't be null")
    Inventory inventory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The articleId of an inventoryline can't be null")
    Article invlineArticle;


    @BmNotBlank
    public String getInvlineComment() {
        return invlineComment;
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
