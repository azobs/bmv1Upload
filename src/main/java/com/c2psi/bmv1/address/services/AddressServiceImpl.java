package com.c2psi.bmv1.address.services;

import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.address.dao.AddressDao;
import com.c2psi.bmv1.address.errorCode.ErrorCode;
import com.c2psi.bmv1.address.mappers.AddressMapper;
import com.c2psi.bmv1.address.models.Address;
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
    final AddressValidator addressValidator;

    @Override
    public AddressDto saveAddress(AddressDto addressDto) {
        /***********************
         * On valide les parametres saisis et envoyes depuis l'interface client
         */
        List<String> errors = addressValidator.validate(addressMapper.dtoToEntity(addressDto));
        if(!errors.isEmpty()){
            log.error("Entity Address not valid because of {}", errors);
            throw new InvalidEntityException("L'addresse a enregistrer n'est pas valide ", errors,
                    ErrorCode.ADDRESS_NOT_VALID.name());
        }

        /*******************************************************************
         * On se rassure que si l'email n'est pas null alors il est unique
         */
        if(addressDto.getEmail() != null){
            if(!isEmailUsed(addressDto.getEmail())){
                log.error("The email sent is already used in the DB");
                throw new DuplicateEntityException("L'email envoye dans l'adresse est deja utilise ",
                        ErrorCode.ADDRESS_DUPLICATED.name());
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
        /****************************************************
         * On se rassure que le parametre n'est pas null
         */
        if(addressDto == null){
            throw new NullValueException("L'argument address a update ne peut etre null");
        }

        /**************************************************************
         * On se rassure que l'id de l'adresse a update n'est pas null
         */
        if(addressDto.getId() == null){
            throw new NullValueException("L'id de l'adresse a update ne peut etre null");
        }

        /***********************************************************
         * On valide l'adresse grace au validateur
         */
        List<String> errors = addressValidator.validate(addressMapper.dtoToEntity(addressDto));
        if(!errors.isEmpty()){
            log.error("Entity Address not valid because of {}", errors);
            throw new InvalidEntityException("L'addresse a enregistrer n'est pas valide ", errors,
                    ErrorCode.ADDRESS_NOT_VALID.name());
        }

        /****************************************************
         * On fait la recherche en BD de l'adresse a update
         */
        Optional<Address> optionalAddress = addressDao.findAddressById(addressDto.getId());
        if(!optionalAddress.isPresent()){
            throw new ModelNotFoundException("Aucune address n'existe avec l'id envoye ", ErrorCode.ADDRES_NOT_FOUND.name());
        }
        Address addressToUpdate = optionalAddress.get();
        /******************************************************
         * On se rassure que si c'est l'email de l'adresse qui
         * veut etre update alors la contrainte d'unicite ne
         * sera pas viole
         */
        if(addressToUpdate.getEmail() != null && addressDto.getEmail() != null) {
            if (!addressToUpdate.getEmail().equalsIgnoreCase(addressDto.getEmail())) {
                if (!isEmailUsed(addressDto.getEmail())) {
                    log.error("The email sent is already used in the DB");
                    throw new DuplicateEntityException("L'email envoye dans l'adresse est deja utilise ",
                            ErrorCode.ADDRESS_DUPLICATED.name());
                }
                addressToUpdate.setEmail(addressDto.getEmail());
            }
        }

        /********************************************************
         * On effectue le reste des mises jours sans souci
         */
        addressToUpdate.setNumtel1(addressDto.getNumtel1());
        addressToUpdate.setNumtel2(addressDto.getNumtel2());
        addressToUpdate.setNumtel3(addressDto.getNumtel3());
        addressToUpdate.setQuarter(addressDto.getQuarter());
        addressToUpdate.setCountry(addressDto.getCountry());
        addressToUpdate.setTown(addressDto.getTown());
        addressToUpdate.setLocalisation(addressDto.getLocalisation());

        Address addressUpdated = addressDao.save(addressToUpdate);

        return addressMapper.entityToDto(addressUpdated);
    }

    @Override
    public Boolean deleteAddressById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'adresse a supprimer ne saurait etre null");
        }

        Optional<Address> optionalAddress = addressDao.findAddressById(id);
        if(!optionalAddress.isPresent()){
            throw new ModelNotFoundException("L'id de l'adresse a supprimer ne correspond a aucune addresse en BD ",
                    ErrorCode.ADDRES_NOT_FOUND.name());
        }
        Address addressToDelete = optionalAddress.get();

        if(!isAddressDeleteable(addressToDelete)){
            throw new EntityNotDeleatableException("L'adresse indique ne peut etre supprime sans cause de conflit",
                    ErrorCode.ADDRESS_NOT_DELETEABLE.name());
        }
        log.info("After all verification the address can be safely deleted");

        addressDao.deleteById(addressToDelete.getId());
        return true;
    }

    @Override
    public AddressDto getAddressById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'adresse rechercher ne saurait etre null");
        }
        Optional<Address> optionalAddress = addressDao.findAddressById(id);
        if(!optionalAddress.isPresent()){
            throw new ModelNotFoundException("Aucune Address n'existe avec l'id passe en parametre ",
                    ErrorCode.ADDRES_NOT_FOUND.name());
        }

        return addressMapper.entityToDto(optionalAddress.get());
    }

    @Override
    public Boolean isEmailAddressUnique(String email) {
        return this.isEmailUsed(email);
    }

    Boolean isAddressDeleteable(Address address){
        return true;
    }
}
