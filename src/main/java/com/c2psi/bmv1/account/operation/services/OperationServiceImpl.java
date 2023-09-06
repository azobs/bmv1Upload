package com.c2psi.bmv1.account.operation.services;

import com.c2psi.bmv1.account.operation.dao.OperationDao;
import com.c2psi.bmv1.account.operation.errorCode.ErrorCode;
import com.c2psi.bmv1.account.operation.mappers.OperationMapper;
import com.c2psi.bmv1.account.operation.models.Operation;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.enumerations.OpTypeEnum;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
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

@Service(value = "OperationServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OperationServiceImpl implements OperationService{
    final AppService appService;
    final OperationDao operationDao;
    final OperationValidator operationValidator;
    final OperationMapper operationMapper;
    final OperationSpecService operationSpecService;

    @Override
    public OperationDto saveOperation(OperationDto operationDto) {
        if(operationDto == null){
            throw new NullValueException("Le operationDto envoye ne saurait etre null");
        }
        List<String> errorsDto = operationValidator.validate(operationDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("L'operationDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.OPERATION_NOT_VALID.name());
        }
        List<String> errors = operationValidator.validate(operationMapper.dtoToEntity(operationDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("L'operation envoye n'est pas valide ", errors,
                    ErrorCode.OPERATION_NOT_VALID.name());
        }
        if(!isOperationNonUnique()){
            throw new DuplicateEntityException("Il existe deja cette operation dans le systeme",
                    ErrorCode.OPERATION_DUPLICATED.name());
        }
        //Operation operationToSave = operationMapper.dtoToEntity(operationDto);
        /********
         * operationToUpdate.setOpDate(operationDto.getOpDate());
         *         operationToUpdate.setOpObject(operationDto.getOpObject());
         *         operationToUpdate.setOpDescription(operationDto.getOpDescription());
         *         operationToUpdate.setOpType(convertToOperationDtoOpTypeEnum(operationDto.getOpType()));
         */
        Operation operationToSave = Operation.builder()
                .opDate(operationDto.getOpDate())
                .opObject(operationDto.getOpObject())
                .opDescription(operationDto.getOpDescription())
                .opType(convertToOperationDtoOpTypeEnum(operationDto.getOpType()))
                .build();

        return operationMapper.entityToDto(operationDao.save(operationToSave));
    }

    Boolean isOperationNonUnique(){
        return true;
    }

    @Override
    public OperationDto updateOperation(OperationDto operationDto) {
        if(operationDto == null){
            throw new NullValueException("Le operationDto envoye ne saurait etre null");
        }
        if(operationDto.getId() == null){
            throw new NullValueException("l'Id de l'operationDto envoye ne saurait etre null");
        }
        List<String> errorsDto = operationValidator.validate(operationDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("L'operationDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.OPERATION_NOT_VALID.name());
        }
        List<String> errors = operationValidator.validate(operationMapper.dtoToEntity(operationDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("L'operation envoye n'est pas valide ", errors,
                    ErrorCode.OPERATION_NOT_VALID.name());
        }
        Optional<Operation> optionalOperation = operationDao.findOperationById(operationDto.getId());
        if(!optionalOperation.isPresent()){
            throw new ModelNotFoundException("Aucune operation n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.OPERATION_NOT_FOUND.name());
        }
        Operation operationToUpdate = optionalOperation.get();
        operationToUpdate.setOpDate(operationDto.getOpDate());
        operationToUpdate.setOpObject(operationDto.getOpObject());
        operationToUpdate.setOpDescription(operationDto.getOpDescription());
        operationToUpdate.setOpType(convertToOperationDtoOpTypeEnum(operationDto.getOpType()));
        /*******
         * On se rassure que si un element de la cle unique est modifier dans le dto alors on se rassure que
         * cette contraite restera inviole
         */

        return operationMapper.entityToDto(operationDao.save(operationToUpdate));
    }

    OpTypeEnum convertToOperationDtoOpTypeEnum (OperationDto.OpTypeEnum opTypeEnum){
        if(opTypeEnum == null){
            throw new NullValueException("Le opTypeEnum envoye ne saurait etre null");
        }
        switch (opTypeEnum){
            case CHANGE: return OpTypeEnum.Change;
            case CREDIT: return OpTypeEnum.Credit;
            case OTHERS: return OpTypeEnum.Others;
            case WITHDRAWAL: return OpTypeEnum.Withdrawal;
            default: throw new EnumNonConvertibleException("Valeur de type d'operation non reconnu dans le systeme");
        }
    }

    @Override
    public Boolean deleteOperationById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'operation envoye ne saurait etre null");
        }
        Optional<Operation> optionalOperation = operationDao.findOperationById(id);
        if(!optionalOperation.isPresent()){
            throw new ModelNotFoundException("Aucune operation n'existe avec l'id envoye ",
                    ErrorCode.OPERATION_NOT_FOUND.name());
        }

        if(!isOperationDeleteable(optionalOperation.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer cette operation ",
                    ErrorCode.OPERATION_NOT_DELETEABLE.name());
        }
        operationDao.delete(optionalOperation.get());
        return true;
    }

    Boolean isOperationDeleteable(Operation operation){
        return true;
    }

    @Override
    public OperationDto getOperationById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'operation envoye ne saurait etre null");
        }
        Optional<Operation> optionalOperation = operationDao.findOperationById(id);
        if(!optionalOperation.isPresent()){
            throw new ModelNotFoundException("Aucune operation n'existe avec l'id envoye ",
                    ErrorCode.OPERATION_NOT_FOUND.name());
        }
        return operationMapper.entityToDto(optionalOperation.get());
    }

    @Override
    public List<OperationDto> getListofOperation(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return operationMapper.entityToDto(operationDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return operationMapper.entityToDto(operationDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return operationMapper.entityToDto(operationDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Operation> operationSpecification = operationSpecService.getOperationSpecification(
                filterRequest.getFilters(), filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return operationMapper.entityToDto(operationDao.findAll(operationSpecification));
    }

    @Override
    public PageofOperationDto getPageofOperation(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Operation> operationPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            operationPage = operationDao.findAll(pageable);
            return getPageofOperationDto(operationPage);
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
                operationPage = operationDao.findAll(pageable);
                return getPageofOperationDto(operationPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                operationPage = operationDao.findAll(pageable);
                return getPageofOperationDto(operationPage);
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
            Specification<Operation> operationSpecification = operationSpecService.getOperationSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            operationPage = operationDao.findAll(operationSpecification, pageable);
            return getPageofOperationDto(operationPage);

        }
    }

    @Override
    public Boolean isOperationExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'operation envoye ne saurait etre null");
        }
        Optional<Operation> optionalOperation = operationDao.findOperationById(id);

        return optionalOperation.isPresent();
    }

    PageofOperationDto getPageofOperationDto(Page<Operation> operationPage){
        PageofOperationDto pageofOperationDto = new PageofOperationDto();
        pageofOperationDto.setContent(operationMapper.entityToDto(operationPage.getContent()));
        pageofOperationDto.setCurrentPage(operationPage.getNumber());
        pageofOperationDto.setPageSize(operationPage.getSize());
        pageofOperationDto.setTotalElements(operationPage.getTotalElements());
        pageofOperationDto.setTotalPages(operationPage.getTotalPages());

        return pageofOperationDto;

    }

}
