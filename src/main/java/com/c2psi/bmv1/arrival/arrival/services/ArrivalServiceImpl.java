package com.c2psi.bmv1.arrival.arrival.services;

import com.c2psi.bmv1.arrival.arrival.dao.ArrivalDao;
import com.c2psi.bmv1.arrival.arrival.mappers.ArrivalMapper;
import com.c2psi.bmv1.arrival.arrival.errorCode.ErrorCode;
import com.c2psi.bmv1.arrival.arrival.models.Arrival;
import com.c2psi.bmv1.arrival.supplyinvoice.mappers.SupplyinvoiceMapper;
import com.c2psi.bmv1.arrival.supplyinvoice.services.SupplyinvoiceService;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.enumerations.ArrivalTypeEnum;
import com.c2psi.bmv1.bmapp.enumerations.DirectionOfMvt;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.product.article.mappers.ArticleMapper;
import com.c2psi.bmv1.product.article.models.Article;
import com.c2psi.bmv1.product.article.service.ArticleService;
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

@Service(value = "ArrivalServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArrivalServiceImpl implements ArrivalService{
    final ArrivalDao arrivalDao;
    final ArrivalMapper arrivalMapper;
    final ArrivalValidator arrivalValidator;
    final ArrivalSpecService arrivalSpecService;
    final SupplyinvoiceService siService;
    final SupplyinvoiceMapper siMapper;
    final ArticleService articleService;
    final ArticleMapper articleMapper;
    final AppService appService;


    @Override
    public ArrivalDto saveArrival(ArrivalDto arrivalDto) {
        log.info("We start saving an arrival by checking if the arrivalDto is not null");
        if(arrivalDto == null){
            throw new NullValueException("L'arrivalDto envoye pour enregistrement ne saurait etre null");
        }
        log.info("We continue saving an arrival by validating the arrivalDto");
        List<String> errorsDto = arrivalValidator.validate(arrivalDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("The arrival dto sent is not valid ", errorsDto,
                    ErrorCode.ARRIVAL_NOT_VALID.name());
        }
        log.info("We continue saving an arrival by validating the arrival");
        List<String> errors = arrivalValidator.validate(arrivalMapper.dtoToEntity(arrivalDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("The arrival dto sent is not valid ", errors,
                    ErrorCode.ARRIVAL_NOT_VALID.name());
        }
        log.info("We continue saving an arrival by validating the unicity of an arrival in the DB if the invoice " +
                "is not null");
        if(arrivalDto.getArrivalInvoiceId() != null){
            if(!isArrivalUniqueInSupplyinvoice(arrivalDto.getArrivalArticleId(), arrivalDto.getArrivalInvoiceId())){
                throw new DuplicateEntityException("Un arrivage de cet article dans la meme facture  a deja " +
                        "ete enregistre ", ErrorCode.ARRIVAL_DUPLICATED.name());
            }
        }
        log.info("We continue saving an arrival by preparing the arrival to save");
        Arrival arrivalToSave = arrivalMapper.dtoToEntity(arrivalDto);
        log.info("We continue saving an arrival by realy save the arrival in the DB");
        Arrival arrivalSaved = arrivalDao.save(arrivalToSave);
        log.info("We continue saving an arrival by retrieving the corresponding article in the DB");
        Article articleToUpdate = arrivalSaved.getArrivalArticle();
        log.info("We continue saving an arrival by updating the quantity in stock of the corresponding article " +
                "in the DB");

//        BigDecimal qteInStock = BigDecimal.valueOf(articleToUpdate.getArtQuantityinstock());
//        BigDecimal qteInArrival = BigDecimal.valueOf(arrivalDto.getDeliveryQuantity());
//        BigDecimal finalQte = qteInArrival.add(qteInStock);
//        Double finalQteInDouble = finalQte.doubleValue();
//        articleToUpdate.setArtQuantityinstock(finalQteInDouble);
//        articleService.updateArticle(articleMapper.entityToDto(articleToUpdate));
//        log.info("After updating the corresponding article, we can return the saving arrival");

        articleService.updateQteInStockofArticle(articleToUpdate.getId(), arrivalDto.getDeliveryQuantity(),
                DirectionOfMvt.ADD);

        log.info("After updating the quantity of the article in stock due to the arrival, we can now return the " +
                "saving arrival ");
        return arrivalMapper.entityToDto(arrivalSaved);
    }

    Boolean isArrivalUniqueInSupplyinvoice(Long articleId, Long siId){
        if(articleId == null || siId == null){
            throw new NullValueException("The id value sent is null");
        }
        Optional<Arrival> optionalArrival = arrivalDao.findArrivalByArticleInSInvoice(articleId, siId);
        return optionalArrival.isEmpty();
    }

    @Override
    public ArrivalDto updateArrival(ArrivalDto arrivalDto) {
        /*************************************************************************************************************
         * Si on met a jour un arrivage, on va regarder si la quantite livre est differente. si c'est le cas alors:
         * La quantite en stock doit obligatoirement etre superieur ou egale a la quantite livre dans l'arrivage
         * sinon la mise a jour nest plus possible car cela signifie qu'apres avoir enregistre l'arrivage il y a eu
         * d'autre mouvement de stock avant cette mise a jour.
         * si cette quantite est donc supperieure, il faut
         *      -d'abord retirer du stock l'ancienne quantite ajoute
         *      -Puis ajouter la nouvelle
         */
        log.info("We start to update the arrival by checking if the arrivalDto is not null");
        if(arrivalDto == null){
            throw new NullValueException("L'arrivalDto a update ne saurait etre null");
        }
        log.error("We continue to update the arrival by ensure that the id of the arrival is not null");
        if(arrivalDto.getId() == null){
            throw new NullValueException("L'id de l'arrival a update ne saurait etre null");
        }
        log.error("We continue to update the arrival by validate the arrivalDto");
        List<String> errorsDto = arrivalValidator.validate(arrivalDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("L'arrivalDto envoye pour update n'est pas valide ", errorsDto,
                    ErrorCode.ARRIVAL_NOT_VALID.name());
        }
        log.error("We continue to update the arrival by validate the arrival");
        List<String> errors = arrivalValidator.validate(arrivalMapper.dtoToEntity(arrivalDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("L'arrival associe a l'arrivalDto pour update n'est pas valide ", errors,
                    ErrorCode.ARRIVAL_NOT_VALID.name());
        }
        log.error("We continue to update the arrival by retrieve the arrival to update in the DB");
        Optional<Arrival> optionalArrival = arrivalDao.findArrivalById(arrivalDto.getId());
        if(!optionalArrival.isPresent()){
            throw new ModelNotFoundException("Aucun Arrival n'existe dans le systeme avec l'id envoye",
                    ErrorCode.ARRIVAL_NOT_FOUND.name());
        }
        Arrival arrivalToUpdate = optionalArrival.get();
        log.error("We continue to update the arrival by ensure that the arrival will stay unique in the DB");
        if(arrivalDto.getArrivalInvoiceId() != null && arrivalToUpdate.getArrivalSi().getId() != null){
            if(!arrivalDto.getArrivalInvoiceId().equals(arrivalToUpdate.getArrivalSi().getId())){
                if(!isArrivalUniqueInSupplyinvoice(arrivalDto.getArrivalArticleId(), arrivalDto.getArrivalInvoiceId())){
                    throw new DuplicateEntityException("Il existe deja un arrival pour cet article dans le systeme ",
                            ErrorCode.ARRIVAL_DUPLICATED.name());
                }
            }
        }

        log.error("We continue to update the arrival by ensure that the arrival will stay unique in the DB if " +
                "the article is different");
        if(!arrivalDto.getArrivalArticleId().equals(arrivalToUpdate.getArrivalArticle().getId())){
            if(arrivalDto.getArrivalInvoiceId() != null){
                if(!isArrivalUniqueInSupplyinvoice(arrivalDto.getArrivalArticleId(), arrivalDto.getArrivalInvoiceId())){
                    throw new DuplicateEntityException("Il existe deja un arrival pour cet article dans le systeme ",
                            ErrorCode.ARRIVAL_DUPLICATED.name());
                }
            }
        }

        log.error("We continue to update the arrival by preparing the update");

        arrivalToUpdate.setArrivalDate(arrivalDto.getArrivalDate());
        arrivalToUpdate.setArrivalUnitprice(arrivalDto.getArrivalUnitprice());
        arrivalToUpdate.setArrivalType(ArrivalTypeEnumToArrivalDtoArrivalTypeEnum(arrivalDto.getArrivalType()));
        arrivalToUpdate.setArrivalNature(ArrivalNatureEnumToArrivalDtoArrivalNatureEnum(arrivalDto.getArrivalNature()));
        arrivalToUpdate.setArrivalArticle(articleMapper.dtoToEntity(
                articleService.getArticleById(arrivalDto.getArrivalArticleId())));
        arrivalToUpdate.setArrivalSi(arrivalDto.getArrivalInvoiceId() == null? null :
                siMapper.dtoToEntity(siService.getSupplyinvoiceById(arrivalDto.getArrivalInvoiceId())));

        log.info("We continue updating an arrival by retrieving the corresponding article in the DB");
        Article articleToUpdate = arrivalToUpdate.getArrivalArticle();

        if(articleToUpdate.getArtQuantityinstock().doubleValue() < arrivalDto.getDeliveryQuantity().doubleValue()){
            throw new InvalidEntityException("Cette mise a jour n'est plus possible puisque des mouvements de stock ont" +
                    " deja ete effectue concernant cet article. La quantite restante en stock est deja inferieur a celle " +
                    "indique dans l'arrivage ", ErrorCode.ARRIVAL_NOT_VALID.name());
        }
        else{
            log.info("We must retrieve the old quantity {} in stock", arrivalToUpdate.getDeliveryQuantity());
            articleService.updateQteInStockofArticle(articleToUpdate.getId(), arrivalToUpdate.getDeliveryQuantity(),
                    DirectionOfMvt.REMOVE);
            log.info("We can now add the new quantity in the arrival {} in stock", arrivalDto.getDeliveryQuantity());
            articleService.updateQteInStockofArticle(articleToUpdate.getId(), arrivalDto.getDeliveryQuantity(),
                    DirectionOfMvt.ADD);
            arrivalToUpdate.setDeliveryQuantity(arrivalDto.getDeliveryQuantity());
        }

        return arrivalMapper.entityToDto(arrivalDao.save(arrivalToUpdate));
    }

    ArrivalTypeEnum ArrivalTypeEnumToArrivalDtoArrivalTypeEnum(ArrivalDto.ArrivalTypeEnum arrivalTypeEnum){
        switch (arrivalTypeEnum){
            case DIVERS:
                return ArrivalTypeEnum.DIVERS;
            case STANDARD:
                return ArrivalTypeEnum.STANDARD;
            default:
                throw new EnumNonConvertibleException("La valeur de l'arrival type n'est pas reconnu par le systeme");
        }
    }

    ArrivalTypeEnum ArrivalNatureEnumToArrivalDtoArrivalNatureEnum(ArrivalDto.ArrivalNatureEnum arrivalNatureEnum){
        switch (arrivalNatureEnum){
            case CASH:
                return ArrivalTypeEnum.CASH;
            case COVER:
                return ArrivalTypeEnum.COVER;
            case DAMAGE:
                return ArrivalTypeEnum.DAMAGE;
            default:
                throw new EnumNonConvertibleException("La valeur de l'arrival nature n'est pas reconnu par le systeme");
        }
    }

    @Override
    public Boolean deleteArrivalById(Long id) {
        /************************************************************************************************
         * Pour la suppression, il faut retirer d'abord du stock la quantite que cet arrivage a ajoute.
         * et pour cela la quantite courante en stock doit etre superieure ou egale a celle de l'arrivage
         * a supprimer. sinon c'est que des mouvements de stock ce sont deja effectue apres que l'arrivage
         * ne soit enregistree. et dans ce cas la suppression de l'arrivage n'est plus possible.
         */
        if(id == null){
            throw new NullValueException("L'id de l'arrival envoye pour delete est null");
        }
        Optional<Arrival> optionalArrival = arrivalDao.findArrivalById(id);
        if(!optionalArrival.isPresent()){
            throw new ModelNotFoundException("Aucun arrival id n'existe dans le systeme avec l'id envoye",
                    ErrorCode.ARRIVAL_NOT_FOUND.name());
        }
        if(!isArrivalDeleteable(optionalArrival.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer cet arrival car des mouvements de stock ont deja " +
                    "ete effectue prenant en compte cet arrivage ", ErrorCode.ARRIVAL_NOT_DELETEABLE.name());
        }
        log.info("Before deleting the arrival, we must update the quantityinstock of the article");
        articleService.updateQteInStockofArticle(optionalArrival.get().getArrivalArticle().getId(),
                optionalArrival.get().getDeliveryQuantity(), DirectionOfMvt.REMOVE);
        arrivalDao.delete(optionalArrival.get());
        return true;
    }

    Boolean isArrivalDeleteable(Arrival arrival){
        Article articleAssociate = arrival.getArrivalArticle();
        if(articleAssociate.getArtQuantityinstock() < arrival.getDeliveryQuantity()){
            return false;
        }
        return true;
    }


    @Override
    public ArrivalDto getArrivalById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'arrival envoye pour delete est null");
        }
        Optional<Arrival> optionalArrival = arrivalDao.findArrivalById(id);
        if(!optionalArrival.isPresent()){
            throw new ModelNotFoundException("Aucun arrival id n'existe dans le systeme avec l'id envoye",
                    ErrorCode.ARRIVAL_NOT_FOUND.name());
        }
        return arrivalMapper.entityToDto(optionalArrival.get());
    }

    @Override
    public List<ArrivalDto> getListofArrival(FilterRequest filterRequest) {

        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return arrivalMapper.entityToDto(arrivalDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return arrivalMapper.entityToDto(arrivalDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return arrivalMapper.entityToDto(arrivalDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Arrival> arrivalSpecification = arrivalSpecService.getArrivalSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return arrivalMapper.entityToDto(arrivalDao.findAll(arrivalSpecification));
    }

    @Override
    public PageofArrivalDto getPageofArrival(FilterRequest filterRequest) {

        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Arrival> arrivalPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            arrivalPage = arrivalDao.findAll(pageable);
            return getPageofArrivalDto(arrivalPage);
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
                arrivalPage = arrivalDao.findAll(pageable);
                return getPageofArrivalDto(arrivalPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                arrivalPage = arrivalDao.findAll(pageable);
                return getPageofArrivalDto(arrivalPage);
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
            Specification<Arrival> arrivalSpecification = arrivalSpecService.getArrivalSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            arrivalPage = arrivalDao.findAll(arrivalSpecification, pageable);
            return getPageofArrivalDto(arrivalPage);

        }
    }

    @Override
    public Boolean isArrivalExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id de l'arrival envoye pour delete est null");
        }
        Optional<Arrival> optionalArrival = arrivalDao.findArrivalById(id);
        return optionalArrival.isPresent();
    }

    PageofArrivalDto getPageofArrivalDto(Page<Arrival> arrivalPage){
        PageofArrivalDto pageofArrivalDto = new PageofArrivalDto();
        pageofArrivalDto.setContent(arrivalMapper.entityToDto(arrivalPage.getContent()));
        pageofArrivalDto.setCurrentPage(arrivalPage.getNumber());
        pageofArrivalDto.setPageSize(arrivalPage.getSize());
        pageofArrivalDto.setTotalElements(arrivalPage.getTotalElements());
        pageofArrivalDto.setTotalPages(arrivalPage.getTotalPages());

        return pageofArrivalDto;

    }
}
