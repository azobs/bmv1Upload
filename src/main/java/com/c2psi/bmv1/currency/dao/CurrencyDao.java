package com.c2psi.bmv1.currency.dao;

import com.c2psi.bmv1.currency.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyDao extends JpaRepository<Currency, Long>, JpaSpecificationExecutor<Currency> {
    Optional<Currency> findCurrencyById(Long id);

    @Query("""
SELECT cur FROM Currency cur WHERE cur.currencyName = :currencyName AND cur.currencyAbbreviation = :currencyAbbreviation
""")
    Optional<Currency> findCurrencyByFullname(@Param("currencyName") String currencyName,
                                              @Param("currencyAbbreviation") String currencyAbbreviation);
}
