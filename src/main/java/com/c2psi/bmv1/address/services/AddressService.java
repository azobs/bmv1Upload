package com.c2psi.bmv1.address.services;

import com.c2psi.bmv1.dto.AddressDto;


public interface AddressService {
    AddressDto saveAddress(AddressDto addressDto);
    AddressDto updateAddress(AddressDto addressDto);
    Boolean deleteAddressById(Long id);
    AddressDto getAddressById(Long id);
    Boolean isEmailAddressUnique(String email);
}
