package com.c2psi.bmv1.role.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.pos.enterprise.models.Enterprise;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import com.c2psi.bmv1.bmapp.enumerations.RoleTypeEnum;
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
//@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Builder
@Entity
@Table(name="role",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"roleName", "roleType"})})
public class Role {

    @NotNull(message = "A role name can't be null value")
    @NotEmpty(message = "A role name can't be empty value")
    @NotBlank(message = "A role name can't be blank value")
    @Size(min = 3, max = 30, message = "A role name must have at least 3 and at most 30 characters")
    String roleName;
    @Size(max = 256, message = "A user cni must have at most 256 characters")
    String roleDescription;
    @NotNull(message = "A role type can't be null value")
    RoleTypeEnum roleType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pos_id", referencedColumnName = "id")
    Pointofsale rolePos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ent_id", referencedColumnName = "id")
    Enterprise roleEnt;

    @BmNotBlank
    public String getRoleName() {
        return roleName;
    }

    @BmNotBlank
    public String getRoleDescription() {
        return roleDescription;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
