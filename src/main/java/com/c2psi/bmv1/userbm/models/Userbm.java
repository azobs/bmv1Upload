package com.c2psi.bmv1.pos.pos.controllers.userbm.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.models.AbstractEntity;
import com.c2psi.bmv1.address.models.Address;
import com.c2psi.bmv1.bmapp.enumerations.UserStateEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@Builder
@Entity
@Table(name="userbm",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"userName", "userSurname", "userDob"})})
public class Userbm /*extends AbstractEntity*/ {
    @NotNull(message = "A user login can't be null value")
    @NotEmpty(message = "A user login can't be empty value")
    @NotBlank(message = "A user login can't be blank value")
    @Size(min = 3, max = 30, message = "A user login must have at least 3 and at most 30 characters")
    @Column(unique = true)
    String userLogin;
    /*@NotBlank(message = "A user cni can't be blank value")
    @Size(min = 9, max = 17, message = "A user cni must have at least 9 and at most 17 characters")*/
    @Size(min = 9, max = 17, message = "A user cni must have at least 9 and at most 17 characters")
    @Column(unique = true)
    String userCni;
    @NotNull(message = "A user password can't be null value")
    @NotEmpty(message = "A user password can't be empty value")
    @NotBlank(message = "A user password can't be blank value")
    @Size(min = 4, message = "A user password must have at least 4 characters")
    String userPassword;
    @NotNull(message = "A user name can't be null value")
    @NotEmpty(message = "A user name can't be empty value")
    @NotBlank(message = "A user name can't be blank value")
    @Size(min = 4, max = 30, message = "A user name must have at least 4 and at most 30 characters")
    String userName;
    @Size(max = 30, message = "A user surname must have at most 30 characters")
    String userSurname;
    //@DateTimeFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate userDob;
    String userPicture;
    @NotNull(message = "A user state can't be null value")
    UserStateEnum userState;
    /*********************************
     * Relation with other entities
     *********************************/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @NotNull(message = "A user address can't be null value")
    Address userAddress;







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


    @BmNotBlank
    public String getUserCni() {
        return userCni;
    }

    @BmNotBlank
    public String getUserSurname() {
        return userSurname;
    }

    @BmNotBlank
    public String getUserPicture() {
        return userPicture;
    }
}
