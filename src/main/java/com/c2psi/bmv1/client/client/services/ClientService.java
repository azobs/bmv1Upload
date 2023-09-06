package com.c2psi.bmv1.client.client.services;

import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofClientDto;
import com.c2psi.bmv1.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto saveClient(ClientDto providerDto);
    ClientDto updateClient(ClientDto providerDto);
    Boolean deleteClientById(Long id);
    ClientDto getClientById(Long id);
    List<ClientDto> getListofClient(FilterRequest filterRequest);
    PageofClientDto getPageofClient(FilterRequest filterRequest);
    Boolean isClientExistWith(Long id);
}
