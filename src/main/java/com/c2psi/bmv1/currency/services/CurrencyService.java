package com.c2psi.bmv1.currency.services;

import com.c2psi.bmv1.dto.*;

import java.util.List;

public interface CurrencyService {
    CurrencyDto saveCurrency(CurrencyDto currencyDto);
    CurrencyDto updateCurrency(CurrencyDto currencyDto);
    Boolean deleteCurrencyById(Long id);
    CurrencyDto getCurrencyById(Long id);
    List<CurrencyDto> getListofCurrency(FilterRequest filterRequest);
    PageofCurrencyDto getPageofCurrency(FilterRequest filterRequest);
}
