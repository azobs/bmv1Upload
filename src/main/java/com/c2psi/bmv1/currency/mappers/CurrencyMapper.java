package com.c2psi.bmv1.currency.mappers;

import com.c2psi.bmv1.currency.models.Currency;
import com.c2psi.bmv1.dto.CurrencyDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurrencyMapper {
    CurrencyDto entityToDto(Currency currency);
    Currency dtoToEntity(CurrencyDto currencyDto);
    List<CurrencyDto> entityToDto(List<Currency> currency);
    List<Currency> dtoToEntity(List<CurrencyDto> currencyDto);
}
