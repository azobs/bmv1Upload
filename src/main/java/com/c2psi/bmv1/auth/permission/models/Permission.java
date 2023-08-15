package com.c2psi.bmv1.auth.permission.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
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
@Builder
@Entity
@Table(name="permission")
public class Permission {

    @NotNull(message = "A permission name can't be null value")
    @NotEmpty(message = "A permission name can't be empty value")
    @NotBlank(message = "A permission name can't be blank value")
    @Size(min = 3, max = 30, message = "A permission name must have at least 3 and at most 30 characters")
    @Column(unique = true)
    String permissionName;
    @Size(max = 256, message = "A permission description must have at most 256 characters")
    String permissionDescription;


    @BmNotBlank
    public String getPermissionName() {
        return permissionName;
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
