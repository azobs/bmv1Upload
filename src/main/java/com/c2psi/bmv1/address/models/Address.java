package com.c2psi.bmv1.address.models;

import com.c2psi.bmv1.bmapp.annotations.BmNotBlank;
import com.c2psi.bmv1.bmapp.models.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
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
@Table(name="address")
public class Address /*extends AbstractEntity*/ {
//    @NotBlank(message = "A phone number can't be blank value")
    @Size(min = 9, max = 13, message = "A phone number must have at least 9 and at most 13 characters")
    String numtel1;
//    @NotBlank(message = "A phone number can't be blank value")
    @Size(min = 9, max = 13, message = "A phone number must have at least 9 and at most 13 characters")
    String numtel2;
//    @NotBlank(message = "A phone number can't be blank value")
    @Size(min = 9, max = 13, message = "A phone number must have at least 9 and at most 13 characters")
    String numtel3;
//    @NotBlank(message = "A quarter name can't be blank value")
    @Size(max = 30, message = "A quarter name must have at most 30 characters")
    String quarter;
//    @NotBlank(message = "A country name can't be blank value")
    @Size(max = 30, message = "A country name must have at most 30 characters")
    String country;
//    @NotBlank(message = "A town name can't be blank value")
    @Size(max = 30, message = "A town name must have at most 30 characters")
    String town;
    //@NotBlank(message = "A localisation name can't be blank value")
    String localisation;
//    @NotBlank(message = "An email can't be blank value")
    @Size(max = 30, message = "An email must have at most 30 characters")
    @Column(unique = true)
    String email;




    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    public String getNumtel1() {
        return numtel1;
    }
    @BmNotBlank
    public String getNumtel2() {
        return numtel2;
    }
    @BmNotBlank
    public String getNumtel3() {
        return numtel3;
    }
    @BmNotBlank
    public String getQuarter() {
        return quarter;
    }
    @BmNotBlank
    public String getCountry() {
        return country;
    }
    @BmNotBlank
    public String getTown() {
        return town;
    }
    @BmNotBlank
    public String getLocalisation() {
        return localisation;
    }
    @BmNotBlank
    public String getEmail() {
        return email;
    }
}
