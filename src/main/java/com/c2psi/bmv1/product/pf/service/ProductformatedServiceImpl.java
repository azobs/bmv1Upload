package com.c2psi.bmv1.product.pf.service;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.product.format.mapper.FormatMapper;
import com.c2psi.bmv1.product.format.service.FormatService;
import com.c2psi.bmv1.product.pf.dao.ProductformatedDao;
import com.c2psi.bmv1.product.pf.errorCode.ErrorCode;
import com.c2psi.bmv1.product.pf.mapper.ProductformatedMapper;
import com.c2psi.bmv1.product.pf.models.Productformated;
import com.c2psi.bmv1.product.product.mapper.ProductMapper;
import com.c2psi.bmv1.product.product.service.ProductService;
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

@Service(value = "ProductformatedServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductformatedServiceImpl implements ProductformatedService{

    final AppService appService;
    final FormatService formatService;
    final FormatMapper formatMapper;
    final ProductService productService;
    final ProductMapper productMapper;
    final ProductformatedDao pfDao;
    final ProductformatedMapper pfMapper;
    final ProductformatedSpecService pfSpecService;
    final ProductformatedValidator pfValidator;


    @Override
    public ProductformatedDto saveProductformated(ProductformatedDto pfDto) {
        if(pfDto == null){
            throw new NullValueException("The pfDto sent can't be null");
        }
        List<String> errorsDto = pfValidator.validate(pfDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le pfDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.PRODUCTFORMATED_NOT_VALID.name());
        }

        List<String> errors = pfValidator.validate(pfMapper.dtoToEntity(pfDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le pf envoye n'est pas valide ", errors,
                    ErrorCode.PRODUCTFORMATED_NOT_VALID.name());
        }

        if(!isPfAttributesUsable(pfDto.getPfProductId(), pfDto.getPfFormatId())){
            throw new DuplicateEntityException("Il existe deja un Product formated dans le systeme avec les memes " +
                    "attributs ", ErrorCode.PRODUCTFORMATED_DUPLICATED.name());
        }

//        Productformated pfToSaved = Productformated.builder()
//                .pfPicture(pfDto.getPfPicture())
//                .pfProduct(productMapper.dtoToEntity(productService.getProductById(pfDto.getPfProductId())))
//                .pfFormat(formatMapper.dtoToEntity(formatService.getFormatById(pfDto.getPfFormatId())))
//                .build();
        Productformated pfToSaved = pfMapper.dtoToEntity(pfDto);

        Productformated pfSaved = pfDao.save(pfToSaved);

        return pfMapper.entityToDto(pfSaved);
    }

    Boolean isPfAttributesUsable(Long productId, Long formatId){
        if(productId == null || formatId == null){
            throw new NullValueException("les arguments envoye sont nuls");
        }
        Optional<Productformated> optionalProductformated = pfDao.findProductformatedByAttributes(productId, formatId);
        return optionalProductformated.isEmpty();
    }

    @Override
    public ProductformatedDto updateProductformated(ProductformatedDto pfDto) {
        if(pfDto == null){
            throw new NullValueException("Le pfDto envoye est null");
        }
        if(pfDto.getId() == null){
            throw new NullValueException("L'id du pfDto a update ne saurait etre null");
        }
        Optional<Productformated> optionalProductformated = pfDao.findProductformatedById(pfDto.getId());
        if(!optionalProductformated.isPresent()){
            throw new ModelNotFoundException("Aucun Productformated n'existe dans le systeme avec l'id envoye",
                    ErrorCode.PRODUCTFORMATED_NOT_FOUND.name());
        }
        Productformated pfToUpdate = optionalProductformated.get();

        List<String> errorsDto = pfValidator.validate(pfDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le pfDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.PRODUCTFORMATED_NOT_VALID.name());
        }

        List<String> errors = pfValidator.validate(pfMapper.dtoToEntity(pfDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le pf envoye n'est pas valide ", errors,
                    ErrorCode.PRODUCTFORMATED_NOT_VALID.name());
        }

        /*****************************************************************************
         * Si on veut modifier soit le format soit le product alors on verifie
         * l'unicite du resultat qui sera obtenu
         */
        if(!pfToUpdate.getPfFormat().getId().equals(pfDto.getPfFormatId()) ||
            !pfToUpdate.getPfProduct().getId().equals(pfDto.getPfProductId())){
            if(!isPfAttributesUsable(pfDto.getPfProductId(), pfDto.getPfFormatId())){
                throw new DuplicateEntityException("Il existe deja un productformated avec les memes attributs en BD ",
                        ErrorCode.PRODUCTFORMATED_DUPLICATED.name());
            }
            pfToUpdate.setPfFormat(formatMapper.dtoToEntity(formatService.getFormatById(pfDto.getPfFormatId())));
            pfToUpdate.setPfProduct(productMapper.dtoToEntity(productService.getProductById(pfDto.getPfProductId())));
        }

        Productformated pfUpdated = pfDao.save(pfToUpdate);

        return pfMapper.entityToDto(pfUpdated);
    }

    @Override
    public Boolean deleteProductformatedById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du productformated a supprimer est null");
        }
        Optional<Productformated> optionalProductformated = pfDao.findProductformatedById(id);
        if(!optionalProductformated.isPresent()){
            throw new ModelNotFoundException("Aucun Productformated n'existe en BD avec l'id envoye",
                    ErrorCode.PRODUCTFORMATED_NOT_FOUND.name());
        }
        if(!isProductformatedDeleteable(optionalProductformated.get())){
            throw new EntityNotDeleatableException("Il n'est pas possible de supprimer ce Productformated sans causer de conflits ",
                    ErrorCode.PRODUCTFORMATED_NOT_DELETEABLE.name());
        }
        pfDao.delete(optionalProductformated.get());
        return true;
    }

    Boolean isProductformatedDeleteable(Productformated productformated){
        return true;
    }

    @Override
    public ProductformatedDto getProductformatedById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du productformated a supprimer est null");
        }
        Optional<Productformated> optionalProductformated = pfDao.findProductformatedById(id);
        if(!optionalProductformated.isPresent()){
            throw new ModelNotFoundException("Aucun Productformated n'existe en BD avec l'id envoye",
                    ErrorCode.PRODUCTFORMATED_NOT_FOUND.name());
        }

        return pfMapper.entityToDto(optionalProductformated.get());
    }

    @Override
    public List<ProductformatedDto> getListofProductformated(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return pfMapper.entityToDto(pfDao.findAll());
        }
        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return pfMapper.entityToDto(pfDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return pfMapper.entityToDto(pfDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Productformated> pfSpecification = pfSpecService.getProductformatedSpecification(
                filterRequest.getFilters(), filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return pfMapper.entityToDto(pfDao.findAll(pfSpecification));
    }

    @Override
    public PageofProductformatedDto getPageofProductformated(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Productformated> pfPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            pfPage = pfDao.findAll(pageable);
            return getPageofProductformatedDto(pfPage);
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
                pfPage = pfDao.findAll(pageable);
                return getPageofProductformatedDto(pfPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                pfPage = pfDao.findAll(pageable);
                return getPageofProductformatedDto(pfPage);
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
            Specification<Productformated> pfSpecification = pfSpecService.getProductformatedSpecification(
                    filterRequest.getFilters(), filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            pfPage = pfDao.findAll(pfSpecification, pageable);
            return getPageofProductformatedDto(pfPage);
        }
    }

    @Override
    public Boolean isProductformatedExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du productformated a supprimer est null");
        }
        Optional<Productformated> optionalProductformated = pfDao.findProductformatedById(id);
        return optionalProductformated.isPresent();
    }

    PageofProductformatedDto getPageofProductformatedDto(Page<Productformated> pfPage){
        PageofProductformatedDto pageofProductformatedDto = new PageofProductformatedDto();
        pageofProductformatedDto.setContent(pfMapper.entityToDto(pfPage.getContent()));
        pageofProductformatedDto.setCurrentPage(pfPage.getNumber());
        pageofProductformatedDto.setPageSize(pfPage.getSize());
        pageofProductformatedDto.setTotalElements(pfPage.getTotalElements());
        pageofProductformatedDto.setTotalPages(pfPage.getTotalPages());

        return pageofProductformatedDto;
    }
}
