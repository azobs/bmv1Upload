package com.c2psi.bmv1.pos.pos.service;

import com.c2psi.bmv1.address.mappers.AddressMapper;
import com.c2psi.bmv1.address.services.AddressService;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.currency.mappers.CurrencyMapper;
import com.c2psi.bmv1.currency.models.Currency;
import com.c2psi.bmv1.currency.services.CurrencyService;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.PageofPointofsaleDto;
import com.c2psi.bmv1.dto.PointofsaleDto;
import com.c2psi.bmv1.pos.enterprise.mappers.EnterpriseMapper;
import com.c2psi.bmv1.pos.enterprise.models.Enterprise;
import com.c2psi.bmv1.pos.enterprise.services.EnterpriseService;
import com.c2psi.bmv1.pos.pos.dao.PointofsaleDao;
import com.c2psi.bmv1.pos.pos.errorCode.ErrorCode;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.models.Pointofsale;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
    final CurrencyMapper currencyMapper;
    final EnterpriseMapper enterpriseMapper;
    final AddressMapper addressMapper;



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
        /******************************************************************
         * On se rassure que le posDto en argument n'est pas null
         */
        if (posDto == null) {
            throw new NullValueException("Le posDto a update ne peut etre null");
        }

        /****************************************************************
         * On valide le parametre posDto avec le validateur
         */
        List<String> errors = posValidator.validate(posMapper.dtoToEntity(posDto));
        if (!errors.isEmpty()) {
            log.error("Validation problem with the entity pos to save {}", posDto);
            throw new InvalidEntityException("Le pointofsale envoye pour update n'est pas valide ", errors,
                    ErrorCode.POINTOFSALE_NOT_VALID.name());
        }

        /************************************************************
         * On verifie que l'id n'est pas null
         */
        if (posDto.getId() == null) {
            throw new InvalidEntityException("L'id du pointofsale a update ne peut etre null ",
                    ErrorCode.POINTOFSALE_NOT_VALID.name());
        }

        /**********************************************************
         * On fait la recherche du Pos a update grace a l'id
         */
        Optional<Pointofsale> optionalPointofsale = posDao.findPointofsaleById(posDto.getId());
        if (!optionalPointofsale.isPresent()) {
            throw new ModelNotFoundException("L'id indique dans le posDto n'identifie aucun Pointofsale en BD ",
                    ErrorCode.POINTOFSALE_NOT_FOUND.name());
        }
        Pointofsale posToUpdate = optionalPointofsale.get();
        /******************************************************
         * Si c'est l'email quon veut modifier on se rassurer
         * que lq contrainte d'unicite ne sera pas viole
         */
        if (posToUpdate.getPosAddress().getEmail() != null && posDto.getPosAddress().getEmail() != null) {
            if (!posToUpdate.getPosAddress().getEmail().equals(posDto.getPosAddress().getEmail())) {
                if (!isPosEmailUsable(posDto.getPosAddress().getEmail())) {
                    log.error("The email {} is already used", posDto.getPosAddress().getEmail());
                    throw new DuplicateEntityException("L'adresse email envoye pour la mise a jour du  Pointofsale est deja utilise ",
                            ErrorCode.POINTOFSALE_DUPLICATED.name());
                }
                //posToUpdate.getPosAddress().setEmail(posDto.getPosAddress().getEmail());
                //On a un service qui va effectue la mise a jour d'une adresse
                //Address addressUpdated = addressMapper.dtoToEntity(addressService.updateAddress(posDto.getPosAddress()));
                addressService.updateAddress(posDto.getPosAddress());
            }
        }

        /**********************************************************
         * Si c'est le name ou l'acronym qu'on veut modifier
         * on se rassure que la contrainte d'unicite me sera viole
         */
        if (posToUpdate.getPosName() != null && posDto.getPosName() != null && posToUpdate.getPosAcronym() != null &&
                posDto.getPosAcronym() != null){
            if (!posToUpdate.getPosName().equals(posDto.getPosName()) ||
                    !posToUpdate.getPosAcronym().equals(posDto.getPosAcronym())) {
                if (!isPosFullnameUsable(posDto.getPosName(), posDto.getPosAcronym())) {
                    log.error("The fullname of pos {} {} sent for update is already used", posDto.getPosName(), posDto.getPosAcronym());
                    throw new DuplicateEntityException("Le nom et l'acronym du Pointofsale envoye pour mise a jour sont deja utilises ",
                            ErrorCode.POINTOFSALE_DUPLICATED.name());
                }
                posToUpdate.setPosName(posDto.getPosName());
                posToUpdate.setPosAcronym(posDto.getPosAcronym());
            }
        }

        /**************************************************************
         * Si c'est le currency par defaut qu'on veut changer on se
         * rassure que le nouvel id identifie bien un currency
         */
        if(posToUpdate.getPosCurrency() != null && posDto.getPosCurrency() != null) {
            if(posToUpdate.getPosCurrency().getId() != null && posDto.getPosCurrency().getId() != null) {
                if (posToUpdate.getPosCurrency().getId().longValue() != posDto.getPosCurrency().getId().longValue()) {
                    if (!currencyService.isCurrencyExistWith(posDto.getPosCurrency().getId())) {
                        throw new ModelNotFoundException("Le currency indique pour la mise a jour du pointofsale n'existe pas en BD: Verifier " +
                                "l'id indique. Le pointofsale n'est donc pas valide ", ErrorCode.POINTOFSALE_NOT_VALID.name());
                    }
                    Currency newCurrency = currencyMapper.dtoToEntity(currencyService.getCurrencyById(posDto.getPosCurrency().getId()));
                    posToUpdate.setPosCurrency(newCurrency);
                }
            }
        }

        /**************************************************************
         * Si c'est l'entreprise qu'on veut changer on se rassure
         *  que le nouvel id identifie bien une entreprise
         */
        if(posToUpdate.getPosEnterprise() != null && posDto.getPosEnterprise() != null) {
            if (posToUpdate.getPosEnterprise().getId() != null && posDto.getPosEnterprise().getId() != null) {
                if (posToUpdate.getPosEnterprise().getId().longValue() != posDto.getPosEnterprise().getId().longValue()) {
                    if (!enterpriseService.isEnterpriseExistWith(posDto.getPosEnterprise().getId())) {
                        throw new ModelNotFoundException("L'entreprise indique pour la mise a jour du pointofsale n'existe pas en BD: Verifier " +
                                "l'id indique. Le pointofsale n'est donc pas valide ", ErrorCode.POINTOFSALE_NOT_VALID.name());
                    }
                    Enterprise newEnt = enterpriseMapper.dtoToEntity(enterpriseService.getEnterpriseById(posDto.getPosEnterprise().getId()));
                    posToUpdate.setPosEnterprise(newEnt);
                }
            }
        }

        /*****************************************************
         * Si tout est bon on effectue le reste des update
         */
        log.info("After all verification, the rest of update can be done safely");
        posToUpdate.setPosDescription(posDto.getPosDescription());
        posToUpdate.setPosBalance(posDto.getPosBalance());

        /****************************************************
         * On valide la mise a jour
         */
        Pointofsale posUpdated = posDao.save(posToUpdate);
        return posMapper.entityToDto(posUpdated);
    }

    @Override
    public Boolean deletePointofsaleById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du pointofsale a supprimer ne peut etre null");
        }
        Optional<Pointofsale> optionalPointofsale = posDao.findPointofsaleById(id);
        if(!optionalPointofsale.isPresent()){
            throw new ModelNotFoundException("L'id du pointofsale a supprimer n'identifie aucun pointofsale en BD",
                    ErrorCode.POINTOFSALE_NOT_FOUND.name());
        }
        Pointofsale posToDelete = optionalPointofsale.get();
        if(!isPosDeleteable(posToDelete)){
            throw new EntityNotDeleatableException("Le pointofsale indique ne peut etre supprimer sans causer de conflits",
                    ErrorCode.POINTOFSALE_NOT_DELETEABLE.name());
        }
        log.info("The pointofsale indicated can be safely deleted");
        posDao.delete(posToDelete);
        return true;
    }

    Boolean isPosDeleteable(Pointofsale pos){
        return true;
    }

    @Override
    public PointofsaleDto getPointofsaleById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du pointofsale rechercher ne saurait etre null");
        }
        Optional<Pointofsale> optionalPointofsale = posDao.findPointofsaleById(id);
        if(!optionalPointofsale.isPresent()){
            throw new ModelNotFoundException("L'id du pointofsale recherche n'identifie aucun pointofsale en BD",
                    ErrorCode.POINTOFSALE_NOT_FOUND.name());
        }
        Pointofsale posFound = optionalPointofsale.get();

        return posMapper.entityToDto(posFound);
    }

    @Override
    public List<PointofsaleDto> getListofPointofsale(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return posMapper.entityToDto(posDao.findAll());
        }
        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return posMapper.entityToDto(posDao.findAll());
        }
        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return posMapper.entityToDto(posDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Pointofsale> posSpecification = posSpecService.getPosSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return posMapper.entityToDto(posDao.findAll(posSpecification));
    }

    @Override
    public PageofPointofsaleDto getPageofPointofsale(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        com.c2psi.bmv1.dto.Page page = new com.c2psi.bmv1.dto.Page();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Pointofsale> posPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            page.setPagenum(0);
            page.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(page);
            posPage = posDao.findAll(pageable);
            return getPageofPosDto(posPage);
        }
        else{
            /*************************************************************************************
             * Si le filterRequest envoye n'est pas null mais que l'element pas indiquant le numero
             * et la taille de page voulu est null alors on assigne des valeurs par defaut soit
             * page numero 0 et taille de page 10
             */
            if(filterRequest.getPage() == null){
                page.setPagenum(0);
                page.setPagesize(10);
                filterRequest.setPage(page);
            }

            /**************************************************************************************
             * Si dans le filterRequest envoye les filtres et les elements de tri sont null alors
             * on retourne le findAll page par page.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
                Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
                posPage = posDao.findAll(pageable);
                return getPageofPosDto(posPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                posPage = posDao.findAll(pageable);
                return getPageofPosDto(posPage);
            }

            /*********************************************************************************************
             * Si l'operateur logique permettant de lier les filtres est null et que la liste des filtres
             * contient plus d'un filtre alors il ya un probleme dans les parametres
             */
            if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
                throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
            }

            /****************************************************************************************************
             * On peut ici lancer une recherche selon les filtres envoyes, les classer selon les elements de tri
             * et ensuite la page demande
             */
            Specification<Pointofsale> posSpecification = posSpecService.getPosSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            posPage = posDao.findAll(posSpecification, pageable);
            return getPageofPosDto(posPage);

        }
    }

    @Override
    public Boolean isEnterpriseExistWith(Long id) {
        if(id == null){
            return false;
        }
        Optional<Pointofsale> optionalPointofsale = posDao.findPointofsaleById(id);
        return optionalPointofsale.isPresent();
    }


    PageofPointofsaleDto getPageofPosDto(Page<Pointofsale> posPage){
        PageofPointofsaleDto pageofPosDto = new PageofPointofsaleDto();
        pageofPosDto.setContent(posMapper.entityToDto(posPage.getContent()));
        pageofPosDto.setCurrentPage(posPage.getNumber());
        pageofPosDto.setPageSize(posPage.getSize());
        pageofPosDto.setTotalElements(posPage.getTotalElements());
        pageofPosDto.setTotalPages(posPage.getTotalPages());

        return pageofPosDto;
    }

}
