package com.c2psi.bmv1.client.clientspecialprice.models;

import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.price.specialprice.models.Specialprice;
import com.c2psi.bmv1.product.article.models.Article;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
//@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Builder
@Entity
@Table(name="client_specialprice",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"client_id", "specialprice_id", "article_id"})})
public class ClientSpecialprice {

    @PastOrPresent(message = "The applied date can't be in the future")
    @NotNull(message = "The applied date can't be in the future")
    OffsetDateTime appliedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The clientId of a clientSpecialprice can't be null")
    Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialprice_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The specialpriceId of a clientSpecialprice can't be null")
    Specialprice specialprice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The articleId of a clientSpecialprice can't be null")
    Article article;

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
