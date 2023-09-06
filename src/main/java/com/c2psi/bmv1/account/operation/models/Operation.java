package com.c2psi.bmv1.account.operation.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.enumerations.OpTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
@Table(name="operation")
public class Operation {
    @NotNull(message = "The operation date can't be null value")
    @PastOrPresent(message = "The operation date of the operation can't be in the future")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    OffsetDateTime opDate;
    @NotNull(message = "An operation object can't be null value")
    @NotEmpty(message = "An operation object can't be empty value")
    @NotBlank(message = "An operation object can't be blank value")
    @Size(min = 3, max = 30, message = "An operation object must have at least 3 and at most 30 characters")
    @Column(nullable = false)
    String opObject;
    @Size(max = 256, message = "An operation description must have at most 256 characters")
    String opDescription;
    @NotNull(message = "The operation type can't be null value")//Credit, Withdrawal, Change, Others
    OpTypeEnum opType;

    @BmNotBlank
    public String getOpDescription() {
        return opDescription;
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
