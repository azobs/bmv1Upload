package com.c2psi.bmv1.sale.delivery.deliverydetails.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.packaging.packaging.mappers.PackagingMapper;
import com.c2psi.bmv1.packaging.packaging.services.PackagingService;
import com.c2psi.bmv1.sale.delivery.delivery.mappers.DeliveryMapper;
import com.c2psi.bmv1.sale.delivery.delivery.services.DeliveryService;
import com.c2psi.bmv1.sale.delivery.deliverydetails.dao.DeliverydetailsDao;
import com.c2psi.bmv1.sale.delivery.deliverydetails.errorCode.ErrorCode;
import com.c2psi.bmv1.sale.delivery.deliverydetails.mappers.DeliverydetailsMapper;
import com.c2psi.bmv1.sale.delivery.deliverydetails.models.Deliverydetails;
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

@Service(value = "DeliverydetailsServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DeliverydetailsServiceImpl implements DeliverydetailsService{

    final AppService appService;
    final DeliverydetailsDao deliverydetailsDao;
    final DeliverydetailsValidator deliverydetailsValidator;
    final DeliverydetailsSpecService deliverydetailsSpecService;
    final DeliverydetailsMapper deliverydetailsMapper;
    final DeliveryService deliveryService;
    final DeliveryMapper deliveryMapper;
    final PackagingService packagingService;
    final PackagingMapper packagingMapper;

    @Override
    public DeliverydetailsDto saveDeliverydetails(DeliverydetailsDto ddDto) {
        log.info("We start saving the deliverydetails by checking if the ddDto is not null");
        if(ddDto == null){
            throw new NullValueException("The ddDto to save can't be null");
        }
        log.info("We continue saving the deliverydetails by validate the ddDto");
        List<String> errorsDto = deliverydetailsValidator.validate(ddDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("le deliverydetails envoye n'est pas valide ", errorsDto,
                    ErrorCode.DELIVERYDETAILS_NOT_VALID.name());
        }
        log.info("We continue saving the deliverydetails by validate the dd");
        List<String> errors = deliverydetailsValidator.validate(deliverydetailsMapper.dtoToEntity(ddDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("le deliverydetails envoye n'est pas valide ", errors,
                    ErrorCode.DELIVERYDETAILS_NOT_VALID.name());
        }
        log.info("We continue saving the deliverydetails by ensure that dd will be unique");
        if(!isDeliverydetailsUnique(ddDto.getDdPackagingId(), ddDto.getDdDeliveryId())){
            throw new DuplicateEntityException("Il existe deja en BD un deliverydetails pour le meme packaging et le " +
                    "meme delivery ", ErrorCode.DELIVERYDETAILS_DUPLICATED.name());
        }
        log.info("We continue saving the deliverydetails by preparing the dd will to save");
        //Deliverydetails deliverydetailsToSave = deliverydetailsMapper.dtoToEntity(ddDto);
        /***
         * deliverydetailsToUpdate.setDdPackaging(packagingMapper.dtoToEntity(packagingService.getPackagingById(
         *                 ddDto.getDdPackagingId())));
         *         deliverydetailsToUpdate.setDdDelivery(deliveryMapper.dtoToEntity(deliveryService.getDeliveryById(
         *                 ddDto.getDdDeliveryId())));
         *         deliverydetailsToUpdate.setPackageReturn(ddDto.getPackageReturn());
         *         deliverydetailsToUpdate.setPackageUsed(ddDto.getPackageUsed());
         */
        Deliverydetails deliverydetailsToSave = Deliverydetails.builder()
                .ddPackaging(packagingMapper.dtoToEntity(packagingService.getPackagingById(ddDto.getDdPackagingId())))
                .ddDelivery(deliveryMapper.dtoToEntity(deliveryService.getDeliveryById(ddDto.getDdDeliveryId())))
                .packageReturn(ddDto.getPackageReturn())
                .packageUsed(ddDto.getPackageUsed())
                .build();

        return deliverydetailsMapper.entityToDto(deliverydetailsDao.save(deliverydetailsToSave));
    }

    Boolean isDeliverydetailsUnique(Long packagingId, Long deliveryId){
        if(packagingId == null || deliveryId == null){
            throw new NullValueException("les arguments envoyes sont nuls");
        }
        Optional<Deliverydetails> optionalDeliverydetails = deliverydetailsDao.
                findDeliverydetailsByPackagingInDelivery(packagingId, deliveryId);
        return optionalDeliverydetails.isEmpty();
    }

    @Override
    public DeliverydetailsDto updateDeliverydetails(DeliverydetailsDto ddDto) {
        log.info("We start updating the deliverydetails by checking if the ddDto is not null");
        if(ddDto == null){
            throw new NullValueException("Le ddDto envoye ne saurait etre null");
        }
        log.info("We continue updating the deliverydetails by checking if the id of the ddDto is not null");
        if(ddDto.getId() == null){
            throw new NullValueException("L'id du ddDto envoye ne saurait etre null");
        }
        log.info("We continue updating the deliverydetails by validate the ddDto");
        List<String> errorsDto = deliverydetailsValidator.validate(ddDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("le deliverydetails envoye n'est pas valide ", errorsDto,
                    ErrorCode.DELIVERYDETAILS_NOT_VALID.name());
        }
        log.info("We continue updating the deliverydetails by validate the dd");
        List<String> errors = deliverydetailsValidator.validate(deliverydetailsMapper.dtoToEntity(ddDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("le deliverydetails envoye n'est pas valide ", errors,
                    ErrorCode.DELIVERYDETAILS_NOT_VALID.name());
        }
        log.info("We continue updating the deliverydetails by retrieve the deliverydetails to Update");
        Optional<Deliverydetails> optionalDeliverydetails = deliverydetailsDao.findDeliverydetailsById(ddDto.getId());
        if(!optionalDeliverydetails.isPresent()){
            throw new ModelNotFoundException("Aucun deliverydetails n'existe dans le systeme avec l'id envoye",
                    ErrorCode.DELIVERYDETAILS_DUPLICATED.name());
        }
        Deliverydetails deliverydetailsToUpdate = optionalDeliverydetails.get();
        log.info("We continue updating the deliverydetails by ensure that dd will be unique");
        boolean deliveryToUpdate = !deliverydetailsToUpdate.getDdDelivery().getId().equals(ddDto.getDdDeliveryId());
        boolean packagingToUpdate = !deliverydetailsToUpdate.getDdPackaging().getId().equals(ddDto.getDdPackagingId());
        if(deliveryToUpdate && packagingToUpdate){
            if(!isDeliverydetailsUnique(ddDto.getDdPackagingId(), ddDto.getDdDeliveryId())){
                throw new DuplicateEntityException("Il existe deja un deliverydetails avec les memes attributs dans le " +
                        "systeme ", ErrorCode.DELIVERYDETAILS_DUPLICATED.name());
            }
        }
        log.info("We continue updating the deliverydetails by preparing the dd will to update");
        deliverydetailsToUpdate.setDdPackaging(packagingMapper.dtoToEntity(packagingService.getPackagingById(
                ddDto.getDdPackagingId())));
        deliverydetailsToUpdate.setDdDelivery(deliveryMapper.dtoToEntity(deliveryService.getDeliveryById(
                ddDto.getDdDeliveryId())));
        deliverydetailsToUpdate.setPackageReturn(ddDto.getPackageReturn());
        deliverydetailsToUpdate.setPackageUsed(ddDto.getPackageUsed());

        return deliverydetailsMapper.entityToDto(deliverydetailsDao.save(deliverydetailsToUpdate));
    }

    @Override
    public Boolean deleteDeliverydetailsById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du deliverydetails a delete ne saurait etre null");
        }
        Optional<Deliverydetails> optionalDeliverydetails = deliverydetailsDao.findDeliverydetailsById(id);
        if(!optionalDeliverydetails.isPresent()){
            throw new ModelNotFoundException("Aucun deliverydetails n'existe avec l'id envoye ",
                    ErrorCode.DELIVERYDETAILS_NOT_FOUND.name());
        }
        if(!isDeliverydetailsDeleteable(optionalDeliverydetails.get())){
            throw new EntityNotDeleatableException("IL n'est pas possible de supprimer le deliverydetails sans causer " +
                    "de conflit ", ErrorCode.DELIVERYDETAILS_NOT_DELETEABLE.name());
        }
        deliverydetailsDao.delete(optionalDeliverydetails.get());
        return true;
    }

    Boolean isDeliverydetailsDeleteable(Deliverydetails deliverydetails){
        return true;
    }

    @Override
    public DeliverydetailsDto getDeliverydetailsById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du deliverydetails a delete ne saurait etre null");
        }
        Optional<Deliverydetails> optionalDeliverydetails = deliverydetailsDao.findDeliverydetailsById(id);
        if(!optionalDeliverydetails.isPresent()){
            throw new ModelNotFoundException("Aucun deliverydetails n'existe avec l'id envoye ",
                    ErrorCode.DELIVERYDETAILS_NOT_FOUND.name());
        }
        return deliverydetailsMapper.entityToDto(optionalDeliverydetails.get());
    }

    @Override
    public List<DeliverydetailsDto> getListofDeliverydetails(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return deliverydetailsMapper.entityToDto(deliverydetailsDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return deliverydetailsMapper.entityToDto(deliverydetailsDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return deliverydetailsMapper.entityToDto(deliverydetailsDao.findAll(appService.getSortOrders(
                    filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Deliverydetails> deliverydetailsSpecification = deliverydetailsSpecService.
                getDeliverydetailsSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return deliverydetailsMapper.entityToDto(deliverydetailsDao.findAll(deliverydetailsSpecification));
    }

    @Override
    public PageofDeliverydetailsDto getPageofDeliverydetails(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Deliverydetails> deliverydetailsPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if (filterRequest == null) {
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            deliverydetailsPage = deliverydetailsDao.findAll(pageable);
            return getPageofDeliverydetailsDto(deliverydetailsPage);
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
                deliverydetailsPage = deliverydetailsDao.findAll(pageable);
                return getPageofDeliverydetailsDto(deliverydetailsPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if (filterRequest.getFilters() == null && filterRequest.getOrderby() != null) {
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                deliverydetailsPage = deliverydetailsDao.findAll(pageable);
                return getPageofDeliverydetailsDto(deliverydetailsPage);
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
            Specification<Deliverydetails> deliverydetailsSpecification = deliverydetailsSpecService.
                    getDeliverydetailsSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            deliverydetailsPage = deliverydetailsDao.findAll(deliverydetailsSpecification, pageable);
            return getPageofDeliverydetailsDto(deliverydetailsPage);

        }
    }

    @Override
    public Boolean isDeliverydetailsExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du deliverydetails a delete ne saurait etre null");
        }
        Optional<Deliverydetails> optionalDeliverydetails = deliverydetailsDao.findDeliverydetailsById(id);
        return optionalDeliverydetails.isPresent();
    }

    PageofDeliverydetailsDto getPageofDeliverydetailsDto(Page<Deliverydetails> deliverydetailsPage){
        PageofDeliverydetailsDto pageofDeliverydetailsDto = new PageofDeliverydetailsDto();
        pageofDeliverydetailsDto.setContent(deliverydetailsMapper.entityToDto(deliverydetailsPage.getContent()));
        pageofDeliverydetailsDto.setCurrentPage(deliverydetailsPage.getNumber());
        pageofDeliverydetailsDto.setPageSize(deliverydetailsPage.getSize());
        pageofDeliverydetailsDto.setTotalElements(deliverydetailsPage.getTotalElements());
        pageofDeliverydetailsDto.setTotalPages(deliverydetailsPage.getTotalPages());

        return pageofDeliverydetailsDto;

    }


}
