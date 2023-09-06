package com.c2psi.bmv1.client.client.mappers;

import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.dto.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(source = "clientPos.id", target = "clientPosId")
    ClientDto entityToDto(Client client);
    List<ClientDto> entityToDto(List<Client> client);
    Client dtoToEntity(ClientDto clientDto);
    List<Client> dtoToEntity(List<ClientDto> clientDto);
}
