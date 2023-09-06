package com.c2psi.bmv1.userbm.services;

import com.c2psi.bmv1.address.models.Address;
import com.c2psi.bmv1.address.services.AddressService;
import com.c2psi.bmv1.auth.config.JwtService;
import com.c2psi.bmv1.auth.models.ExtendedUser;
import com.c2psi.bmv1.auth.services.LoadUserbmService;
import com.c2psi.bmv1.auth.token.dao.TokenDao;
import com.c2psi.bmv1.auth.token.models.Token;
import com.c2psi.bmv1.auth.token.services.TokenService;
import com.c2psi.bmv1.bmapp.enumerations.TokenType;
import com.c2psi.bmv1.bmapp.enumerations.UserStateEnum;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.userbm.errorCode.ErrorCode;
import com.c2psi.bmv1.userbm.mappers.UserbmMapper;
import com.c2psi.bmv1.userbm.dao.UserbmDao;
import com.c2psi.bmv1.userbm.models.Userbm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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
    final AppService appService;
    final UserbmSpecService userbmSpecService;
    final UserbmValidator userbmValidator;

    @Override
    public UserbmDto saveUserbm(UserbmDto userbmDto) {
        if(userbmDto == null){
            throw new NullValueException("Le User envoye est null");
        }
        /**************************************************************************
         * On valide les parametres saisis et envoyes depuis l'interface client
         **************************************************************************/
        List<String> errors = userbmValidator.validate(userbmMapper.dtoToEntity(userbmDto));
        if(!errors.isEmpty()){
            log.error("Entity Userbm not valid because of {}", errors);
            throw new InvalidEntityException("Le userbm a enregistrer n'est pas valide ", errors,
                    ErrorCode.USERBM_NOT_VALID.name());
        }

        /***********************************************************************
         * Il faut se rassurer de l'unicite du login ou alors lever
         * une exception sinon
         * NB: Dans la validation on est sur que le login ne peut etre null
         ***********************************************************************/
        if(userbmDto.getUserLogin() != null){
            if(!isLoginUsable(userbmDto.getUserLogin())){
                log.error("The login sent is already used in the DB");
                throw new DuplicateEntityException("Le login envoye est deja utilise",
                        ErrorCode.USERBM_DUPLICATED.name());
            }
        }

        if(userbmDto.getUserCni() != null){
            if(!isCniUsable(userbmDto.getUserCni())){
                log.error("The cni number sent is already used in the DB");
                throw new DuplicateEntityException("Le cni number envoye est deja utilise",
                        ErrorCode.USERBM_DUPLICATED.name());
            }
        }

        if(userbmDto.getUserAddress() != null){
            if(userbmDto.getUserAddress().getEmail() != null){
                if(!isAddressUsable(userbmDto.getUserAddress().getEmail())){
                    log.error("The userbm email sent is already used in the DB");
                    throw new DuplicateEntityException("Le userbm email envoye est deja utilise",
                            ErrorCode.USERBM_DUPLICATED.name());
                }
            }
        }

        if(userbmDto.getUserName() != null ){
            if(!isFullnameUsable(userbmDto.getUserName(), userbmDto.getUserSurname(), userbmDto.getUserDob())){
                log.error("The userbm fullname sent is already used in the DB");
                throw new DuplicateEntityException("Le userbm fullname envoye est deja utilise",
                        ErrorCode.USERBM_DUPLICATED.name());
            }
        }

        if(userbmDto.getUserPassword() == null || userbmDto.getUserRepassword() == null){
            log.error("Password and Repassword can't be null and must equal");
            throw new NullValueException("Password or Repassword is null please check");
        }

        if(userbmDto.getUserPassword() != null && userbmDto.getUserRepassword() != null){
            if(!isPasswordConfirm(userbmDto.getUserPassword(), userbmDto.getUserRepassword())){
                throw new PasswordNotConfirmedException("Password is not well confirmed");
            }
        }

        /*********************************************************************************
         * After including security, the password confirm must be encrypted before saved
         * by using the PasswordEncoder of SpringSecurity
         ********************************************************************************/
        String passwordToEncode = userbmDto.getUserPassword();
        userbmDto.setUserPassword(new BCryptPasswordEncoder().encode(passwordToEncode));

        log.info("After all verification, the Userbm sent can be safely saved in the DB ");

        /**
         * userbmToUpdate.setUserCni(userbmDto.getUserCni());
         * userbmToUpdate.setUserLogin(userbmDto.getUserLogin());
         * userbmToUpdate.setUserName(userbmDto.getUserName());
         * userbmToUpdate.setUserSurname(userbmDto.getUserSurname());
         *  userbmToUpdate.setUserDob(userbmDto.getUserDob());
         *  userbmToUpdate.setUserState(convert(userbmDto.getUserState()));
         */
        Userbm userbmToSave = Userbm.builder()
                .userAddress(Address.builder()
                        .numtel1(userbmDto.getUserAddress().getNumtel1())
                        .numtel2(userbmDto.getUserAddress().getNumtel2())
                        .numtel3(userbmDto.getUserAddress().getNumtel3())
                        .quarter(userbmDto.getUserAddress().getQuarter())
                        .country(userbmDto.getUserAddress().getCountry())
                        .town(userbmDto.getUserAddress().getTown())
                        .localisation(userbmDto.getUserAddress().getLocalisation())
                        .email(userbmDto.getUserAddress().getEmail())
                        .build())
                .userCni(userbmDto.getUserCni())
                .userLogin(userbmDto.getUserLogin())
                .userName(userbmDto.getUserName())
                .userSurname(userbmDto.getUserSurname())
                .userDob(userbmDto.getUserDob())
                .userState(convert(userbmDto.getUserState()))
                .userPassword(userbmDto.getUserPassword())
                .build();


        Userbm userbmSaved = userbmDao.save(userbmToSave);
        ///////////////////////////////////////////////////

        //TokenDto tokenDto =

        ///////////////////////////////////////////////////

        return userbmMapper.entityToDto(userbmSaved);
    }

    public boolean isPasswordConfirm(String password, String rePassword){
        return password.equals(rePassword);
    }

    public boolean isLoginUsable(String login){
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmByUserLogin(login);
        return optionalUserbm.isEmpty();
    }

    public boolean isCniUsable(String cni){
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmByUserCni(cni);
        return optionalUserbm.isEmpty();
    }

    public boolean isFullnameUsable(String userName, String userSurname, LocalDate userDob){
        if(userName != null){
            if(userSurname != null && userDob != null){
                Optional<Userbm>  optionalUserbm = userbmDao.findUserbmByFullname(userName, userSurname, userDob);
                return optionalUserbm.isEmpty();
            } else if (userDob != null) {
                Optional<List<Userbm>> optionalUserbmList = userbmDao.findUserbmByFullname(userName, userDob);
                if(optionalUserbmList.isPresent()){
                    return optionalUserbmList.get().size() < 1;
                }
            }
            else {
                Optional<List<Userbm>> optionalUserbmList = userbmDao.findUserbmByFullname(userName);
                if(optionalUserbmList.isPresent()){
                    return optionalUserbmList.get().size() < 1;
                }
            }
        }
       return true;
    }

    public boolean isAddressUsable(String email){
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmByEmail(email);
        return optionalUserbm.isEmpty();
    }

    @Override
    public UserbmDto updateUserbm(UserbmDto userbmDto) {
        /***********************************************
         * On se rassure que le parametre n'est pas null
         */
        if(userbmDto == null){
            throw new NullValueException("Le User envoye est null");
        }

        /*****************************************************
         * On se rassure que l'id du parametre n'est pas null
         */
        if(userbmDto.getId() == null){
            throw new NullValueException("L'id du Userbm a modifier ne peut etre null");
        }

        /*******************************************************
         * On valide le parametre grace au validateur
         */
        List<String> errors = userbmValidator.validate(userbmMapper.dtoToEntity(userbmDto));
        if(!errors.isEmpty()){
            log.error("Entity Userbm not valid because of {}", errors);
            throw new InvalidEntityException("Le userbm a enregistrer n'est pas valide ", errors,
                    ErrorCode.USERBM_NOT_VALID.name());
        }

        /****************************************************
         * On recupere le Userbm a update grace au id
         */
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmById(userbmDto.getId());
        if(!optionalUserbm.isPresent()) {
            throw new ModelNotFoundException("L'id envoye n'identifie aucun userbm dans la BD",
                    ErrorCode.USERBM_NOT_FOUND.name());
        }
        Userbm userbmToUpdate = optionalUserbm.get();

        /*********************************************************
         * Si c'est le cni qu'on veut modifier alors on se rassure
         * que l'unicite ne sera pas viole
         */
        if(userbmDto.getUserCni() != null){
            if(userbmToUpdate.getUserCni() != null){
                if(!userbmDto.getUserCni().equalsIgnoreCase(userbmToUpdate.getUserCni())) {
                    if (!isCniUsable(userbmDto.getUserCni())) {
                        throw new DuplicateEntityException("Le nouveau cni number envoye existe deja en BD",
                                ErrorCode.USERBM_DUPLICATED.name());
                    }
                    userbmToUpdate.setUserCni(userbmDto.getUserCni());
                }
            }
            //userbmToUpdate.setUserCni(userbmDto.getUserCni());
        }

        /*******************************************************************
         * Si c'est le login qu'on veut modifier alors on se rassure que
         * l'unicite ne sera pas viole
         */

        if(userbmDto.getUserLogin() != null){
            if(userbmToUpdate.getUserLogin() != null){
                if(!userbmDto.getUserLogin().equalsIgnoreCase(userbmToUpdate.getUserLogin())){
                    if(!isLoginUsable(userbmDto.getUserLogin())){
                        throw new DuplicateEntityException("Un userbm existe deja avec le meme login ",
                                ErrorCode.USERBM_DUPLICATED.name());
                    }
                    userbmToUpdate.setUserLogin(userbmDto.getUserLogin());
                }
            }
        }

        /****************************************************************************
         * Si c'est le nom ou le prenom ou alors la date de naissance
         * qu'on veut modifier alors on se rassure que l'unicite ne sera pas viole
         */

        if(userbmDto.getUserName() != null){
            if(userbmToUpdate.getUserName() != null){
                if(!userbmDto.getUserName().equalsIgnoreCase(userbmToUpdate.getUserName())){
                    if(!isFullnameUsable(userbmDto.getUserName(), userbmDto.getUserSurname(), userbmDto.getUserDob())){
                        throw new DuplicateEntityException("Un userbm existe deja avec le meme nom, le meme prenom et la " +
                                "meme date de naissance ", ErrorCode.USERBM_DUPLICATED.name());
                    }
                    userbmToUpdate.setUserName(userbmDto.getUserName());
                }
            }
        }

        if(userbmDto.getUserSurname() != null){
            if(userbmToUpdate.getUserSurname() != null){
                if(!userbmDto.getUserSurname().equalsIgnoreCase(userbmToUpdate.getUserSurname())){
                    if(!isFullnameUsable(userbmDto.getUserName(), userbmDto.getUserSurname(), userbmDto.getUserDob())){
                        throw new DuplicateEntityException("Un userbm existe deja avec le meme nom, le meme prenom et la " +
                                "meme date de naissance ", ErrorCode.USERBM_DUPLICATED.name());
                    }
                    userbmToUpdate.setUserSurname(userbmDto.getUserSurname());
                }
            }
            else{
                if(!isFullnameUsable(userbmDto.getUserName(), userbmDto.getUserSurname(), userbmDto.getUserDob())){
                    throw new DuplicateEntityException("Un userbm existe deja avec le meme nom, le meme prenom et la " +
                            "meme date de naissance ", ErrorCode.USERBM_DUPLICATED.name());
                }
                userbmToUpdate.setUserSurname(userbmDto.getUserSurname());
            }
        }

        if(userbmDto.getUserDob() != null){
            if(userbmToUpdate.getUserDob() != null){
                if(!userbmDto.getUserDob().isEqual(userbmToUpdate.getUserDob())){
                    if(!isFullnameUsable(userbmDto.getUserName(), userbmDto.getUserSurname(), userbmDto.getUserDob())){
                        throw new DuplicateEntityException("Un userbm existe deja avec le meme nom, le meme prenom et la " +
                                "meme date de naissance ", ErrorCode.USERBM_DUPLICATED.name());
                    }
                    userbmToUpdate.setUserDob(userbmDto.getUserDob());
                }
            }
            else{
                if(!isFullnameUsable(userbmDto.getUserName(), userbmDto.getUserSurname(), userbmDto.getUserDob())){
                    throw new DuplicateEntityException("Un userbm existe deja avec le meme nom, le meme prenom et la " +
                            "meme date de naissance ", ErrorCode.USERBM_DUPLICATED.name());
                }
                userbmToUpdate.setUserDob(userbmDto.getUserDob());
            }
        }


        /******************************************************************
         * On met a jour l'adresse qui va lancer une exception au cas ou
         */

        addressService.updateAddress(userbmDto.getUserAddress());

        /**********************************************************
         * Si tout est bon on effectue le reste des modifications
         */
        userbmToUpdate.setUserState(convert(userbmDto.getUserState()));

        Userbm userbmUpdated = userbmDao.save(userbmToUpdate);

        log.info("After all verification The userbm can be updated");
        return userbmMapper.entityToDto(userbmUpdated);
    }

    @Override
    public UserbmDto updateUserbmState(Long userbmId, UserbmDto.UserStateEnum userStateEnum) {
        if(userbmId == null || userStateEnum == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmById(userbmId);
        if(!optionalUserbm.isPresent()){
            throw new ModelNotFoundException("Aucun Userbm n'existe avec l'id envoye", ErrorCode.USERBM_NOT_FOUND.name());
        }
        Userbm userbmToUpdate = optionalUserbm.get();
        userbmToUpdate.setUserState(convert(userStateEnum));

        return userbmMapper.entityToDto(userbmDao.save(userbmToUpdate));
    }

    UserStateEnum convert(UserbmDto.UserStateEnum userStateEnum){
        switch (userStateEnum){
            case ACTIVATED -> {
                return UserStateEnum.Activated;
            }
            case CONNECTED -> {
                return UserStateEnum.Connected;
            }
            case DEACTIVATED -> {
                return UserStateEnum.Deactivated;
            }
            case DISCONNECTED -> {
                return UserStateEnum.Disconnected;
            }
        }
        throw new EnumNonConvertibleException("La valeur envoye ne peut etre convertit en UserStateEnum");
    }

    UserbmDto.UserStateEnum convert(UserStateEnum userStateEnum){
        switch (userStateEnum){
            case Activated -> {
                return UserbmDto.UserStateEnum.ACTIVATED;
            }
            case Connected -> {
                return UserbmDto.UserStateEnum.CONNECTED;
            }
            case Deactivated -> {
                return UserbmDto.UserStateEnum.DEACTIVATED;
            }
            case Disconnected -> {
                return UserbmDto.UserStateEnum.DISCONNECTED;
            }
        }
        throw new EnumNonConvertibleException("La valeur envoye ne peut etre convertit en UserbmDto.UserStateEnum");
    }

    @Override
    public Boolean deleteUserbmById(Long id) {
        if(id == null){
            throw new NullValueException("L'identifiant de l'utilisateur a supprimer ne peut etre null");
        }
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmById(id);
        if(!optionalUserbm.isPresent()){
            throw new ModelNotFoundException("Aucun Userbm n'existe en BD avec l'id envoye ",
                    ErrorCode.USERBM_NOT_FOUND.name());
        }
        if(!isUserbmDeleteable(optionalUserbm.get())){
            throw new EntityNotDeleatableException("La suppression du Userbm va causer beaucoup de conflit ",
                    ErrorCode.USERBM_NOT_DELETEABLE.name());
        }
        userbmDao.delete(optionalUserbm.get());
        return true;
    }

    private Boolean isUserbmDeleteable(Userbm userbm){
        return true;
    }

    @Override
    public UserbmDto getUserbmById(Long id) {
        if(id == null){
            throw new NullValueException("L'id envoye ne peut etre null");
        }
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmById(id);
        if(!optionalUserbm.isPresent()){
            throw new ModelNotFoundException("Il n'existe pas de Userbm en BD avec l'id envoye",
                    ErrorCode.USERBM_NOT_FOUND.name());
        }
        return userbmMapper.entityToDto(optionalUserbm.get());
    }

    @Override
    public UserbmDto getUserbmByCni(String userCni) {
        if(userCni == null){
            throw new NullValueException("Le usercni envoye ne peut etre null");
        }
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmByUserCni(userCni);
        if(!optionalUserbm.isPresent()){
            throw new ModelNotFoundException("Il n'existe pas de Userbm en BD avec le cni number envoye",
                    ErrorCode.USERBM_NOT_FOUND.name());
        }
        return userbmMapper.entityToDto(optionalUserbm.get());
    }

    @Override
    public UserbmDto getUserbmByLogin(String userLogin) {
        if(userLogin == null){
            throw new NullValueException("Le userLogin envoye ne peut etre null");
        }
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmByUserLogin(userLogin);
        if(!optionalUserbm.isPresent()){
            throw new ModelNotFoundException("Il n'existe pas de Userbm en BD avec le login number envoye",
                    ErrorCode.USERBM_NOT_FOUND.name());
        }
        return userbmMapper.entityToDto(optionalUserbm.get());
    }

    @Override
    public UserbmDto getUserbmByEmail(String userEmail) {
        if(userEmail == null){
            throw new NullValueException("Le userEmail envoye ne peut etre null");
        }
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmByEmail(userEmail);
        if(!optionalUserbm.isPresent()){
            throw new ModelNotFoundException("Il n'existe pas de Userbm en BD avec l'email envoye",
                    ErrorCode.USERBM_NOT_FOUND.name());
        }
        return userbmMapper.entityToDto(optionalUserbm.get());
    }

    @Override
    public List<UserbmDto> getListofUserbm(FilterRequest filterRequest) {
        if(filterRequest == null){
            return userbmMapper.entityToDto(userbmDao.findAll());
        }

        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return userbmMapper.entityToDto(userbmDao.findAll());
        }

        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return userbmMapper.entityToDto(userbmDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Userbm> userbmSpecification = userbmSpecService.getUserbmSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return userbmMapper.entityToDto(userbmDao.findAll(userbmSpecification));
    }

    @Override
    public PageofUserbmDto getPageofUserbm(FilterRequest filterRequest) {

        Pagebm pagebm = new Pagebm();
        Page<Userbm> userbmPage = null;
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(1);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            userbmPage = userbmDao.findAll(pageable);
            //log.error("dsdsds {}", userbmPage.getTotalElements());
            return getPageofUserbmDto(userbmPage);
        }
        else{
            if(filterRequest.getPage() == null){
                pagebm.setPagenum(0);
                pagebm.setPagesize(1);
                filterRequest.setPage(pagebm);
            }

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
                Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
                userbmPage = userbmDao.findAll(pageable);
                return getPageofUserbmDto(userbmPage);
            }

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                userbmPage = userbmDao.findAll(pageable);
                return getPageofUserbmDto(userbmPage);
            }

            if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
                throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
            }

            Specification<Userbm> userbmSpecification = userbmSpecService.getUserbmSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            userbmPage = userbmDao.findAll(userbmSpecification, pageable);
            return getPageofUserbmDto(userbmPage);
        }
    }

    @Override
    public Boolean isUserbmExistWithId(Long userbmId) {
        if(userbmId == null){
            throw new NullValueException("Le userbmId envoye est null");
        }
        Optional<Userbm> optionalUserbm = userbmDao.findUserbmById(userbmId);
        return optionalUserbm.isPresent();
    }

    @Override
    public UserbmDto loadUserbmByUsername(String username) {
        if(username == null){
            throw new NullValueException("Le username du Userbm recherche ne peut etre null");
        }
        Optional<Userbm> optionalUserbm1 = userbmDao.findUserbmByEmail(username);
        Optional<Userbm> optionalUserbm2 = userbmDao.findUserbmByUserLogin(username);
        Optional<Userbm> optionalUserbm3 = userbmDao.findUserbmByUserCni(username);
        if(optionalUserbm1.isPresent()){
            return userbmMapper.entityToDto(optionalUserbm1.get());
        } else if (optionalUserbm2.isPresent()) {
            return userbmMapper.entityToDto(optionalUserbm2.get());
        } else if (optionalUserbm3.isPresent()) {
            return userbmMapper.entityToDto(optionalUserbm3.get());
        }

        throw new ModelNotFoundException("Aucun User n'existe avec les parametres envoye");
    }

    PageofUserbmDto getPageofUserbmDto(Page<Userbm> userbmPage){
        PageofUserbmDto pageofUserbmDto = new PageofUserbmDto();
        pageofUserbmDto.setContent(userbmMapper.entityToDto(userbmPage.getContent()));
        pageofUserbmDto.setCurrentPage(userbmPage.getNumber());
        pageofUserbmDto.setPageSize(userbmPage.getSize());
        pageofUserbmDto.setTotalElements(userbmPage.getTotalElements());
        pageofUserbmDto.setTotalPages(userbmPage.getTotalPages());

        return pageofUserbmDto;
    }

}
