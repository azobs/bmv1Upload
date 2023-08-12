package com.c2psi.bmv1.pos.pos.service;

import com.c2psi.bmv1.address.models.Address;
import com.c2psi.bmv1.address.services.AddressService;
import com.c2psi.bmv1.bmapp.exceptions.DuplicateEntityException;
import com.c2psi.bmv1.bmapp.exceptions.InvalidEntityException;
import com.c2psi.bmv1.bmapp.exceptions.ModelNotFoundException;
import com.c2psi.bmv1.bmapp.exceptions.NullValueException;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.currency.services.CurrencyService;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofPointofsaleDto;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.pos.enterprise.services.EnterpriseService;
import com.c2psi.bmv1.pos.pos.dao.PointofsaleDao;
import com.c2psi.bmv1.pos.pos.errorCode.ErrorCode;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service(value = "PointofsaleServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PointofsaleServiceImpl implements PointofsaleService{
    final AppService appService;
    final PointofsaleDao posDao;
    final PointofsaleMapper posMapper;
    final PointofsaleValidator posValidator;
    final PointofsaleSpecService posSpecService;
    final AddressService addressService;
    final CurrencyService currencyService;
    final EnterpriseService enterpriseService;



    @Override
    public PointofsaleDto savePointofsale(PointofsaleDto posDto) {
        /*******************************************************************************
         * On se rassure que le parametre n'est pas null sinon on leve une exception
         */
        if(posDto == null){
            throw new NullValueException("Le parametre posDto ne peut etre null");
        }

        /***********************************************************************************
         * On valide le parametre recuperer et si il n'est pas valide on leve une exception
         */
        List<String> errors = posValidator.validate(posMapper.dtoToEntity(posDto));

        if(!errors.isEmpty()){
            log.error("Validation problem with the entity pos to save {}", posDto);
            throw new InvalidEntityException("Le pointofsale envoye pour enregistrement n'est pas valide ", errors,
                    ErrorCode.POINTOFSALE_NOT_VALID.name());
        }

        /************************************************************************************
         * On se rassure que les contraintes d'unicites ne seront pas viole apres insertion
         * et si c'est le cas on leve une exception
         */
        if(!isPosEmailUsable(posDto.getPosAddress().getEmail())){
            log.error("The email {} is already used", posDto.getPosAddress().getEmail());
            throw new DuplicateEntityException("L'adresse email envoye pour le Pointofsale est deja utilise ",
                    ErrorCode.POINTOFSALE_DUPLICATED.name());
        }

        if(!isPosFullnameUsable(posDto.getPosName(), posDto.getPosAcronym())){
            log.error("The fullname of pos {} {} is already used", posDto.getPosName(), posDto.getPosAcronym());
            throw new DuplicateEntityException("Le nom et l'acronym du Pointofsale envoye sont deja utilises ",
                    ErrorCode.POINTOFSALE_DUPLICATED.name());
        }

        /***********************************************************************************
         *On se rassure que l'id precise pour le currency par defaut identifie bel et bien
         * un currency en BD
         * The method isCurrencyExistWith return true if the currency exist. then the negation
         * of true is false and then the execption can't be throw
         */
        if(!currencyService.isCurrencyExistWith(posDto.getPosCurrency().getId())){
            throw new ModelNotFoundException("Le currency indique pour le pointofsale n'existe pas en BD: Verifier " +
                    "l'id indique. Le pointofsale n'est donc pas valide ", ErrorCode.POINTOFSALE_NOT_VALID.name());
        }


        /***********************************************************************************
         *On se rassure que l'id precise pour l'entreprise  identifie bel et bien
         * une entreprise en BD
         */
        if(!enterpriseService.isEnterpriseExistWith(posDto.getPosEnterprise().getId())){
            throw new ModelNotFoundException("L'entreprise indique pour le pointofsale n'existe pas en BD: Verifier " +
                    "l'id indique. Le pointofsale n'est donc pas valide ", ErrorCode.POINTOFSALE_NOT_VALID.name());
        }

        /**************************************************************************************
         * Si tout est bon on effectue l'enregistrement car avec les bon Id des entites lies
         * JPA pourra s'en sortir sans exceptions generees
         */
        log.info("After all verification, the Pointofsale can be safely saved in the DB");
        Pointofsale pointofsaleSaved = posDao.save(posMapper.dtoToEntity(posDto));

        return posMapper.entityToDto(pointofsaleSaved);
    }

    public Boolean isPosEmailUsable(String posEmail){
        return addressService.isEmailAddressUnique(posEmail);
    }

    public Boolean isPosFullnameUsable(String posName, String posAcronym){
        Optional<Pointofsale> optionalPointofsale = posDao.findPointofsaleByFullname(posName, posAcronym);
        return optionalPointofsale.isEmpty();
    }

    @Override
    public PointofsaleDto updatePointofsale(PointofsaleDto posDto) {
        return null;
    }

    @Override
    public Boolean deletePointofsaleById(Long id) {
        return null;
    }

    @Override
    public PointofsaleDto getPointofsaleById(Long id) {
        return null;
    }

    @Override
    public List<PointofsaleDto> getListofPointofsale(FilterRequest filterRequest) {
        return null;
    }

    @Override
    public PageofPointofsaleDto getPageofPointofsale(FilterRequest filterRequest) {
        return null;
    }
}
