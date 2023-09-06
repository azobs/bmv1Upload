package com.c2psi.bmv1.account.account.services;

import com.c2psi.bmv1.account.account.dao.AccountDao;
import com.c2psi.bmv1.account.account.errorCode.ErrorCode;
import com.c2psi.bmv1.account.account.mappers.AccountMapper;
import com.c2psi.bmv1.account.account.models.Account;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.enumerations.AccountTypeEnum;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.client.client.mappers.ClientMapper;
import com.c2psi.bmv1.client.client.services.ClientService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.packaging.packaging.mappers.PackagingMapper;
import com.c2psi.bmv1.packaging.packaging.services.PackagingService;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.product.article.mappers.ArticleMapper;
import com.c2psi.bmv1.product.article.service.ArticleService;
import com.c2psi.bmv1.provider.provider.mappers.ProviderMapper;
import com.c2psi.bmv1.provider.provider.service.ProviderService;
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

@Service(value = "AccountServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{

    final AppService appService;
    final AccountDao accountDao;
    final AccountMapper accountMapper;
    final AccountValidator accountValidator;
    final AccountSpecService accountSpecService;
    final ArticleService articleService;
    final ArticleMapper articleMapper;
    final PackagingService packagingService;
    final PackagingMapper packagingMapper;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;
    final ProviderService providerService;
    final ProviderMapper providerMapper;
    final ClientService clientService;
    final ClientMapper clientMapper;

    @Override
    public AccountDto saveAccount(AccountDto accountDto) {
        log.info("We start saving the account by checking if it is not null");
        if(accountDto == null){
            throw new NullValueException("The accountDto to save can't be null");
        }
        log.info("We continue saving the account by validate the dto");
        List<String> errorsDto  = accountValidator.validate(accountDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("L'accounDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.ACCOUNT_NOT_VALID.name());
        }
        log.info("We continue saving the account by validate its entity associate");
        List<String> errors  = accountValidator.validate(accountMapper.dtoToEntity(accountDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("L'accounDto envoye n'est pas valide ", errors,
                    ErrorCode.ACCOUNT_NOT_VALID.name());
        }
        log.info("We continue saving the account by ensure the unicity of the account according to the argument " +
                "precise");

        boolean articlePresent = accountDto.getAccountArticleId() != null;
        boolean clientPresent = accountDto.getAccountClientId() != null;
        boolean providerPresent = accountDto.getAccountProviderId() != null;
        boolean packagingPresent = accountDto.getAccountPackagingId() != null;

        if(articlePresent && clientPresent){
            if(!isAccountofArticleAndClientInPosExist(accountDto.getAccountArticleId(),
                    accountDto.getAccountClientId(), accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte existe deja pour cet article et ce client dans " +
                        "le pointofsale indique ", ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        if(articlePresent && providerPresent){
            if(!isAccountofArticleAndProviderInPosExist(accountDto.getAccountArticleId(),
                    accountDto.getAccountProviderId(), accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte existe deja pour cet article et ce provider dans " +
                        "le pointofsale indique ", ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        if(packagingPresent && clientPresent){
            if(!isAccountofPackagingAndClientInPos(accountDto.getAccountPackagingId(), accountDto.getAccountClientId(),
                    accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte existe deja pour ce packaging et ce client dans " +
                        "le pointofsale indique ", ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        if(packagingPresent && providerPresent){
            if (!isAccountofPackagingAndProviderInPos(accountDto.getAccountPackagingId(),
                    accountDto.getAccountProviderId(), accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte existe deja pour ce packaging et ce provider dans le " +
                        "pointofsale indique", ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        if(articlePresent){
            if(!isAccountofArticleOfPos(accountDto.getAccountArticleId(), accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte pour cet article dans le pointofsale existe deja",
                        ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        if(packagingPresent){
            if(!isAccountofPackagingOfPos(accountDto.getAccountPackagingId(), accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte pour ce packaging existe deja dans le pointofsale " +
                        "indique ", ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        log.info("We continue saving the account by preparing the account to validate");
        //Account accountToSave = accountMapper.dtoToEntity(accountDto);
        Account accountToSave = Account.builder()
                .accountArticle(accountDto.getAccountArticleId() == null ? null : articleMapper.dtoToEntity(
                        articleService.getArticleById(accountDto.getAccountArticleId())))
                .accountPackaging(accountDto.getAccountPackagingId() == null ? null : packagingMapper.dtoToEntity(
                        packagingService.getPackagingById(accountDto.getAccountPackagingId())))
                .accountClient(accountDto.getAccountClientId() == null ? null : clientMapper.dtoToEntity(
                        clientService.getClientById(accountDto.getAccountClientId())))
                .accountProvider(accountDto.getAccountProviderId() == null ? null : providerMapper.dtoToEntity(
                        providerService.getProviderById(accountDto.getAccountProviderId())))
                .accountPos(posMapper.dtoToEntity(posService.getPointofsaleById(accountDto.getAccountPosId())))
                .accountType(convertToAccountDtoAccountTypeEnum(accountDto.getAccountType()))
                .coverNumber(accountDto.getCoverNumber())
                .damageNumber(accountDto.getDamageNumber())
                .packageNumber(accountDto.getPackageNumber())
                .build();
        /***
         * accountToUpdate.setAccountArticle(accountDto.getAccountArticleId() == null ? null : articleMapper.dtoToEntity(
         *                 articleService.getArticleById(accountDto.getAccountArticleId())));
         *         accountToUpdate.setAccountPackaging(accountDto.getAccountPackagingId() == null ? null : packagingMapper.dtoToEntity(
         *                 packagingService.getPackagingById(accountDto.getAccountPackagingId())));
         *         accountToUpdate.setAccountClient(accountDto.getAccountClientId() == null ? null : clientMapper.dtoToEntity(
         *                 clientService.getClientById(accountDto.getAccountClientId())));
         *         accountToUpdate.setAccountProvider(accountDto.getAccountProviderId() == null ? null : providerMapper.dtoToEntity(
         *                 providerService.getProviderById(accountDto.getAccountProviderId())));
         *         accountToUpdate.setAccountPos(posMapper.dtoToEntity(posService.getPointofsaleById(accountDto.getAccountPosId())));
         *         accountToUpdate.setAccountType(convertToAccountDtoAccountTypeEnum(accountDto.getAccountType()));
         *         accountToUpdate.setCoverNumber(accountDto.getCoverNumber());
         *         accountToUpdate.setDamageNumber(accountDto.getDamageNumber());
         *         accountToUpdate.setPackageNumber(accountDto.getPackageNumber());
         */



        return accountMapper.entityToDto(accountDao.save(accountToSave));
    }

    Boolean isAccountofArticleAndClientInPosExist(Long articleId, Long clientId, Long posId){
        if(articleId == null || clientId == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Account> optionalAccount = accountDao.findAccountofArticleAndClientInPos(articleId, clientId, posId);
        return optionalAccount.isEmpty();
    }

    Boolean isAccountofArticleAndProviderInPosExist(Long articleId, Long providerId, Long posId){
        if(articleId == null || providerId == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Account> optionalAccount = accountDao.findAccountofArticleAndProviderInPos(articleId, providerId, posId);
        return optionalAccount.isEmpty();
    }

    Boolean isAccountofPackagingAndClientInPos(Long packagingId, Long clientId, Long posId){
        if(packagingId == null || clientId == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Account> optionalAccount = accountDao.findAccountofPackagingAndClientInPos(packagingId, clientId, posId);
        return optionalAccount.isEmpty();
    }

    Boolean isAccountofPackagingAndProviderInPos(Long packagingId, Long providerid, Long posId){
        if(packagingId == null || providerid == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Account> optionalAccount = accountDao.findAccountofPackagingAndProviderInPos(packagingId, providerid, posId);
        return optionalAccount.isEmpty();
    }

    Boolean isAccountofArticleOfPos(Long articleId, Long posId){
        if(articleId == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Account> optionalAccount = accountDao.findAccountofArticleOfPos(articleId, posId);
        return optionalAccount.isEmpty();
    }

    Boolean isAccountofPackagingOfPos(Long packagingId, Long posId){
        if(packagingId == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Account> optionalAccount = accountDao.findAccountofPackagingOfPos(packagingId, posId);
        return optionalAccount.isEmpty();
    }


    @Override
    public AccountDto updateAccount(AccountDto accountDto) {
        log.info("We start updating the account by checking if it is not null");
        if(accountDto == null){
            throw new NullValueException("Le accountDto a update ne saurait etre null");
        }
        log.info("We continue updating the account by checking if the id is not null");
        if(accountDto.getId() == null){
            throw new NullValueException("L'Id du accountDto a update ne saurait etre null");
        }
        log.info("We continue updating the account by validate the dto");
        List<String> errorsDto  = accountValidator.validate(accountDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("L'accounDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.ACCOUNT_NOT_VALID.name());
        }
        log.info("We continue updating the account by validate its entity associate");
        List<String> errors  = accountValidator.validate(accountMapper.dtoToEntity(accountDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("L'accounDto envoye n'est pas valide ", errors,
                    ErrorCode.ACCOUNT_NOT_VALID.name());
        }
        log.info("We continue updating the account by retrieve the account to update");
        Optional<Account> optionalAccount = accountDao.findAccountById(accountDto.getId());
        if(!optionalAccount.isPresent()){
            throw new ModelNotFoundException("Aucun account n'existe en BD avec l'id envoye ",
                    ErrorCode.ACCOUNT_NOT_FOUND.name());
        }
        Account accountToUpdate = optionalAccount.get();
        log.info("We continue updating the account by ensure the unicity of the account according to the argument precise");

        boolean articleToUpdate = accountDto.getAccountArticleId() != null ? !accountDto.getAccountArticleId().equals(
                accountToUpdate.getAccountArticle().getId()):false;
        boolean clientToUpdate = accountDto.getAccountClientId() != null ? !accountDto.getAccountClientId().equals(
                accountToUpdate.getAccountClient().getId()): false;
        boolean providerToUpdate = accountDto.getAccountProviderId() != null ? !accountDto.getAccountProviderId().equals(
                accountToUpdate.getAccountProvider().getId()): false;
        boolean packagingToUpdate = accountDto.getAccountPackagingId() != null ? !accountDto.getAccountPackagingId().equals(
                accountToUpdate.getAccountPackaging().getId()): false;


        if(articleToUpdate || clientToUpdate){
            if(!isAccountofArticleAndClientInPosExist(accountDto.getAccountArticleId(),
                    accountDto.getAccountClientId(), accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte existe deja pour cet article et ce client dans " +
                        "le pointofsale indique ", ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        if(articleToUpdate || providerToUpdate){
            if(!isAccountofArticleAndProviderInPosExist(accountDto.getAccountArticleId(),
                    accountDto.getAccountProviderId(), accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte existe deja pour cet article et ce provider dans " +
                        "le pointofsale indique ", ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        if(packagingToUpdate || clientToUpdate){
            if(!isAccountofPackagingAndClientInPos(accountDto.getAccountPackagingId(), accountDto.getAccountClientId(),
                    accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte existe deja pour ce packaging et ce client dans " +
                        "le pointofsale indique ", ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        if(packagingToUpdate || providerToUpdate){
            if (!isAccountofPackagingAndProviderInPos(accountDto.getAccountPackagingId(),
                    accountDto.getAccountProviderId(), accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte existe deja pour ce packaging et ce provider dans le " +
                        "pointofsale indique", ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        if(articleToUpdate){
            if(!isAccountofArticleOfPos(accountDto.getAccountArticleId(), accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte pour cet article dans le pointofsale existe deja",
                        ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        if(packagingToUpdate){
            if(!isAccountofPackagingOfPos(accountDto.getAccountPackagingId(), accountDto.getAccountPosId())){
                throw new DuplicateEntityException("Un compte pour ce packaging existe deja dans le pointofsale " +
                        "indique ", ErrorCode.ACCOUNT_DUPLICATED.name());
            }
        }

        log.info("We continue updating the account by preparing the account to validate");
        accountToUpdate.setAccountArticle(accountDto.getAccountArticleId() == null ? null : articleMapper.dtoToEntity(
                articleService.getArticleById(accountDto.getAccountArticleId())));
        accountToUpdate.setAccountPackaging(accountDto.getAccountPackagingId() == null ? null : packagingMapper.dtoToEntity(
                packagingService.getPackagingById(accountDto.getAccountPackagingId())));
        accountToUpdate.setAccountClient(accountDto.getAccountClientId() == null ? null : clientMapper.dtoToEntity(
                clientService.getClientById(accountDto.getAccountClientId())));
        accountToUpdate.setAccountProvider(accountDto.getAccountProviderId() == null ? null : providerMapper.dtoToEntity(
                providerService.getProviderById(accountDto.getAccountProviderId())));
        accountToUpdate.setAccountPos(posMapper.dtoToEntity(posService.getPointofsaleById(accountDto.getAccountPosId())));
        accountToUpdate.setAccountType(convertToAccountDtoAccountTypeEnum(accountDto.getAccountType()));
        accountToUpdate.setCoverNumber(accountDto.getCoverNumber());
        accountToUpdate.setDamageNumber(accountDto.getDamageNumber());
        accountToUpdate.setPackageNumber(accountDto.getPackageNumber());


        return accountMapper.entityToDto(accountDao.save(accountToUpdate));
    }

    AccountTypeEnum convertToAccountDtoAccountTypeEnum(AccountDto.AccountTypeEnum accountTypeEnum){
        if(accountTypeEnum == null){
            throw new NullValueException("Le accountTypeEnum ne saurait etre null");
        }
        else{
            switch (accountTypeEnum){
                case POS: return AccountTypeEnum.Pos;
                case CLIENT: return AccountTypeEnum.Client;
                case PROVIDER: return AccountTypeEnum.Provider;
                default: throw new EnumNonConvertibleException("Valeur de AccountTypeEnum non reconnu");
            }
        }
    }

    @Override
    public Boolean deleteAccountById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du account a delete ne saurait etre null");
        }
        Optional<Account> optionalAccount = accountDao.findAccountById(id);
        if(!optionalAccount.isPresent()){
            throw new ModelNotFoundException("Aucun Account n'existe avec l'id envoye", ErrorCode.ACCOUNT_NOT_FOUND.name());
        }
        if(!isAccountDeleteable(optionalAccount.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer ce compte sans causer de conflit ",
                    ErrorCode.ACCOUNT_NOT_DELETEABLE.name());
        }
        accountDao.delete(optionalAccount.get());
        return true;
    }

    Boolean isAccountDeleteable(Account account){
        return true;
    }

    @Override
    public AccountDto getAccountById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du account a delete ne saurait etre null");
        }
        Optional<Account> optionalAccount = accountDao.findAccountById(id);
        if(!optionalAccount.isPresent()){
            throw new ModelNotFoundException("Aucun Account n'existe avec l'id envoye", ErrorCode.ACCOUNT_NOT_FOUND.name());
        }
        return accountMapper.entityToDto(optionalAccount.get());
    }

    @Override
    public List<AccountDto> getListofAccount(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return accountMapper.entityToDto(accountDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return accountMapper.entityToDto(accountDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return accountMapper.entityToDto(accountDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Account> accountSpecification = accountSpecService.getAccountSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return accountMapper.entityToDto(accountDao.findAll(accountSpecification));
    }

    @Override
    public PageofAccountDto getPageofAccount(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Account> accountPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            accountPage = accountDao.findAll(pageable);
            return getPageofAccountDto(accountPage);
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
                accountPage = accountDao.findAll(pageable);
                return getPageofAccountDto(accountPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                accountPage = accountDao.findAll(pageable);
                return getPageofAccountDto(accountPage);
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
            Specification<Account> accountSpecification = accountSpecService.getAccountSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            accountPage = accountDao.findAll(accountSpecification, pageable);
            return getPageofAccountDto(accountPage);

        }
    }

    @Override
    public Boolean isAccountExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du account a delete ne saurait etre null");
        }
        Optional<Account> optionalAccount = accountDao.findAccountById(id);
        return optionalAccount.isPresent();
    }

    PageofAccountDto getPageofAccountDto(Page<Account> accountPage){
        PageofAccountDto pageofAccountDto = new PageofAccountDto();
        pageofAccountDto.setContent(accountMapper.entityToDto(accountPage.getContent()));
        pageofAccountDto.setCurrentPage(accountPage.getNumber());
        pageofAccountDto.setPageSize(accountPage.getSize());
        pageofAccountDto.setTotalElements(accountPage.getTotalElements());
        pageofAccountDto.setTotalPages(accountPage.getTotalPages());

        return pageofAccountDto;

    }
}
