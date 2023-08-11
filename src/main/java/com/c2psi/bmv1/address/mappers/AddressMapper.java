package com.c2psi.bmv1.address.mappers;

import com.c2psi.bmv1.address.models.Address;
import com.c2psi.bmv1.dto.AddressDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto entityToDto(Address address);
    Address dtoToEntity(AddressDto addressDto);
    List<AddressDto> entityToDto(List<Address> address);
    List<Address> dtoToEntity(List<AddressDto> addressDto);

}
