package com.c2psi.bmv1.sale.sale.services;

import com.c2psi.bmv1.sale.sale.models.Sale;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.sale.sale.models.Sale;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.sale.sale.models.Sale;
import com.c2psi.bmv1.bmapp.enumerations.CommandStateEnum;
import com.c2psi.bmv1.bmapp.enumerations.SaleTypeEnum;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.product.article.mappers.ArticleMapper;
import com.c2psi.bmv1.product.article.service.ArticleService;
import com.c2psi.bmv1.sale.command.mappers.CommandMapper;
import com.c2psi.bmv1.sale.command.models.Command;
import com.c2psi.bmv1.sale.command.services.CommandService;
import com.c2psi.bmv1.sale.sale.dao.SaleDao;
import com.c2psi.bmv1.sale.sale.errorCode.ErrorCode;
import com.c2psi.bmv1.sale.sale.mappers.SaleMapper;
import com.c2psi.bmv1.sale.sale.models.Sale;
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

@Service(value = "SaleServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SaleServiceImpl implements SaleService{
    final AppService appService;
    final SaleValidator saleValidator;
    final SaleMapper saleMapper;
    final SaleDao saleDao;
    final SaleSpecService saleSpecService;
    final CommandService commandService;
    final CommandMapper commandMapper;
    final ArticleService articleService;
    final ArticleMapper articleMapper;

    @Override
    public SaleDto saveSale(SaleDto saleDto) {
        log.info("The sale saving process start by ensure that saleDto is not null");
        if(saleDto == null){
            throw new NullValueException("Le saleDto a save ne saurait etre null");
        }
        log.info("The sale saving process continue by validate the saleDto");
        List<String> errorsDto = saleValidator.validate(saleDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le saleDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.SALE_NOT_VALID.name());
        }
        log.info("The sale saving process continue by validate the sale associate to the saleDto");
        List<String> errors = saleValidator.validate(saleMapper.dtoToEntity(saleDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le saleDto envoye n'est pas valide ", errors,
                    ErrorCode.SALE_NOT_VALID.name());
        }
        log.info("The sale saving process continue by ensure that the sale will be unique in DB");
        if(!isSaleAboutArticleUniqueInCommand(saleDto.getSaleArticleId(), saleDto.getSaleCommandId())){
            throw new DuplicateEntityException("Il existe deja une vente concernant cet article dans le pointofsale " +
                    "precise ", ErrorCode.SALE_DUPLICATED.name());
        }
        log.info("The sale saving process continue by preparing the sale to register");
        //Sale saleToSave = saleMapper.dtoToEntity(saleDto);
        /**
         * saleToUpdate.setSaleArticle(articleMapper.dtoToEntity(articleService.getArticleById(saleDto.getSaleArticleId())));
         *         saleToUpdate.setSaleCommand(commandMapper.dtoToEntity(commandService.getCommandById(saleDto.getSaleCommandId())));
         *         saleToUpdate.setSaleComment(saleDto.getSaleComment());
         *         saleToUpdate.setSaleFinalprice(saleDto.getSaleFinalprice());
         *         saleToUpdate.setSaleType(convertToSaleDtoSaleTypeEnum(saleDto.getSaleType()));
         *         saleToUpdate.setSaleQuantity(saleDto.getSaleQuantity());
         */
        Sale saleToSave = Sale.builder()
                .saleArticle(articleMapper.dtoToEntity(articleService.getArticleById(saleDto.getSaleArticleId())))
                .saleCommand(commandMapper.dtoToEntity(commandService.getCommandById(saleDto.getSaleCommandId())))
                .saleComment(saleDto.getSaleComment())
                .saleFinalprice(saleDto.getSaleFinalprice())
                .saleType(convertToSaleDtoSaleTypeEnum(saleDto.getSaleType()))
                .saleQuantity(saleDto.getSaleQuantity())
                .build();

        return saleMapper.entityToDto(saleDao.save(saleToSave));
    }

    Boolean isSaleAboutArticleUniqueInCommand(Long articleId, Long commandId){
        if(articleId == null || commandId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Sale> optionalSale = saleDao.findSaleAboutArticleInCommand(articleId, commandId);
        return optionalSale.isEmpty();
    }

    @Override
    public SaleDto updateSale(SaleDto saleDto) {
        log.info("The sale updating process start by ensure that saleDto is not null");
        if(saleDto == null){
            throw new NullValueException("Le saleDto envoye dans le update est null");
        }
        log.info("The sale updating process continue by ensure that saleDtoId is not null");
        if(saleDto.getId() == null){
            throw new NullValueException("Le saleDtoId envoye dans le update est null");
        }
        log.info("The sale updating process continue by validate the saleDto");
        List<String> errorsDto = saleValidator.validate(saleDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le saleDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.SALE_NOT_VALID.name());
        }
        log.info("The sale updating process continue by validate the sale associate to the saleDto");
        List<String> errors = saleValidator.validate(saleMapper.dtoToEntity(saleDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le saleDto envoye n'est pas valide ", errors,
                    ErrorCode.SALE_NOT_VALID.name());
        }
        log.info("The sale updating process continue by retrieve the saleDto to update");
        Optional<Sale> optionalSale = saleDao.findSaleById(saleDto.getId());
        if(!optionalSale.isPresent()){
            throw new ModelNotFoundException("Aucun sale n'existe dans le systeme avec l'id envoye");
        }
        Sale saleToUpdate = optionalSale.get();
        log.info("The sale updating process continue by ensure that the sale will be unique in DB");
        boolean isArticlechange = !saleToUpdate.getSaleArticle().getId().equals(saleDto.getSaleArticleId());
        boolean isCommandchange = !saleToUpdate.getSaleCommand().getId().equals(saleDto.getSaleCommandId());
        if(isArticlechange && isCommandchange){
            if(!isSaleAboutArticleUniqueInCommand(saleDto.getSaleArticleId(), saleDto.getSaleCommandId())){
                throw new DuplicateEntityException("Il existe deja un sale dans le systeme pour cet article dans " +
                        "la meme commande ", ErrorCode.SALE_DUPLICATED.name());
            }
        }
        log.info("The sale updating process continue by preparing the sale to update");
        saleToUpdate.setSaleArticle(articleMapper.dtoToEntity(articleService.getArticleById(saleDto.getSaleArticleId())));
        saleToUpdate.setSaleCommand(commandMapper.dtoToEntity(commandService.getCommandById(saleDto.getSaleCommandId())));
        saleToUpdate.setSaleComment(saleDto.getSaleComment());
        saleToUpdate.setSaleFinalprice(saleDto.getSaleFinalprice());
        saleToUpdate.setSaleType(convertToSaleDtoSaleTypeEnum(saleDto.getSaleType()));
        saleToUpdate.setSaleQuantity(saleDto.getSaleQuantity());
        return saleMapper.entityToDto(saleDao.save(saleToUpdate));
    }

    SaleTypeEnum convertToSaleDtoSaleTypeEnum(SaleDto.SaleTypeEnum saleTypeEnum){
        switch (saleTypeEnum){
            case WHOLE:
                return SaleTypeEnum.Whole;
            case DETAILS:
                return SaleTypeEnum.Whole;
            case SEMIWHOLE:
                return SaleTypeEnum.Semiwhole;
            case PERMUTATION:
                return SaleTypeEnum.Permutation;
            default:
                throw new EnumNonConvertibleException("Sale type non recognize by the system");
        }

    }

    @Override
    public Boolean deleteSaleById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du sale a delete ne saurait etre null");
        }
        Optional<Sale> optionalSale = saleDao.findSaleById(id);
        if(!optionalSale.isPresent()){
            throw new ModelNotFoundException("Aucun sale n'existe avec l'id envoye", ErrorCode.SALE_NOT_FOUND.name());
        }
        if(!isSaleDeleteable(optionalSale.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer ce sale sans causer de conflit ",
                    ErrorCode.SALE_NOT_DELETEABLE.name());
        }
        saleDao.delete(optionalSale.get());
        return true;
    }

    Boolean isSaleDeleteable(Sale sale){
        if(sale == null){
            throw new NullValueException("Le sale envoye ne saurait etre null");
        }
        Command command = sale.getSaleCommand();
        if(command == null){
            throw new NullValueException("le command associe au sale ne saurait etre null");
        }
        if(command.getCmdState().equals(CommandStateEnum.Delivery)){
            return false;
        }
        return true;
    }

    @Override
    public SaleDto getSaleById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du sale a delete ne saurait etre null");
        }
        Optional<Sale> optionalSale = saleDao.findSaleById(id);
        if(!optionalSale.isPresent()){
            throw new ModelNotFoundException("Aucun sale n'existe avec l'id envoye", ErrorCode.SALE_NOT_FOUND.name());
        }
        return saleMapper.entityToDto(optionalSale.get());
    }

    @Override
    public List<SaleDto> getListofSale(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return saleMapper.entityToDto(saleDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return saleMapper.entityToDto(saleDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return saleMapper.entityToDto(saleDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Sale> saleSpecification = saleSpecService.getSaleSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return saleMapper.entityToDto(saleDao.findAll(saleSpecification));
    }

    @Override
    public PageofSaleDto getPageofSale(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Sale> salePage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            salePage = saleDao.findAll(pageable);
            return getPageofSaleDto(salePage);
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
                salePage = saleDao.findAll(pageable);
                return getPageofSaleDto(salePage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                salePage = saleDao.findAll(pageable);
                return getPageofSaleDto(salePage);
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
            Specification<Sale> saleSpecification = saleSpecService.getSaleSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            salePage = saleDao.findAll(saleSpecification, pageable);
            return getPageofSaleDto(salePage);

        }
    }

    @Override
    public Boolean isSaleExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du sale a delete ne saurait etre null");
        }
        Optional<Sale> optionalSale = saleDao.findSaleById(id);

        return optionalSale.isPresent();
    }

    PageofSaleDto getPageofSaleDto(Page<Sale> salePage){
        PageofSaleDto pageofSaleDto = new PageofSaleDto();
        pageofSaleDto.setContent(saleMapper.entityToDto(salePage.getContent()));
        pageofSaleDto.setCurrentPage(salePage.getNumber());
        pageofSaleDto.setPageSize(salePage.getSize());
        pageofSaleDto.setTotalElements(salePage.getTotalElements());
        pageofSaleDto.setTotalPages(salePage.getTotalPages());

        return pageofSaleDto;

    }

}
