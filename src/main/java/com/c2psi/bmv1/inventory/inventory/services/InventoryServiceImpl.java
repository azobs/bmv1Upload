package com.c2psi.bmv1.inventory.inventory.services;

import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.inventory.inventory.dao.InventoryDao;
import com.c2psi.bmv1.inventory.inventory.errorCode.ErrorCode;
import com.c2psi.bmv1.inventory.inventory.mappers.InventoryMapper;
import com.c2psi.bmv1.inventory.inventory.models.Inventory;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
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

@Service(value = "InventoryServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{

    final InventoryDao invDao;
    final InventoryValidator invValidator;
    final InventorySpecService invSpecService;
    final InventoryMapper invMapper;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;
    final AppService appService;

    @Override
    public InventoryDto saveInventory(InventoryDto invDto) {
        log.info("We start saving an inventory by checking if inventoryDto is not null");
        if(invDto == null){
            throw new NullValueException("L'inventoryDto envoye ne saurait etre null");
        }
        log.info("We continue saving an inventory by validating the inventoryDto sent");
        List<String> errorsDto = invValidator.validate(invDto);
        if(!errorsDto.isEmpty() ){
            throw new InvalidEntityException("L'inventoryDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.INVENTORY_NOT_VALID.name());
        }
        log.info("We continue saving an inventory by validating the inventory associate to the inventoryDto");
        List<String> errors = invValidator.validate(invMapper.dtoToEntity(invDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("L'inventory envoye n'est pas valide ", errors,
                    ErrorCode.INVENTORY_NOT_VALID.name());
        }
        log.info("We continue saving an inventory by make sure that the unique constraint will not be violated");

        if(invDto.getInvCode() != null){
            if(!isInventoryCodeUsableInPos(invDto.getInvCode(), invDto.getInvPosId())){
                throw new DuplicateEntityException("Il existe deja un inventory avec le meme code et pour le meme " +
                        "pointofsale ", ErrorCode.INVENTORY_DUPLICATED.name());
            }
        }
        log.info("We continue saving an inventory by preparing the inventory to save");

        Inventory inventoryToSave = invMapper.dtoToEntity(invDto);

        return invMapper.entityToDto(invDao.save(inventoryToSave));
    }

    Boolean isInventoryCodeUsableInPos(String invCode, Long posId){
        if(invCode == null || posId == null){
            throw new NullValueException("The argument sent is null ");
        }
        Optional<Inventory> optionalInventory = invDao.findInventoryByCodeInPos(invCode, posId);
        return optionalInventory.isEmpty();
    }

    @Override
    public InventoryDto updateInventory(InventoryDto invDto) {
        log.info("We start updating an inventory by checking if the invDto sent is not null");
        if(invDto == null){
            throw new NullValueException("L'invDto envoye en argument est null");
        }
        log.info("We continue the updating process by validate the invDto sent");
        List<String> errorsDto = invValidator.validate(invDto);
        if(!errorsDto.isEmpty() ){
            throw new InvalidEntityException("L'inventoryDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.INVENTORY_NOT_VALID.name());
        }
        log.info("We continue updating an inventory by validating the inventory associate to the inventoryDto");
        List<String> errors = invValidator.validate(invMapper.dtoToEntity(invDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("L'inventory envoye n'est pas valide ", errors,
                    ErrorCode.INVENTORY_NOT_VALID.name());
        }
        log.info("We continue updating an inventory by retrieving the inventory to update");
        Optional<Inventory> optionalInventory = invDao.findInventoryById(invDto.getId());
        if(!optionalInventory.isPresent()){
            throw new ModelNotFoundException("Aucun inventory n'existe dans le systeme avec l'id envoye",
                    ErrorCode.INVENTORY_NOT_FOUND.name());
        }
        Inventory invToUpdate = optionalInventory.get();
        log.info("We continue updating an inventory by make sur that if the invCode is different the constraint will " +
                "stay non violated");
        if(invDto.getInvCode() != null){
            if(invToUpdate.getInvCode() != null){
                if(!invDto.getInvCode().equals(invToUpdate.getInvCode())){
                    if(!isInventoryCodeUsableInPos(invDto.getInvCode(), invDto.getInvPosId())){
                        throw new DuplicateEntityException("Il existe deja un Inventory dans le systeme avec ce meme " +
                                "code pour ce pointofsale ", ErrorCode.INVENTORY_DUPLICATED.name());
                    }
                }
            }
        }
        log.info("We continue updating an inventory by make sur that if the pos will not change ");
        if(!invDto.getInvPosId().equals(invToUpdate.getInvPos().getId())){
            throw new InvalidEntityException("On ne peut changer le pointofsale d'un inventory ",
                    ErrorCode.INVENTORY_NOT_VALID.name());
        }
        log.info("We continue updating an inventory by preparing the inventory to update");

        invToUpdate.setInvCode(invDto.getInvCode());
        invToUpdate.setInvComment(invDto.getInvComment());
        invToUpdate.setInvDate(invDto.getInvDate());
        invToUpdate.setInvPos(posMapper.dtoToEntity(posService.getPointofsaleById(invDto.getInvPosId())));
        log.info("We continue updating an inventory by making the update");

        return invMapper.entityToDto(invDao.save(invToUpdate));
    }

    @Override
    public Boolean deleteInventoryById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'inventory a delete ne saurait etre null");
        }
        Optional<Inventory> optionalInventory = invDao.findInventoryById(id);
        if(!optionalInventory.isPresent()){
            throw new ModelNotFoundException("Aucun inventory n'existe avec l'id envoye ",
                    ErrorCode.INVENTORY_NOT_FOUND.name());
        }
        if(!isInventoryDeleteable(optionalInventory.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer l'inventory indique sans cause de conflit ",
                    ErrorCode.INVENTORY_NOT_DELETEABLE.name());
        }
        invDao.delete(optionalInventory.get());
        return true;
    }

    Boolean isInventoryDeleteable(Inventory inventory){
        return true;
    }

    @Override
    public InventoryDto getInventoryById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'inventory a delete ne saurait etre null");
        }
        Optional<Inventory> optionalInventory = invDao.findInventoryById(id);
        if(!optionalInventory.isPresent()){
            throw new ModelNotFoundException("Aucun inventory n'existe avec l'id envoye ",
                    ErrorCode.INVENTORY_NOT_FOUND.name());
        }
        return invMapper.entityToDto(optionalInventory.get());
    }

    @Override
    public List<InventoryDto> getListofInventory(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return invMapper.entityToDto(invDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return invMapper.entityToDto(invDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return invMapper.entityToDto(invDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Inventory> invSpecification = invSpecService.getInventorySpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return invMapper.entityToDto(invDao.findAll(invSpecification));
    }

    @Override
    public PageofInventoryDto getPageofInventory(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Inventory> invPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            invPage = invDao.findAll(pageable);
            return getPageofInventoryDto(invPage);
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
                invPage = invDao.findAll(pageable);
                return getPageofInventoryDto(invPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                invPage = invDao.findAll(pageable);
                return getPageofInventoryDto(invPage);
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
            Specification<Inventory> invSpecification = invSpecService.getInventorySpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            invPage = invDao.findAll(invSpecification, pageable);
            return getPageofInventoryDto(invPage);

        }
    }

    @Override
    public Boolean isInventoryExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'inventory a delete ne saurait etre null");
        }
        Optional<Inventory> optionalInventory = invDao.findInventoryById(id);
        return optionalInventory.isPresent();
    }

    PageofInventoryDto getPageofInventoryDto(Page<Inventory> invPage){
        PageofInventoryDto pageofInventoryDto = new PageofInventoryDto();
        pageofInventoryDto.setContent(invMapper.entityToDto(invPage.getContent()));
        pageofInventoryDto.setCurrentPage(invPage.getNumber());
        pageofInventoryDto.setPageSize(invPage.getSize());
        pageofInventoryDto.setTotalElements(invPage.getTotalElements());
        pageofInventoryDto.setTotalPages(invPage.getTotalPages());

        return pageofInventoryDto;

    }
}
