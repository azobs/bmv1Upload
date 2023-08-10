package com.c2psi.bmv1.address.service;

import com.c2psi.bmv1.global.exceptions.DuplicateEntityException;
import com.c2psi.bmv1.global.exceptions.InvalidEntityException;
import com.c2psi.bmv1.address.dao.AddressDao;
import com.c2psi.bmv1.address.exceptions.AddressErrorCode;
import com.c2psi.bmv1.address.mapper.AddressMapper;
import com.c2psi.bmv1.address.models.Address;
import com.c2psi.bmv1.address.validators.AddressValidator;
import com.c2psi.bmv1.dto.AddressDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service(value = "AddressServiceV1")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{
    final AddressDao addressDao;
    final AddressMapper addressMapper;

    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        /***********************
         * On valide les parametres saisis et envoyes depuis l'interface client
         */
        List<String> errors = AddressValidator.validate(addressMapper.dtoToEntity(addressDto));
        if(!errors.isEmpty()){
            log.error("Entity Address not valid because of {}", errors);
            throw new InvalidEntityException("L'addresse a enregistrer n'est pas valide ", errors,
                    AddressErrorCode.ADDRESS_NOT_VALID.name());
        }

        /*******************************************************************
         * On se rassure que si l'email n'est pas null alors il est unique
         */
        if(addressDto.getEmail() != null){
            if(!isEmailUsed(addressDto.getEmail())){
                log.error("The email sent is already used in the DB");
                throw new DuplicateEntityException("L'email envoye dans l'adresse est deja utilise ",
                        AddressErrorCode.ADDRESS_DUPLICATED.name());
            }
        }

        log.info("After all semantic verification, the address {} can be saved safely", addressDto);

        Address addressSaved = addressDao.save(addressMapper.dtoToEntity(addressDto));

        return addressMapper.entityToDto(addressSaved);
    }

    public boolean isEmailUsed(String email){
        /********************************
         * If the there is no Address in the DB with the email pass as argument, Then
         * the below optionalAddress will not contain Address. Mean optionalAddress.isPresent
         * will return false. If it return false, then if(!isEmailUnique) will be true.
         */
        Optional<Address> optionalAddress = addressDao.findAddressByEmail(email);
        return optionalAddress.isEmpty();
    }

    @Override
    public AddressDto updateAddress(AddressDto addressDto) {
        return null;
    }

    @Override
    public Boolean deleteAddressById(Long id) {
        return null;
    }

    @Override
    public AddressDto getAddressById(Long id) {
        return null;
    }

    @Override
    public Boolean isEmailAddressUnique(String email) {
        return this.isEmailUsed(email);
    }
}
