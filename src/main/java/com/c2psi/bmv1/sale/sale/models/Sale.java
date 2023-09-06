package com.c2psi.bmv1.sale.sale.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.enumerations.SaleTypeEnum;
import com.c2psi.bmv1.product.article.models.Article;
import com.c2psi.bmv1.sale.command.models.Command;
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
@Table(name="sale",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"article_id", "command_id"})})
public class Sale {

    @Positive(message = "The salequantity must be positive value")
    @NotNull(message = "The salequantity can't be null value")
    Double saleQuantity;
    String saleComment;
    @PositiveOrZero(message = "The salefinalprice must be positive value or zero")
    @NotNull(message = "The salefinalprice can't be null value")
    Double saleFinalprice;
    @NotNull(message = "The saletype can't be null")
    SaleTypeEnum saleType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The articleId of a sale can't be null")
    Article saleArticle;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "command_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The commandId of a sale can't be null")
    Command saleCommand;


    @BmNotBlank
    public String getSaleComment() {
        return saleComment;
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
