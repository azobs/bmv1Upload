package com.c2psi.bmv1.product.product.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.product.category.models.Category;
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
@Table(name="product",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"prodCode"})})
public class Product {

    @NotNull(message = "A product name can't be null value")
    @NotEmpty(message = "A product name can't be empty value")
    @NotBlank(message = "A product name can't be blank value")
    @Size(min = 3, max = 30, message = "A product name must have at least 3 and at most 30 characters")
    String prodName;
    @Size(max = 15, message = "A product code must have at most 15 characters")
    String prodCode;
    @Size(max = 256, message = "A product code must have at most 256 characters")
    String prodDescription;
    @Size(max = 20, message = "A product alias must have at most 20 characters")
    String prodAlias;
    @NotNull(message = "The perishable can't be null")
    Boolean prodPerishable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id", referencedColumnName = "id")
    Category prodCat;

    @BmNotBlank
    public String getProdCode() {
        return prodCode;
    }

    @BmNotBlank
    public String getProdDescription() {
        return prodDescription;
    }

    @BmNotBlank
    public String getProdAlias() {
        return prodAlias;
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
