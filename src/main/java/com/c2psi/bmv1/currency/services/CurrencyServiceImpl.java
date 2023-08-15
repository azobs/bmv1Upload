package com.c2psi.bmv1.currency.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.currency.dao.CurrencyDao;
import com.c2psi.bmv1.currency.mappers.CurrencyMapper;
import com.c2psi.bmv1.currency.models.Currency;
import com.c2psi.bmv1.dto.CurrencyDto;
import com.c2psi.bmv1.dto.FilterRequest;
import com.c2psi.bmv1.dto.Pagebm;
import com.c2psi.bmv1.dto.PageofCurrencyDto;
import com.c2psi.bmv1.currency.errorCode.ErrorCode;
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

@Service(value = "CurrencyServiceV1")
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService{
    final CurrencyDao currencyDao;
    final CurrencyMapper currencyMapper;
    final AppService appService;
    final CurrencyValidator currencyValidator;
    final CurrencySpecService currencySpecService;


    @Override
    public CurrencyDto saveCurrency(CurrencyDto currencyDto) {
        /******************************************************************************************
         * On se rassure que le parametre n'est pas null et si c'est le cas on leve une exception
         */
        if(currencyDto == null){
            throw new NullValueException("L'argument currencyDto ne peut etre null");
        }

        /**************************************************************
         * On valide le parametre a enregistrer grace au validateur
         */
        List<String> errors = currencyValidator.validate(currencyMapper.dtoToEntity(currencyDto));
        if(!errors.isEmpty()){
            log.error("Entity Currency not valid because of {}", errors);
            throw new InvalidEntityException("Le Currency a enregistrer n'est pas valide ", errors,
                    ErrorCode.CURRENCY_NOT_VALID.name());
        }

        /*******************************************************************
         *On se rassure que les contraintes d'unicite ne seront pas violes
         */
        if(currencyDto.getCurrencyName() != null && currencyDto.getCurrencyAbbreviation() != null){
            if(!isCurrencyFullnameUsable(currencyDto.getCurrencyName(), currencyDto.getCurrencyAbbreviation())){
                throw new DuplicateEntityException("L'identifiant unique envoye est deja utilise",
                        ErrorCode.CURRENCY_DUPLICATED.name());
            }
        }

        /************************************************************
         * Si tout est bon on peut donc sauvegarder l'entite
         */
        log.info("After all verification the entity can be saved");
        Currency currencySaved = currencyDao.save(currencyMapper.dtoToEntity(currencyDto));
        return currencyMapper.entityToDto(currencySaved);
    }

    Boolean isCurrencyFullnameUsable(String currencyName, String currencyAbbreviation){
//        log.error("currencyName = {}",currencyName);
//        log.error("currencyAbbreviation = {}", currencyAbbreviation);
        Optional<Currency> optionalCurrency = currencyDao.findCurrencyByFullname(currencyName, currencyAbbreviation);
        return optionalCurrency.isEmpty();
    }

    @Override
    public CurrencyDto updateCurrency(CurrencyDto currencyDto) {
        /*******************************************************
         * On se rassure que le parametre n'est pas null
         */
        if(currencyDto == null){
            throw new NullValueException("Le parametre currencyDto ne peut etre null");
        }

        /***************************************************************
         * On valide le parametre grace au validateur
         */
        List<String> errors = currencyValidator.validate(currencyMapper.dtoToEntity(currencyDto));
        if(!errors.isEmpty()){
            log.error("Entity Currency not valid because of {}", errors);
            throw new InvalidEntityException("Le Currency a enregistrer n'est pas valide ", errors,
                    ErrorCode.CURRENCY_NOT_VALID.name());
        }

        /***************************************************************
         * On recherche l'entite currency a modifier
         */
        if(currencyDto.getId() == null){
            throw new NullValueException("L'identifiant du currency a update est null");
        }

        Optional<Currency> optionalCurrency = currencyDao.findCurrencyById(currencyDto.getId());
        if(!optionalCurrency.isPresent()){
            throw new ModelNotFoundException("Aucun currency n'existe avec l'id precise",
                    ErrorCode.CURRENCY_NOT_FOUND.name());
        }
        Currency currencyToUpdate = optionalCurrency.get();
        /******************************************************************
         * On se rassure que la contrainte d'unicite ne sera viole au cas
         * ou un des champs le composant est modifie
         */
        if(!currencyDto.getCurrencyName().equalsIgnoreCase(currencyToUpdate.getCurrencyName()) ||
            !currencyDto.getCurrencyAbbreviation().equalsIgnoreCase(currencyToUpdate.getCurrencyAbbreviation())){
            if(!isCurrencyFullnameUsable(currencyDto.getCurrencyName(), currencyDto.getCurrencyAbbreviation())){
                throw new DuplicateEntityException("Les nouveaux parametre du currency sont deja utilise ",
                        ErrorCode.CURRENCY_DUPLICATED.name());
            }
            currencyToUpdate.setCurrencyName(currencyDto.getCurrencyName());
            currencyToUpdate.setCurrencyAbbreviation(currencyDto.getCurrencyAbbreviation());
        }

        /**********************************************
         * Si tout est bon on enregistre la mise a jour
         */
        log.info("After all verification the entity can be updated");

        Currency currencyUpdated = currencyDao.save(currencyToUpdate);
        return currencyMapper.entityToDto(currencyUpdated);
    }

    @Override
    public Boolean deleteCurrencyById(Long id) {
        /*************************************************
         * Il faut se rassurer que l'id n'est pas null
         */
        if(id == null){
            throw new NullValueException("L'id du currency a supprimer ne peut etre null");
        }

        /******************************************************
         * Il faut recuperer le Currency identifier par cet id
         */
        Optional<Currency> optionalCurrency = currencyDao.findCurrencyById(id);
        if(!optionalCurrency.isPresent()){
            throw new ModelNotFoundException("Aucun currency n'existe avec l'id specifie",
                    ErrorCode.CURRENCY_NOT_FOUND.name());
        }
        Currency currency = optionalCurrency.get();
        /**********************************************************
         * Il faut se rassurer que ce currency peut etre supprimer
         * sans creer de conflit
         */
        if(!isCurrencyDeleteable(currency)){
            throw new EntityNotDeleatableException("Le currency ne peut etre supprime sans creer des conflits",
                    ErrorCode.CURRENCY_NOT_DELETEABLE.name());
        }

        /************************************************************
         * Il faut donc effectuer la suppression
         */
        log.info("After all verification, currency can be deleted safely");
        currencyDao.delete(currency);
        return true;
    }

    public Boolean isCurrencyDeleteable(Currency currency){
        return true;
    }

    @Override
    public CurrencyDto getCurrencyById(Long id) {
        /****************************************
         * On se rassure que l'id n'est pas null
         */
        if(id == null){
            throw new NullValueException("L'id envoye ne saurait etre null");
        }

        /********************************************
         * On effectue la recherche proprement dite
         */
        Optional<Currency> optionalCurrency = currencyDao.findCurrencyById(id);
        if(!optionalCurrency.isPresent()){
            throw new ModelNotFoundException("Aucun currency n'existe avec l'id envoye",
                    ErrorCode.CURRENCY_NOT_FOUND.name());
        }
        return currencyMapper.entityToDto(optionalCurrency.get());
    }

    @Override
    public List<CurrencyDto> getListofCurrency(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return currencyMapper.entityToDto(currencyDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return currencyMapper.entityToDto(currencyDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return currencyMapper.entityToDto(currencyDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        log.info("We are going to call getCurrencySpecification ");
        Specification<Currency> currencySpecification = currencySpecService.getCurrencySpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return currencyMapper.entityToDto(currencyDao.findAll(currencySpecification));
    }

    @Override
    public PageofCurrencyDto getPageofCurrency(FilterRequest filterRequest) {
        /*********************************************************************
         * On prepare un element pagebm de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /*********************************************************************
         * On declare un objet Page de spring
         */
        Page<Currency> currencyPage = null;
        /*************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on
         * retourne page par page. On va donc retourner la page 0 avec une taille
         * de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            currencyPage = currencyDao.findAll(pageable);
            return getPageofCurrencyDto(currencyPage);
        }
        else{
            /*******************************************************************************************************
             * Si le filterRequest envoye n'est pas null mais que l'element pas indiquant le numero
             * et la taille de page voulu est null alors on assigne des valeurs par defaut soit
             * page numero 0 et taille de page 10
             */
            if(filterRequest.getPage() == null){
               pagebm.setPagenum(0);
               pagebm.setPagesize(10);
               filterRequest.setPage(pagebm);
            }

            /***********************************************************************************************
             * Si dans le filterRequest envoye les filtres et les elements de tri sont null alors
             * on retourne le findAll page par page.
             */
            if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
                Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
                currencyPage = currencyDao.findAll(pageable);
                return getPageofCurrencyDto(currencyPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */
            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                currencyPage = currencyDao.findAll(pageable);
                return getPageofCurrencyDto(currencyPage);
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
            Specification<Currency> currencySpecification = currencySpecService.
                    getCurrencySpecification(filterRequest.getFilters(), filterRequest.getLogicOperator(),
                            filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            currencyPage = currencyDao.findAll(currencySpecification, pageable);
            return getPageofCurrencyDto(currencyPage);
        }
    }

    @Override
    public Boolean isCurrencyExistWith(Long id) {
        Optional<Currency> optionalCurrency = currencyDao.findCurrencyById(id);
        return optionalCurrency.isPresent();
    }

    PageofCurrencyDto getPageofCurrencyDto(Page<Currency> currencyPage){
        PageofCurrencyDto pageofCurrencyDto = new PageofCurrencyDto();
        pageofCurrencyDto.setContent(currencyMapper.entityToDto(currencyPage.getContent()));
        pageofCurrencyDto.setCurrentPage(currencyPage.getNumber());
        pageofCurrencyDto.setPageSize(currencyPage.getSize());
        pageofCurrencyDto.setTotalElements(currencyPage.getTotalElements());
        pageofCurrencyDto.setTotalPages(currencyPage.getTotalPages());

        return pageofCurrencyDto;
    }

}
