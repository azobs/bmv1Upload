package com.c2psi.bmv1.client.clientspecialprice.services;

import com.c2psi.bmv1.dto.ClientSpecialpriceDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofClientSpecialpriceDto;

import java.util.List;

public interface ClientSpecialpriceService {
    ClientSpecialpriceDto saveClientSpecialprice(ClientSpecialpriceDto clientSpecialpriceDto);
    ClientSpecialpriceDto updateClientSpecialprice(ClientSpecialpriceDto clientSpecialpriceDto);
    Boolean deleteClientSpecialpriceById(Long id);
    ClientSpecialpriceDto getClientSpecialpriceById(Long id);
    List<ClientSpecialpriceDto> getListofClientSpecialprice(FilterRequest filterRequest);
    PageofClientSpecialpriceDto getPageofClientSpecialprice(FilterRequest filterRequest);
    Boolean isClientSpecialpriceExistWith(Long id);
}
