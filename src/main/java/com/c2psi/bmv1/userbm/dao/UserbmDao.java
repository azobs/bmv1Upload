package com.c2psi.bmv1.pos.pos.controllers.userbm.dao;

import com.c2psi.bmv1.pos.pos.controllers.userbm.models.Userbm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserbmDao extends JpaRepository<Userbm, Long>, JpaSpecificationExecutor<Userbm> {
    Optional<Userbm> findUserbmById(Long id);
    Optional<Userbm> findUserbmByUserCni(String userCni);
    Optional<Userbm> findUserbmByUserLogin(String userLogin);
    @Query("""
SELECT user FROM Userbm user WHERE user.userName = :userName AND user.userSurname = :userSurname 
                                AND (user.userDob BETWEEN :userDob AND :userDob)
    """)
    Optional<Userbm> findUserbmByFullname(@Param("userName") String userName, @Param("userSurname") String userSurname,
                                      @Param("userDob") LocalDate userDob);

    @Query("""
SELECT user FROM Userbm user WHERE user.userName = :userName AND user.userSurname = :userSurname
    """)
    Optional<List<Userbm>> findUserbmByFullname(@Param("userName") String userName, @Param("userSurname") String userSurname);

    @Query("""
SELECT user FROM Userbm user WHERE user.userName = :userName
    """)
    Optional<List<Userbm>> findUserbmByFullname(@Param("userName") String userName);

    @Query("""
SELECT user FROM Userbm user WHERE user.userName = :userName  AND (user.userDob BETWEEN :userDob AND :userDob)
    """)
    Optional<List<Userbm>> findUserbmByFullname(@Param("userName") String userName, @Param("userDob") LocalDate userDob);

    @Query("""
SELECT user FROM Userbm user WHERE user.userAddress.email = :email
""")
    Optional<Userbm> findUserbmByEmail(@Param("email") String email);
}
