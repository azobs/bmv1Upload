package com.c2psi.bmv1.account.accountoperation.services;

import com.c2psi.bmv1.account.accountoperation.dao.CashOperationDao;
import com.c2psi.bmv1.account.accountoperation.errorCode.ErrorCode;
import com.c2psi.bmv1.account.accountoperation.mappers.CashOperationMapper;
import com.c2psi.bmv1.account.accountoperation.models.CashOperation;
import com.c2psi.bmv1.account.operation.mappers.OperationMapper;
import com.c2psi.bmv1.account.operation.services.OperationService;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.enumerations.AmountLocationEnum;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.client.client.mappers.ClientMapper;
import com.c2psi.bmv1.client.client.services.ClientService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.PageofCashOperationDto;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
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

@Service(value = "CashOperationServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CashOperationServiceImpl implements  CashOperationService{
    final AppService appService;
    final CashOperationValidator cashOperationValidator;
    final CashOperationMapper cashOperationMapper;
    final CashOperationDao cashOperationDao;
    final CashOperationSpecService cashOperationSpecService;
    final OperationService operationService;
    final OperationMapper operationMapper;
    final PointofsaleMapper posMapper;
    final PointofsaleService posService;
    final ClientService clientService;
    final ClientMapper clientMapper;
    final ProviderService providerService;
    final ProviderMapper providerMapper;

    @Override
    public CashOperationDto saveCashOperation(CashOperationDto cashOperationDto) {
        if(cashOperationDto == null){
            throw new NullValueException("Le cashOperationDto envoye ne saurait etre null");
        }
        List<String> errorsDto = cashOperationValidator.validate(cashOperationDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le cashOperationDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.CASHOPERATION_NOT_VALID.name());
        }
        List<String> errors = cashOperationValidator.validate(cashOperationMapper.dtoToEntity(cashOperationDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le cashOperationDto envoye n'est pas valide ", errors,
                    ErrorCode.CASHOPERATION_NOT_VALID.name());
        }
        if(cashOperationDto.getClientConcernedId() != null){
            if(!isCashOperationofClientUniqueInPos(cashOperationDto.getClientConcernedId(),
                    cashOperationDto.getOperationId(), cashOperationDto.getPosConcernedId())){
                throw new DuplicateEntityException("Cette operation du client existe deja dans le systeme ",
                        ErrorCode.CASHOPERATION_DUPLICATED.name());
            }
        }

        if(cashOperationDto.getProviderConcernedId() != null){
            if(!isCashOperationofProviderUniqueInPos(cashOperationDto.getProviderConcernedId(),
                    cashOperationDto.getOperationId(), cashOperationDto.getPosConcernedId())){
                throw new DuplicateEntityException("Cette operation du provider existe deja dans le systeme ",
                        ErrorCode.CASHOPERATION_DUPLICATED.name());
            }
        }

        if(cashOperationDto.getClientConcernedId() == null && cashOperationDto.getProviderConcernedId() == null){
            if(!isCashOperationUniqueforPos(cashOperationDto.getOperationId(), cashOperationDto.getPosConcernedId())){
                throw new DuplicateEntityException("Cette operation concernant le pointofsale lui meme existe deja dans le systeme ",
                        ErrorCode.CASHOPERATION_DUPLICATED.name());
            }
        }

        //CashOperation cashOperationToSave = cashOperationMapper.dtoToEntity(cashOperationDto);
        /******
         * cashOperationToUpdate.setAmountinMvt(cashOperationDto.getAmountinMvt());
         *         cashOperationToUpdate.setAmountSource(convertToAmountLocationEnum(cashOperationDto.getAmountSource(),
         *                 null));
         *         cashOperationToUpdate.setAmountDestination(convertToAmountLocationEnum(null,
         *                 cashOperationDto.getAmountDestination()));
         *         cashOperationToUpdate.setOperation(operationMapper.dtoToEntity(operationService.getOperationById(
         *                 cashOperationDto.getOperationId())));
         *         cashOperationToUpdate.setPosConcerned(posMapper.dtoToEntity(posService.getPointofsaleById(
         *                 cashOperationDto.getPosConcernedId())));
         *         cashOperationToUpdate.setClientConcerned(cashOperationDto.getClientConcernedId()!=null ?
         *                 clientMapper.dtoToEntity(clientService.getClientById(cashOperationDto.getClientConcernedId())): null);
         *         cashOperationToUpdate.setProviderConcerned(cashOperationDto.getProviderConcernedId()!=null ?
         *                 providerMapper.dtoToEntity(providerService.getProviderById(cashOperationDto.getProviderConcernedId())):
         *                 null);
         */
        CashOperation cashOperationToSave = CashOperation.builder()
                .amountinMvt(cashOperationDto.getAmountinMvt())
                .amountSource(convertToAmountLocationEnum(cashOperationDto.getAmountSource(), null))
                .amountDestination(convertToAmountLocationEnum(null, cashOperationDto.getAmountDestination()))
                .operation(operationMapper.dtoToEntity(operationService.getOperationById(cashOperationDto.getOperationId())))
                .posConcerned(posMapper.dtoToEntity(posService.getPointofsaleById(cashOperationDto.getPosConcernedId())))
                .clientConcerned(cashOperationDto.getClientConcernedId()!=null ?
                        clientMapper.dtoToEntity(clientService.getClientById(cashOperationDto.getClientConcernedId())): null)
                .providerConcerned(cashOperationDto.getProviderConcernedId()!=null ?
                        providerMapper.dtoToEntity(providerService.getProviderById(cashOperationDto.getProviderConcernedId())): null)
                .build();

        return cashOperationMapper.entityToDto(cashOperationDao.save(cashOperationToSave));
    }

    Boolean isCashOperationUniqueforPos(Long operationId, Long posId){
        if(operationId == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<CashOperation> optionalCashOperation = cashOperationDao.findCashOperationByOperationAndPos(operationId,
                posId);
        return optionalCashOperation.isEmpty();
    }

    Boolean isCashOperationofClientUniqueInPos(Long clientId, Long operationId, Long posId){
        if(clientId == null || operationId == null || posId == null){
            throw new NullValueException("les arguments envoyes sont nuls");
        }
        Optional<CashOperation> optionalCashOperation = cashOperationDao.findCashOperationByOperationOfClientInPos(
                clientId, operationId, posId);
        return optionalCashOperation.isEmpty();
    }

    Boolean isCashOperationofProviderUniqueInPos(Long providerId, Long operationId, Long posId){
        if(providerId == null || operationId == null || posId == null){
            throw new NullValueException("les arguments envoyes sont nuls");
        }
        Optional<CashOperation> optionalCashOperation = cashOperationDao.findCashOperationByOperationOfProviderInPos(
                providerId, operationId, posId);
        return optionalCashOperation.isEmpty();
    }

    @Override
    public CashOperationDto updateCashOperation(CashOperationDto cashOperationDto) {
        if(cashOperationDto == null){
            throw new NullValueException("Le cashoperationDto envoye ne saurait etre null dans le update");
        }
        if(cashOperationDto.getId() == null){
            throw new NullValueException("Le Id du cashoperationDto ne saurait etre null dans le update");
        }
        List<String> errorsDto = cashOperationValidator.validate(cashOperationDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le cashOperationDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.CASHOPERATION_NOT_VALID.name());
        }
        List<String> errors = cashOperationValidator.validate(cashOperationMapper.dtoToEntity(cashOperationDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le cashOperationDto envoye n'est pas valide ", errors,
                    ErrorCode.CASHOPERATION_NOT_VALID.name());
        }

        Optional<CashOperation> optionalCashOperation = cashOperationDao.findCashOperationById(cashOperationDto.getId());
        if(!optionalCashOperation.isPresent()){
            throw new ModelNotFoundException("Aucun cashoperation n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.CASHOPERATION_NOT_FOUND.name());
        }
        CashOperation cashOperationToUpdate = optionalCashOperation.get();
        boolean opChange = !cashOperationToUpdate.getOperation().getId().equals(cashOperationDto.getOperationId());
        boolean posChange = !cashOperationToUpdate.getPosConcerned().getId().equals(cashOperationDto.getPosConcernedId());
        boolean clientChange = cashOperationDto.getClientConcernedId() != null ? (
                cashOperationToUpdate.getClientConcerned() != null ?
                        !cashOperationDto.getClientConcernedId().equals(
                                cashOperationToUpdate.getClientConcerned().getId()): false): false;
        boolean providerChange = cashOperationDto.getProviderConcernedId() != null ? (
                cashOperationToUpdate.getProviderConcerned() != null ?
                        !cashOperationDto.getProviderConcernedId().equals(
                                cashOperationToUpdate.getProviderConcerned().getId()): false): false;


        if(opChange || posChange){
            if(clientChange){
                if(!isCashOperationofClientUniqueInPos(cashOperationDto.getClientConcernedId(),
                        cashOperationDto.getOperationId(), cashOperationDto.getPosConcernedId())){
                    throw new DuplicateEntityException("Cette operation du client existe deja dans le systeme ",
                            ErrorCode.CASHOPERATION_DUPLICATED.name());
                }
            }

            if(providerChange){
                if(!isCashOperationofProviderUniqueInPos(cashOperationDto.getProviderConcernedId(),
                        cashOperationDto.getOperationId(), cashOperationDto.getPosConcernedId())){
                    throw new DuplicateEntityException("Cette operation du client existe deja dans le systeme ",
                            ErrorCode.CASHOPERATION_DUPLICATED.name());
                }
            }
        }

        cashOperationToUpdate.setAmountinMvt(cashOperationDto.getAmountinMvt());
        cashOperationToUpdate.setAmountSource(convertToAmountLocationEnum(cashOperationDto.getAmountSource(),
                null));
        cashOperationToUpdate.setAmountDestination(convertToAmountLocationEnum(null,
                cashOperationDto.getAmountDestination()));
        cashOperationToUpdate.setOperation(operationMapper.dtoToEntity(operationService.getOperationById(
                cashOperationDto.getOperationId())));
        cashOperationToUpdate.setPosConcerned(posMapper.dtoToEntity(posService.getPointofsaleById(
                cashOperationDto.getPosConcernedId())));
        cashOperationToUpdate.setClientConcerned(cashOperationDto.getClientConcernedId()!=null ?
                clientMapper.dtoToEntity(clientService.getClientById(cashOperationDto.getClientConcernedId())): null);
        cashOperationToUpdate.setProviderConcerned(cashOperationDto.getProviderConcernedId()!=null ?
                providerMapper.dtoToEntity(providerService.getProviderById(cashOperationDto.getProviderConcernedId())):
                null);

        return cashOperationMapper.entityToDto(cashOperationDao.save(cashOperationToUpdate));
    }

    AmountLocationEnum convertToAmountLocationEnum (CashOperationDto.AmountSourceEnum amountSourceEnum,
                                                    CashOperationDto.AmountDestinationEnum amountDestinationEnum){
        if(amountSourceEnum == null && amountDestinationEnum == null){
            throw new NullValueException("les arguments envoyes sont nuls");
        }
        if(amountSourceEnum != null && amountDestinationEnum == null){
            switch (amountSourceEnum){
                case CASH_DESK: return AmountLocationEnum.CASH_DESK;
                case OM_ACCOUNT: return AmountLocationEnum.OM_ACCOUNT;
                case MOMO_ACCOUNT: return AmountLocationEnum.MOMO_ACCOUNT;
                default: throw new EnumNonConvertibleException("Valeur de AmountLocationEnum non reconnu dans le systeme");
            }
        }
        if(amountSourceEnum == null && amountDestinationEnum != null){
            switch (amountDestinationEnum){
                case CASH_DESK: return AmountLocationEnum.CASH_DESK;
                case OM_ACCOUNT: return AmountLocationEnum.OM_ACCOUNT;
                case MOMO_ACCOUNT: return AmountLocationEnum.MOMO_ACCOUNT;
                default: throw new EnumNonConvertibleException("Valeur de AmountLocationEnum non reconnu dans le systeme");
            }
        }
        throw new InvalidEntityException("Les arguments envoyes ne sont pas valide");
    }

    @Override
    public Boolean deleteCashOperationById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du cashOperation a delete ne saurait etre null");
        }
        Optional<CashOperation> optionalCashOperation = cashOperationDao.findCashOperationById(id);
        if(!optionalCashOperation.isPresent()){
            throw new ModelNotFoundException("Aucun CashOperation n'existe avec l'id envoye ",
                    ErrorCode.CASHOPERATION_NOT_FOUND.name());
        }
        if(!isCashOperationDeleteable(optionalCashOperation.get())){
            throw new EntityNotDeleatableException("on ne peut supprimer le cashoperation ",
                    ErrorCode.CASHOPERATION_NOT_DELETEABLE.name());
        }
        cashOperationDao.delete(optionalCashOperation.get());
        return true;
    }

    Boolean isCashOperationDeleteable(CashOperation cashOperation){
        return true;
    }

    @Override
    public CashOperationDto getCashOperationById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du cashOperation a delete ne saurait etre null");
        }
        Optional<CashOperation> optionalCashOperation = cashOperationDao.findCashOperationById(id);
        if(!optionalCashOperation.isPresent()){
            throw new ModelNotFoundException("Aucun CashOperation n'existe avec l'id envoye ",
                    ErrorCode.CASHOPERATION_NOT_FOUND.name());
        }
        return cashOperationMapper.entityToDto(optionalCashOperation.get());
    }

    @Override
    public List<CashOperationDto> getListofCashOperation(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return cashOperationMapper.entityToDto(cashOperationDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return cashOperationMapper.entityToDto(cashOperationDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return cashOperationMapper.entityToDto(cashOperationDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<CashOperation> cashOperationSpecification = cashOperationSpecService.getCashOperationSpecification(
                filterRequest.getFilters(), filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return cashOperationMapper.entityToDto(cashOperationDao.findAll(cashOperationSpecification));
    }

    @Override
    public PageofCashOperationDto getPageofCashOperation(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<CashOperation> cashOperationPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if (filterRequest == null) {
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            cashOperationPage = cashOperationDao.findAll(pageable);
            return getPageofCashOperationDto(cashOperationPage);
        } else {
            /*************************************************************************************
             * Si le filterRequest envoye n'est pas null mais que l'element pas indiquant le numero
             * et la taille de page voulu est null alors on assigne des valeurs par defaut soit
             * page numero 0 et taille de page 10
             */
            if (filterRequest.getPage() == null) {
                pagebm.setPagenum(0);
                pagebm.setPagesize(10);
                filterRequest.setPage(pagebm);
            }

            /**************************************************************************************
             * Si dans le filterRequest envoye les filtres et les elements de tri sont null alors
             * on retourne le findAll page par page.
             */

            if (filterRequest.getFilters() == null && filterRequest.getOrderby() == null) {
                Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
                cashOperationPage = cashOperationDao.findAll(pageable);
                return getPageofCashOperationDto(cashOperationPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if (filterRequest.getFilters() == null && filterRequest.getOrderby() != null) {
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                cashOperationPage = cashOperationDao.findAll(pageable);
                return getPageofCashOperationDto(cashOperationPage);
            }

            /*********************************************************************************************
             * Si l'operateur logique permettant de lier les filtres est null et que la liste des filtres
             * contient plus d'un filtre alors il ya un probleme dans les parametres
             */
            if (filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1) {
                throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
            }

            /****************************************************************************************************
             * On peut ici lancer une recherche selon les filtres envoyes, les classer selon les elements de tri
             * et ensuite la page demande
             */
            Specification<CashOperation> cashOperationSpecification = cashOperationSpecService.getCashOperationSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            cashOperationPage = cashOperationDao.findAll(cashOperationSpecification, pageable);
            return getPageofCashOperationDto(cashOperationPage);

        }
    }

    @Override
    public Boolean isCashOperationExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du cashOperation a delete ne saurait etre null");
        }
        Optional<CashOperation> optionalCashOperation = cashOperationDao.findCashOperationById(id);
        return optionalCashOperation.isPresent();
    }

    PageofCashOperationDto getPageofCashOperationDto(Page<CashOperation> cashOperationPage){
        PageofCashOperationDto pageofCashOperationDto = new PageofCashOperationDto();
        pageofCashOperationDto.setContent(cashOperationMapper.entityToDto(cashOperationPage.getContent()));
        pageofCashOperationDto.setCurrentPage(cashOperationPage.getNumber());
        pageofCashOperationDto.setPageSize(cashOperationPage.getSize());
        pageofCashOperationDto.setTotalElements(cashOperationPage.getTotalElements());
        pageofCashOperationDto.setTotalPages(cashOperationPage.getTotalPages());

        return pageofCashOperationDto;

    }
}
