package com.c2psi.bmv1.account.account.models;

import com.c2psi.bmv1.bmapp.enumerations.AccountTypeEnum;
import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.packaging.packaging.models.Packaging;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.product.article.models.Article;
import com.c2psi.bmv1.provider.provider.models.Provider;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name="account",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"article_id", "client_id", "pos_id"}),
                @UniqueConstraint(
                        columnNames = {"article_id", "provider_id", "pos_id"}),
                @UniqueConstraint(
                        columnNames = {"packaging_id", "client_id", "pos_id"}),
                @UniqueConstraint(
                        columnNames = {"packaging_id", "provider_id", "pos_id"}),
                @UniqueConstraint(
                        columnNames = {"article_id", "pos_id"}),
                @UniqueConstraint(
                        columnNames = {"packaging_id", "pos_id"})})
public class Account {

    Integer coverNumber;
    Integer damageNumber;
    Integer packageNumber;
    @NotNull(message = "The account type can't be null value")//Client, Pos, Provider
    AccountTypeEnum accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    Client accountClient;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pos_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The posId of an account can't be null")
    Pointofsale accountPos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    Provider accountProvider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", referencedColumnName = "id")
    Article accountArticle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "packaging_id", referencedColumnName = "id")
    Packaging accountPackaging;


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
