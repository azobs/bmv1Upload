package com.c2psi.bmv1.account.accountoperation.services;

import com.c2psi.bmv1.account.account.mappers.AccountMapper;
import com.c2psi.bmv1.account.account.services.AccountService;
import com.c2psi.bmv1.account.accountoperation.dao.AccountOperationDao;
import com.c2psi.bmv1.account.accountoperation.errorCode.ErrorCode;
import com.c2psi.bmv1.account.accountoperation.mappers.AccountOperationMapper;
import com.c2psi.bmv1.account.accountoperation.models.AccountOperation;
import com.c2psi.bmv1.account.operation.mappers.OperationMapper;
import com.c2psi.bmv1.account.operation.services.OperationService;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
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

@Service(value = "AccountOperationServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountOperationServiceImpl implements AccountOperationService{

    final AppService appService;
    final AccountOperationValidator accountOperationValidator;
    final AccountOperationMapper accountOperationMapper;
    final AccountOperationDao accountOperationDao;
    final AccountOperationSpecService accountOperationSpecService;
    final AccountService accountService;
    final AccountMapper accountMapper;
    final OperationService operationService;
    final OperationMapper operationMapper;


    @Override
    public AccountOperationDto saveAccountOperation(AccountOperationDto accountOperationDto) {
        if(accountOperationDto == null){
            throw new NullValueException("The accountOperationDto sent can't be null");
        }
        List<String> errorsDto = accountOperationValidator.validate(accountOperationDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidArgumentException("Le accountOperationDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.ACCOUNTOPERATION_NOT_VALID.name());
        }
        List<String> errors = accountOperationValidator.validate(accountOperationMapper.dtoToEntity(accountOperationDto));
        if(!errors.isEmpty()){
            throw new InvalidArgumentException("Le accountOperation envoye n'est pas valide ", errors,
                    ErrorCode.ACCOUNTOPERATION_NOT_VALID.name());
        }

        if(!isAccountOperationExist(accountOperationDto.getAccountId(), accountOperationDto.getOperationId())){
            throw new DuplicateEntityException("Il existe deja cette operation sur ce compte",
                    ErrorCode.ACCOUNTOPERATION_DUPLICATED.name());
        }

        //AccountOperation accountOperationToSave = accountOperationMapper.dtoToEntity(accountOperationDto);

        /****
         * accountOperationToUpdate.setAccount(accountMapper.dtoToEntity(accountService.getAccountById(
         *                 accountOperationDto.getAccountId())));
         *         accountOperationToUpdate.setOperation(operationMapper.dtoToEntity(operationService.getOperationById(
         *                 accountOperationDto.getOperationId())));
         *         accountOperationToUpdate.setQuantityinMvt(accountOperationDto.getQuantityinMvt());
         */
        AccountOperation accountOperationToSave = AccountOperation.builder()
                .account(accountMapper.dtoToEntity(accountService.getAccountById(accountOperationDto.getAccountId())))
                .operation(operationMapper.dtoToEntity(operationService.getOperationById(accountOperationDto.getOperationId())))
                .quantityinMvt(accountOperationDto.getQuantityinMvt())
                .build();

        return accountOperationMapper.entityToDto(accountOperationDao.save(accountOperationToSave));
    }

    Boolean isAccountOperationExist(Long accountId, Long operationId){
        if(accountId == null || operationId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<AccountOperation> optionalAccountOperation = accountOperationDao.
                findAccountOperationByAttributes(accountId, operationId);
        return optionalAccountOperation.isEmpty();
    }

    @Override
    public AccountOperationDto updateAccountOperation(AccountOperationDto accountOperationDto) {
        if(accountOperationDto == null){
            throw new NullValueException("Le accountOperationDto sent pour update ne saurait etre null");
        }
        if(accountOperationDto.getId() == null){
            throw new InvalidEntityException("L'id du accountOperation a update ne saurait etre null");
        }
        List<String> errorsDto = accountOperationValidator.validate(accountOperationDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidArgumentException("Le accountOperationDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.ACCOUNTOPERATION_NOT_VALID.name());
        }
        List<String> errors = accountOperationValidator.validate(accountOperationMapper.dtoToEntity(accountOperationDto));
        if(!errors.isEmpty()){
            throw new InvalidArgumentException("Le accountOperation envoye n'est pas valide ", errors,
                    ErrorCode.ACCOUNTOPERATION_NOT_VALID.name());
        }
        Optional<AccountOperation> optionalAccountOperation = accountOperationDao.findAccountOperationById(
                accountOperationDto.getId());
        if(!optionalAccountOperation.isPresent()){
            throw new ModelNotFoundException("Aucun accountOperation n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.ACCOUNTOPERATION_NOT_FOUND.name());
        }
        AccountOperation accountOperationToUpdate = optionalAccountOperation.get();
        boolean accountToUpdate = !accountOperationToUpdate.getAccount().getId().equals(accountOperationDto.getAccountId());
        boolean operationToUpdate = !accountOperationToUpdate.getOperation().getId().equals(accountOperationDto.getOperationId());
        if(accountToUpdate || operationToUpdate){
            if(!isAccountOperationExist(accountOperationDto.getAccountId(), accountOperationDto.getOperationId())){
                throw new DuplicateEntityException("Il existe deja cette operation sur ce compte",
                        ErrorCode.ACCOUNTOPERATION_DUPLICATED.name());
            }
        }
        accountOperationToUpdate.setAccount(accountMapper.dtoToEntity(accountService.getAccountById(
                accountOperationDto.getAccountId())));
        accountOperationToUpdate.setOperation(operationMapper.dtoToEntity(operationService.getOperationById(
                accountOperationDto.getOperationId())));
        accountOperationToUpdate.setQuantityinMvt(accountOperationDto.getQuantityinMvt());
        return accountOperationMapper.entityToDto(accountOperationDao.save(accountOperationToUpdate));
    }

    @Override
    public Boolean deleteAccountOperationById(Long id) {
        if(id == null){
            throw new NullValueException("L'id envoye ne saurait etre null");
        }
        Optional<AccountOperation> optionalAccountOperation = accountOperationDao.findAccountOperationById(id);
        if(!optionalAccountOperation.isPresent()){
            throw new ModelNotFoundException("Aucun accountOperation n'existe avec l'id saisi",
                    ErrorCode.ACCOUNTOPERATION_NOT_FOUND.name());
        }
        if(!isAccountOperationDeleteable(optionalAccountOperation.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer ce accountoperation sans conflit ",
                    ErrorCode.ACCOUNTOPERATION_NOT_DELETEABLE.name());
        }
        accountOperationDao.delete(optionalAccountOperation.get());
        return true;
    }

    Boolean isAccountOperationDeleteable(AccountOperation accountOperation){
        return true;
    }

    @Override
    public AccountOperationDto getAccountOperationById(Long id) {
        if(id == null){
            throw new NullValueException("L'id envoye ne saurait etre null");
        }
        Optional<AccountOperation> optionalAccountOperation = accountOperationDao.findAccountOperationById(id);
        if(!optionalAccountOperation.isPresent()){
            throw new ModelNotFoundException("Aucun accountOperation n'existe avec l'id saisi",
                    ErrorCode.ACCOUNTOPERATION_NOT_FOUND.name());
        }
        return accountOperationMapper.entityToDto(optionalAccountOperation.get());
    }

    @Override
    public List<AccountOperationDto> getListofAccountOperation(FilterRequest filterRequest) {

        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return accountOperationMapper.entityToDto(accountOperationDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return accountOperationMapper.entityToDto(accountOperationDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return accountOperationMapper.entityToDto(accountOperationDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<AccountOperation> accopSpecification = accountOperationSpecService.getAccountOperationSpecification(
                filterRequest.getFilters(), filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return accountOperationMapper.entityToDto(accountOperationDao.findAll(accopSpecification));
    }

    @Override
    public PageofAccountOperationDto getPageofAccountOperation(FilterRequest filterRequest) {

        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<AccountOperation> accountOperationPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            accountOperationPage = accountOperationDao.findAll(pageable);
            return getPageofAccountOperationDto(accountOperationPage);
        }
        else{
            /*************************************************************************************
             * Si le filterRequest envoye n'est pas null mais que l'element pas indiquant le numero
             * et la taille de page voulu est null alors on assigne des valeurs par defaut soit
             * page numero 0 et taille de page 10
             */
            if(filterRequest.getPage() == null){
                pagebm.setPagenum(0);
                pagebm.setPagesize(10);
                filterRequest.setPage(pagebm);
            }

            /**************************************************************************************
             * Si dans le filterRequest envoye les filtres et les elements de tri sont null alors
             * on retourne le findAll page par page.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
                Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
                accountOperationPage = accountOperationDao.findAll(pageable);
                return getPageofAccountOperationDto(accountOperationPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                accountOperationPage = accountOperationDao.findAll(pageable);
                return getPageofAccountOperationDto(accountOperationPage);
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
            Specification<AccountOperation> accountOperationSpecification = accountOperationSpecService.getAccountOperationSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            accountOperationPage = accountOperationDao.findAll(accountOperationSpecification, pageable);
            return getPageofAccountOperationDto(accountOperationPage);

        }
    }

    PageofAccountOperationDto getPageofAccountOperationDto(Page<AccountOperation> accountOperationPage){
        PageofAccountOperationDto pageofAccountOperationDto = new PageofAccountOperationDto();
        pageofAccountOperationDto.setContent(accountOperationMapper.entityToDto(accountOperationPage.getContent()));
        pageofAccountOperationDto.setCurrentPage(accountOperationPage.getNumber());
        pageofAccountOperationDto.setPageSize(accountOperationPage.getSize());
        pageofAccountOperationDto.setTotalElements(accountOperationPage.getTotalElements());
        pageofAccountOperationDto.setTotalPages(accountOperationPage.getTotalPages());

        return pageofAccountOperationDto;

    }

    @Override
    public Boolean isAccountOperationExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id envoye ne saurait etre null");
        }
        Optional<AccountOperation> optionalAccountOperation = accountOperationDao.findAccountOperationById(id);

        return optionalAccountOperation.isPresent();
    }
}
