package com.c2psi.bmv1.sale.backin.backin.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.sale.command.models.Command;
import com.c2psi.bmv1.userbm.models.Userbm;
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
@EntityListeners(AuditingEntityListener.class)
//@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Table(name="backin",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"command_id"})})
public class Backin {

    @NotNull(message = "The backin date can't be null")
    @PastOrPresent(message = "The backin date can't be in the future")
    OffsetDateTime biDate;
    String biComment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "command_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The commandId of a backin can't be null")
    Command biCommand;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "saler_id", nullable = false, referencedColumnName = "id")
    @NotNull(message = "The userbmId of a backin can't be null")
    Userbm biSaler;


    @BmNotBlank
    public String getBiComment() {
        return biComment;
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
