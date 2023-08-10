package com.c2psi.bmv1.userbm.service;

import com.c2psi.bmv1.address.service.AddressService;
import com.c2psi.bmv1.dto.AddressDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofUserbmDto;
import com.c2psi.bmv1.dto.UserbmDto;
import com.c2psi.bmv1.global.exceptions.DuplicateEntityException;
import com.c2psi.bmv1.global.exceptions.InvalidEntityException;
import com.c2psi.bmv1.userbm.dao.UserbmDao;
import com.c2psi.bmv1.userbm.enumerations.UserbmErrorCode;
import com.c2psi.bmv1.userbm.mapper.UserbmMapper;
import com.c2psi.bmv1.userbm.models.Userbm;
import com.c2psi.bmv1.userbm.validators.UserbmValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service(value = "UserbmServiceV1")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserbmServiceImpl implements UserbmService{
    final UserbmDao userbmDao;
    final UserbmMapper userbmMapper;
    final AddressService addressService;

    @Override
    public UserbmDto saveUserbm(UserbmDto userbmDto) {
        /**************************************************************************
         * On valide les parametres saisis et envoyes depuis l'interface client
         **************************************************************************/
        List<String> errors = UserbmValidator.validate(userbmMapper.dtoToEntity(userbmDto));
        if(!errors.isEmpty()){
            log.error("Entity Userbm not valid because of {}", errors);
            throw new InvalidEntityException("Le userbm a enregistrer n'est pas valide ", errors,
                    UserbmErrorCode.USERBM_NOT_VALID.name());
        }

        /***********************************************************************
         * Il faut se rassurer de l'unicite du login ou alors lever
         * une exception sinon
         * NB: Dans la validation on est sur que le login ne peut etre null
         ***********************************************************************/
        if(userbmDto.getUserLogin() != null){
            if(!isLoginUsed(userbmDto.getUserLogin())){
                log.error("The login sent is already used in the DB");
                throw new DuplicateEntityException("Le login envoye est deja utilise",
                        UserbmErrorCode.USERBM_DUPLICATED.name());
            }
        }

        if(userbmDto.getUserCni() != null){
            if(!isCniUsed(userbmDto.getUserCni())){
                log.error("The cni number sent is already used in the DB");
                throw new DuplicateEntityException("Le cni number envoye est deja utilise",
                        UserbmErrorCode.USERBM_DUPLICATED.name());
            }
        }

        if(userbmDto.getUserAddress() != null){
            if(userbmDto.getUserAddress().getEmail() != null){
                if(!isAddressUsed(userbmDto.getUserAddress().getEmail())){
                    log.error("The userbm email sent is already used in the DB");
                    throw new DuplicateEntityException("Le userbm email envoye est deja utilise",
                            UserbmErrorCode.USERBM_DUPLICATED.name());
                }
            }
        }

        if(userbmDto.getUserName() != null ){
            if(!isFullnameUsed(userbmDto.getUserName(), userbmDto.getUserSurname(), userbmDto.getUserDob())){
                log.error("The userbm fullname sent is already used in the DB");
                throw new DuplicateEntityException("Le userbm fullname envoye est deja utilise",
                        UserbmErrorCode.USERBM_DUPLICATED.name());
            }
        }

        AddressDto addressDtoSaved = addressService.saveAddress(userbmDto.getUserAddress());

        userbmDto.setUserAddress(addressDtoSaved);

        log.info("After all verification, the Userbm {} sent can be safely saved in the DB ", userbmDto);

        Userbm userbmSaved = userbmDao.save(userbmMapper.dtoToEntity(userbmDto));

        return userbmMapper.entityToDto(userbmSaved);
    }

    public boolean isLoginUsed(String login){
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmByUserLogin(login);
        return optionalUserbm.isEmpty();
    }

    public boolean isCniUsed(String cni){
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmByUserCni(cni);
        return optionalUserbm.isEmpty();
    }

    public boolean isFullnameUsed(String userName, String userSurname, LocalDate userDob){
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmByFullname(userName, userSurname, userDob);
        return optionalUserbm.isEmpty();
    }

    public boolean isAddressUsed(String email){
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmByEmail(email);
        return optionalUserbm.isEmpty();
    }

    @Override
    public UserbmDto updateUserbm(UserbmDto userbmDto) {
        return null;
    }

    @Override
    public Boolean deleteUserbmById(Long id) {
        return null;
    }

    @Override
    public UserbmDto getUserbmById(Long id) {
        return null;
    }

    @Override
    public UserbmDto getUserbmByCni(String userCni) {
        return null;
    }

    @Override
    public UserbmDto getUserbmByLogin(String userLogin) {
        return null;
    }

    @Override
    public UserbmDto getUserbmByEmail(String userEmail) {
        return null;
    }

    @Override
    public List<UserbmDto> getUserbmList(FilterRequest filterRequest) {
        return null;
    }

    @Override
    public PageofUserbmDto getUserbmPage(FilterRequest filterRequest) {
        return null;
    }
}
