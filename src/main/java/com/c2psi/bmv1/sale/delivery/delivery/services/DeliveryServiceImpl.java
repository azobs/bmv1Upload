package com.c2psi.bmv1.sale.delivery.delivery.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.enumerations.DeliveryStateEnum;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.DeliveryDto;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.sale.delivery.delivery.dao.DeliveryDao;
import com.c2psi.bmv1.sale.delivery.delivery.errorCode.ErrorCode;
import com.c2psi.bmv1.sale.delivery.delivery.mappers.DeliveryMapper;
import com.c2psi.bmv1.sale.delivery.delivery.models.Delivery;
import com.c2psi.bmv1.userbm.mappers.UserbmMapper;
import com.c2psi.bmv1.userbm.services.UserbmService;
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

@Service(value = "DeliveryServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService{

    final DeliveryDao deliveryDao;
    final DeliveryValidator deliveryValidator;
    final DeliveryMapper deliveryMapper;
    final DeliverySpecService deliverySpecService;
    final AppService appService;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;
    final UserbmService userbmService;
    final UserbmMapper userbmMapper;

    @Override
    public DeliveryDto saveDelivery(DeliveryDto deliveryDto) {
        log.info("The delivery saving process start by checking if it is not null");
        if(deliveryDto == null){
            throw new NullValueException("Le deliveryDto ne saurait etre null");
        }
        log.info("The delivery saving process continue by validate the deliveryDto");
        List<String> errorsDto = deliveryValidator.validate(deliveryDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le deliveryDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.DELIVERY_NOT_VALID.name());
        }
        log.info("The delivery saving process continue by validate the delivery");
        List<String> errors = deliveryValidator.validate(deliveryMapper.dtoToEntity(deliveryDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le delivery envoye n'est pas valide ", errorsDto,
                    ErrorCode.DELIVERY_NOT_VALID.name());
        }
        log.info("The delivery saving process continue by ensure that the unique constraint is not violated");
        if(!isDeliveryCodeUsableInPos(deliveryDto.getDeliveryCode(), deliveryDto.getDeliveryPosId())){
            throw new DuplicateEntityException("Il existe deja un delivery avec le meme code dans le pointofsale " +
                    "indique ", ErrorCode.DELIVERY_DUPLICATED.name());
        }
        log.info("The delivery saving process continue by preparing the delivery to save");
        //Delivery deliveryToSave = deliveryMapper.dtoToEntity(deliveryDto);
        /***
         * deliveryToUpdate.setDeliveryComment(deliveryDto.getDeliveryComment());
         *         deliveryToUpdate.setDeliveryCode(deliveryDto.getDeliveryCode());
         *         deliveryToUpdate.setDeliveryDate(deliveryDto.getDeliveryDate());
         *         deliveryToUpdate.setDeliveryState(deliveryStateEnumToDeliveryStateEnum(deliveryDto.getDeliveryState()));
         *         deliveryToUpdate.setDeliveryDeliver(userbmMapper.dtoToEntity(userbmService.getUserbmById(
         *                 deliveryDto.getDeliveryDeliverId())));
         *         deliveryToUpdate.setDeliveryPos(posMapper.dtoToEntity(posService.getPointofsaleById(
         *                 deliveryDto.getDeliveryPosId())));
         */
        Delivery deliveryToSave = Delivery.builder()
                .deliveryComment(deliveryDto.getDeliveryComment())
                .deliveryCode(deliveryDto.getDeliveryCode())
                .deliveryDate(deliveryDto.getDeliveryDate())
                .deliveryState(deliveryStateEnumToDeliveryStateEnum(deliveryDto.getDeliveryState()))
                .deliveryDeliver(userbmMapper.dtoToEntity(userbmService.getUserbmById(deliveryDto.getDeliveryDeliverId())))
                .deliveryPos(posMapper.dtoToEntity(posService.getPointofsaleById(deliveryDto.getDeliveryPosId())))
                .build();

        return deliveryMapper.entityToDto(deliveryDao.save(deliveryToSave));
    }

    Boolean isDeliveryCodeUsableInPos(String deliveryCode, Long posId){
        if(deliveryCode == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Delivery> optionalDelivery = deliveryDao.findDeliveryByCodeInPos(deliveryCode, posId);
        return optionalDelivery.isEmpty();
    }

    @Override
    public DeliveryDto updateDelivery(DeliveryDto deliveryDto) {
        log.info("The delivery updating process start by checking if it is not null");
        if(deliveryDto == null){
            throw new NullValueException("Le deliveryDto envoye ne saurait etre null");
        }
        log.info("The delivery updating process continue by checking if the id of the deliveryDto is not null");
        if(deliveryDto.getId() == null){
            throw new NullValueException("L'id du deliveryDto ne saurait etre null");
        }
        log.info("The delivery updating process continue by validate the deliveryDto");
        List<String> errorsDto = deliveryValidator.validate(deliveryDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le deliveryDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.DELIVERY_NOT_VALID.name());
        }
        log.info("The delivery updating process continue by validate the delivery");
        List<String> errors = deliveryValidator.validate(deliveryMapper.dtoToEntity(deliveryDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le delivery envoye n'est pas valide ", errorsDto,
                    ErrorCode.DELIVERY_NOT_VALID.name());
        }
        log.info("The delivery updating process continue by retrieve the delivery to Update");
        Optional<Delivery> optionalDelivery = deliveryDao.findDeliveryById(deliveryDto.getId());
        if(!optionalDelivery.isPresent()){
            throw new ModelNotFoundException("Aucun delivery n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.DELIVERY_NOT_FOUND.name());
        }
        Delivery deliveryToUpdate = optionalDelivery.get();
        log.info("The delivery updating process continue by look what will be update and ensure the unicity of the delivery");
        boolean codeToUpdate = !deliveryToUpdate.getDeliveryCode().equalsIgnoreCase(deliveryDto.getDeliveryCode());
        boolean posToUpdate = !deliveryToUpdate.getDeliveryPos().getId().equals(deliveryDto.getDeliveryPosId());
        if(posToUpdate){
            throw new InvalidEntityException("On ne peut changer le pointofsale du delivery lors du update ",
                    ErrorCode.DELIVERY_NOT_VALID.name());
        }
        if(codeToUpdate){
            if(!isDeliveryCodeUsableInPos(deliveryDto.getDeliveryCode(), deliveryDto.getDeliveryPosId())){
                throw new DuplicateEntityException("Il existe deja un delivery avec ce code pour ce meme pointofsale",
                        ErrorCode.DELIVERY_DUPLICATED.name());
            }
        }
        log.info("The delivery updating process continue by preparing the delivery to update");
        deliveryToUpdate.setDeliveryComment(deliveryDto.getDeliveryComment());
        deliveryToUpdate.setDeliveryCode(deliveryDto.getDeliveryCode());
        deliveryToUpdate.setDeliveryDate(deliveryDto.getDeliveryDate());
        deliveryToUpdate.setDeliveryState(deliveryStateEnumToDeliveryStateEnum(deliveryDto.getDeliveryState()));
        deliveryToUpdate.setDeliveryDeliver(userbmMapper.dtoToEntity(userbmService.getUserbmById(
                deliveryDto.getDeliveryDeliverId())));
        deliveryToUpdate.setDeliveryPos(posMapper.dtoToEntity(posService.getPointofsaleById(
                deliveryDto.getDeliveryPosId())));
        Delivery deliveryUpdated = deliveryDao.save(deliveryToUpdate);

        return deliveryMapper.entityToDto(deliveryUpdated);
    }

    DeliveryStateEnum deliveryStateEnumToDeliveryStateEnum(DeliveryDto.DeliveryStateEnum deliveryStateEnum){
        switch (deliveryStateEnum){
            case INEDITING :
                return DeliveryStateEnum.InEditing;
            case EDITED:
                return DeliveryStateEnum.Edited;
            case DELIVERY:
                return DeliveryStateEnum.Delivery;
            default:
                throw new EnumNonConvertibleException("The sending enum value is not recognized in the system");
        }
    }

    @Override
    public Boolean deleteDeliveryById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du Delivery a delete ne saurait etre null");
        }
        Optional<Delivery> optionalDelivery = deliveryDao.findDeliveryById(id);
        if(!optionalDelivery.isPresent()){
            throw new ModelNotFoundException("Aucun delivery n'existe avec l'id envoye ",
                    ErrorCode.DELIVERY_NOT_FOUND.name());
        }
        if(!isDeliveryDeleteable(optionalDelivery.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer ce delivery sans causer de conflit ",
                    ErrorCode.DELIVERY_NOT_DELETEABLE.name());
        }
        deliveryDao.delete(optionalDelivery.get());
        return true;
    }

    Boolean isDeliveryDeleteable(Delivery delivery){
        return true;
    }

    @Override
    public DeliveryDto getDeliveryById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du Delivery a delete ne saurait etre null");
        }
        Optional<Delivery> optionalDelivery = deliveryDao.findDeliveryById(id);
        if(!optionalDelivery.isPresent()){
            throw new ModelNotFoundException("Aucun delivery n'existe avec l'id envoye ",
                    ErrorCode.DELIVERY_NOT_FOUND.name());
        }

        return deliveryMapper.entityToDto(optionalDelivery.get());
    }

    @Override
    public List<DeliveryDto> getListofDelivery(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return deliveryMapper.entityToDto(deliveryDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return deliveryMapper.entityToDto(deliveryDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return deliveryMapper.entityToDto(deliveryDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Delivery> deliverySpecification = deliverySpecService.getDeliverySpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return deliveryMapper.entityToDto(deliveryDao.findAll(deliverySpecification));
    }

    @Override
    public PageofDeliveryDto getPageofDelivery(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Delivery> deliveryPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            deliveryPage = deliveryDao.findAll(pageable);
            return getPageofDeliveryDto(deliveryPage);
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
                deliveryPage = deliveryDao.findAll(pageable);
                return getPageofDeliveryDto(deliveryPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                deliveryPage = deliveryDao.findAll(pageable);
                return getPageofDeliveryDto(deliveryPage);
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
            Specification<Delivery> deliverySpecification = deliverySpecService.getDeliverySpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            deliveryPage = deliveryDao.findAll(deliverySpecification, pageable);
            return getPageofDeliveryDto(deliveryPage);

        }
    }

    @Override
    public Boolean isDeliveryExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du Delivery a delete ne saurait etre null");
        }
        Optional<Delivery> optionalDelivery = deliveryDao.findDeliveryById(id);
        return optionalDelivery.isPresent();
    }

    PageofDeliveryDto getPageofDeliveryDto(Page<Delivery> deliveryPage){
        PageofDeliveryDto pageofDeliveryDto = new PageofDeliveryDto();
        pageofDeliveryDto.setContent(deliveryMapper.entityToDto(deliveryPage.getContent()));
        pageofDeliveryDto.setCurrentPage(deliveryPage.getNumber());
        pageofDeliveryDto.setPageSize(deliveryPage.getSize());
        pageofDeliveryDto.setTotalElements(deliveryPage.getTotalElements());
        pageofDeliveryDto.setTotalPages(deliveryPage.getTotalPages());

        return pageofDeliveryDto;

    }
}
