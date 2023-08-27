package com.c2psi.bmv1.product.category.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
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
@Table(name="category",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"catCode", "pos_id"})})
public class Category {

    @NotNull(message = "A category name can't be null value")
    @NotEmpty(message = "A category name can't be empty value")
    @NotBlank(message = "A category name can't be blank value")
    @Size(min = 3, max = 30, message = "A category name must have at least 3 and at most 30 characters")
    String catName;
    @Size(max = 20, message = "A pos description must have at most 20 characters")
    String catShortname;
    @Size(max = 30, message = "A pos description must have at most 30 characters")
    String catCode;
    @Size(max = 256, message = "A pos description must have at most 256 characters")
    String catDescription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catParent_id", referencedColumnName = "id")
    Category catParent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of a Category can't be null")
    Pointofsale catPos;

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

    @BmNotBlank
    public String getCatShortname() {
        return catShortname;
    }

    @BmNotBlank
    public String getCatCode() {
        return catCode;
    }

    @BmNotBlank
    public String getCatDescription() {
        return catDescription;
    }
}
