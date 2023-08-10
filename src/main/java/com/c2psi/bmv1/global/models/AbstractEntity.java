package com.c2psi.bmv1.global.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Data
@MappedSuperclass//Designates a class whose mapping information is applied to the entities that
// inherit from it. A mapped superclass has no separate table defined for it.
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    private Long id;
    @CreatedDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "creationDate", nullable = false, updatable = false)
    private Instant creationDate;
    @LastModifiedDate
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "lastModifiedDate")
    private Instant lastModifiedDate;
}
