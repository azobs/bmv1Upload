package com.c2psi.bmv1.product.article.service;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.enumerations.DirectionOfMvt;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.price.baseprice.mappers.BasepriceMapper;
import com.c2psi.bmv1.price.baseprice.services.BasepriceService;
import com.c2psi.bmv1.product.article.dao.ArticleDao;
import com.c2psi.bmv1.product.article.errorCode.ErrorCode;
import com.c2psi.bmv1.product.article.mappers.ArticleMapper;
import com.c2psi.bmv1.product.article.models.Article;
import com.c2psi.bmv1.product.pf.mapper.ProductformatedMapper;
import com.c2psi.bmv1.product.pf.service.ProductformatedService;
import com.c2psi.bmv1.product.unit.unit.mapper.UnitMapper;
import com.c2psi.bmv1.product.unit.unit.service.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service(value = "ArticleServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService{

    final ArticleDao articleDao;
    final ArticleMapper articleMapper;
    final ArticleValidator articleValidator;
    final ArticleSpecService articleSpecService;
    final AppService appService;
    final ProductformatedService pfService;
    final ProductformatedMapper pfMapper;
    final UnitService unitService;
    final UnitMapper unitMapper;
    final BasepriceService basepriceService;
    final BasepriceMapper basepriceMapper;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;

    @Override
    public ArticleDto saveArticle(ArticleDto articleDto) {
        log.info("The saveArticle must start by check if the articleDto is not null");
        if(articleDto == null){
            throw new NullValueException("L'article Dto envoye ne saurait etre null");
        }
        log.info("The articleDto must be validate");
        List<String> errorsDto = articleValidator.validate(articleDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("L'article Dto envoye n'est pas valide ", errorsDto,
                    ErrorCode.ARTICLE_NOT_VALID.name());
        }
        log.info("The article must be validate");
        List<String> errors = articleValidator.validate(articleMapper.dtoToEntity(articleDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("L'article envoye n'est pas valide ", errors,
                    ErrorCode.ARTICLE_NOT_VALID.name());
        }
        log.info("The constraint (code, pos) must be checked");
        if(articleDto.getArtCode() != null){
            if(!isArticleCodeInPosUsable(articleDto.getArtCode(), articleDto.getArtPosId())){
                throw new DuplicateEntityException("Un article existe deja en BD avec le meme code pour le meme " +
                        "pointofsale ", ErrorCode.ARTICLE_NOT_FOUND.name());
            }
        }
        log.info("The constraint (pf_id, unit_id, bp_id) must be checked");
        if(articleDto.getArtPfId() != null){
            if(!isArticlePfUnitAndBpUsable(articleDto.getArtPfId(), articleDto.getArtUnitId(),
                    articleDto.getArtBasepriceId())){
                throw new DuplicateEntityException("Un article issu du productformated indique, se vendant dans l'unite " +
                        "precise et au meme prix indique existe deja dans la BD", ErrorCode.ARTICLE_DUPLICATED.name());
            }
        }
        log.info("All constraint has been verified and the entity can be registered in the system");

//        Article articleToSave = Article.builder()
//                .artCode(articleDto.getArtCode())
//                .artName(articleDto.getArtName())
//                .artShortname(articleDto.getArtShortname())
//                .artDescription(articleDto.getArtDescription())
//                .artThreshold(Double.valueOf(articleDto.getArtThreshold()))
//                .artLowlimitwholesale(Double.valueOf(articleDto.getArtLowlimitwholesale()))
//                .artlowlimitSemiwholesale(Double.valueOf(articleDto.getArtlowlimitSemiwholesale()))
//                .artQuantityinstock(Double.valueOf(articleDto.getArtQuantityinstock()))
//                .artPf(pfMapper.dtoToEntity(pfService.getProductformatedById(articleDto.getArtPfId())))
//                .artUnit(unitMapper.dtoToEntity(unitService.getUnitById(articleDto.getArtUnitId())))
//                .artPos(posMapper.dtoToEntity(posService.getPointofsaleById(articleDto.getArtPosId())))
//                .artBaseprice(basepriceMapper.dtoToEntity(basepriceService.getBasepriceById(articleDto.getArtBasepriceId())))
//                .build();
        Article articleToSave = articleMapper.dtoToEntity(articleDto);

        Article articleSaved = articleDao.save(articleToSave);
        return articleMapper.entityToDto(articleSaved);
    }

    Boolean isArticleCodeInPosUsable(String artCode, Long posId){
        if(artCode == null || posId == null){
            throw new NullValueException("The arguments sent is null");
        }
        Optional<Article> optionalArticle = articleDao.findArticleByCodeInPos(artCode, posId);
        return optionalArticle.isEmpty();
    }

    Boolean isArticlePfUnitAndBpUsable(Long pfId, Long unitId, Long bpId){
        if(pfId == null || unitId == null || bpId == null){
            throw new NullValueException("The arguments sent are nuls");
        }
        Optional<Article> optionalArticle = articleDao.findArticleByPfUnitAndBpInPos(pfId, unitId, bpId);
        return optionalArticle.isEmpty();
    }

    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        log.info("On lance le update en testant si l'argument n'est pas null");
        if(articleDto == null){
            throw new NullValueException("L'articleDto envoye pour update ne saurait etre null");
        }
        log.info("On continue le update en testant que l'id de l'articleDto n'est pas null");
        if(articleDto.getId() == null){
            throw new NullValueException("L'Id de l'articledto a update ne saurait etre null");
        }
        log.info("On continue le update en validant l'articleDto");
        List<String> errorsDto = articleValidator.validate(articleDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("L'articledto envoye pour update n'est pas valide ",
                    ErrorCode.ARTICLE_NOT_VALID.name());
        }
        log.info("On continue le update en validant l'article associe au Dto");
        List<String> errors = articleValidator.validate(articleMapper.dtoToEntity(articleDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("L'article associe au Dto pour update n'est pas valide ",
                    ErrorCode.ARTICLE_NOT_VALID.name());
        }
        log.info("On continue le update en recherchant l'article a Update");
        Optional<Article> optionalArticle = articleDao.findArticleById(articleDto.getId());
        if(!optionalArticle.isPresent()){
            throw new ModelNotFoundException("Aucun Article n'existe en BD avec l'id envoye",
                    ErrorCode.ARTICLE_NOT_FOUND.name());
        }
        Article articleToUpdate = optionalArticle.get();

        log.info("On continue le update en se rassurant que si le code est precise dans le Dto alors la contrainte " +
                "d'unicite lie au code ne sera pas viole");
        if(articleDto.getArtCode() != null){
            if(articleToUpdate.getArtCode() != null){
                if(!isArticleCodeInPosUsable(articleDto.getArtCode(), articleDto.getArtPosId())){
                    throw new DuplicateEntityException("Il existe deja un Article avec le code dans le pointofsale " +
                            "precise ", ErrorCode.ARTICLE_DUPLICATED.name());
                }
            }
        }

        log.info("On continue le update en se rassurant que si le code est precise dans le Dto alors la contrainte " +
                "d'unicite lie au product formated et au prix ne sera pas viole");
        if(articleDto.getArtPfId() != null){
            if(articleToUpdate.getArtPf() != null){
                if(articleToUpdate.getArtPf().getId() != null){
                    if(!isArticlePfUnitAndBpUsable(articleDto.getArtPfId(), articleDto.getArtUnitId(),
                            articleDto.getArtBasepriceId())){
                        throw new DuplicateEntityException("Il existe deja un Article pour ce productformated vendu " +
                                "dans la meme unite de vente et au meme prix ", ErrorCode.ARTICLE_DUPLICATED.name());
                    }
                }
            }
        }

        log.info("On continue le update en preparant toutes les modifications");

        articleToUpdate.setArtCode(articleDto.getArtCode());
        articleToUpdate.setArtName(articleDto.getArtName());
        articleToUpdate.setArtShortname(articleDto.getArtShortname());
        articleToUpdate.setArtDescription(articleDto.getArtDescription());
        articleToUpdate.setArtThreshold(articleDto.getArtThreshold());
        articleToUpdate.setArtLowlimitwholesale(articleDto.getArtLowlimitwholesale());
        articleToUpdate.setArtlowlimitSemiwholesale(articleDto.getArtlowlimitSemiwholesale());
        articleToUpdate.setArtQuantityinstock(articleDto.getArtQuantityinstock());
        articleToUpdate.setArtPf(pfMapper.dtoToEntity(pfService.getProductformatedById(articleDto.getArtPfId())));
        articleToUpdate.setArtUnit(unitMapper.dtoToEntity(unitService.getUnitById(articleDto.getArtUnitId())));
        articleToUpdate.setArtPos(posMapper.dtoToEntity(posService.getPointofsaleById(articleDto.getArtPosId())));
        articleToUpdate.setArtBaseprice(basepriceMapper.dtoToEntity(basepriceService.getBasepriceById(articleDto.
                getArtBasepriceId())));

        Article article = articleDao.save(articleToUpdate);
        return articleMapper.entityToDto(article);
    }

    @Override
    public ArticleDto updateQteInStockofArticle(Long articleId, Double qteInMvt, DirectionOfMvt directionOfMvt) {
        if(articleId == null || qteInMvt == null || directionOfMvt == null){
            throw new NullValueException("Some of the arguments sent are nulls");
        }
        ArticleDto articleDto = this.getArticleById(articleId);
        BigDecimal qteInStock = BigDecimal.valueOf(articleDto.getArtQuantityinstock());
        BigDecimal qteInArrival = BigDecimal.valueOf(qteInMvt);
        BigDecimal finalQte = null;
        switch (directionOfMvt){
            case ADD:
                finalQte = qteInArrival.add(qteInStock);
                break;
            case REMOVE:
                finalQte = qteInArrival.subtract(qteInStock);
                break;
            default:
                throw new InvalidDirectionofMouvementException("The precise direction is not recognize by the system. " +
                        "The possible value are Add of Remove");
        }

        Double finalQteInDouble = finalQte.doubleValue();
        articleDto.setArtQuantityinstock(finalQteInDouble);

        return this.updateArticle(articleDto);
    }

    @Override
    public Boolean deleteArticleById(Long id) {
        if(id == null){
            throw new NullValueException("l'id de l'article a delete ne saurait etre null");
        }
        Optional<Article> optionalArticle = articleDao.findArticleById(id);
        if(!optionalArticle.isPresent()){
            throw new ModelNotFoundException("Aucun article n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.ARTICLE_NOT_FOUND.name());
        }
        if(!isArticleDeleteable(optionalArticle.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer cet article sans causer de conflit",
                    ErrorCode.ARTICLE_NOT_DELETEABLE.name());
        }
        articleDao.delete(optionalArticle.get());
        return true;
    }

    Boolean isArticleDeleteable(Article article){
        return true;
    }

    @Override
    public ArticleDto getArticleById(Long id) {
        if(id == null){
            throw new NullValueException("l'id de l'article a delete ne saurait etre null");
        }
        Optional<Article> optionalArticle = articleDao.findArticleById(id);
        if(!optionalArticle.isPresent()){
            throw new ModelNotFoundException("Aucun article n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.ARTICLE_NOT_FOUND.name());
        }
        return articleMapper.entityToDto(optionalArticle.get());
    }

    @Override
    public List<ArticleDto> getListofArticle(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return articleMapper.entityToDto(articleDao.findAll());
        }
        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return articleMapper.entityToDto(articleDao.findAll());
        }
        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return articleMapper.entityToDto(articleDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Article> articleSpecification = articleSpecService.getArticleSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return articleMapper.entityToDto(articleDao.findAll(articleSpecification));
    }

    @Override
    public PageofArticleDto getPageofArticle(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Article> articlePage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            articlePage = articleDao.findAll(pageable);
            return getPageofArticleDto(articlePage);
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
                articlePage = articleDao.findAll(pageable);
                return getPageofArticleDto(articlePage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                articlePage = articleDao.findAll(pageable);
                return getPageofArticleDto(articlePage);
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
            Specification<Article> articleSpecification = articleSpecService.getArticleSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            articlePage = articleDao.findAll(articleSpecification, pageable);
            return getPageofArticleDto(articlePage);
        }
    }

    @Override
    public Boolean isArticleExistWith(Long id) {
        if(id == null){
            throw new NullValueException("l'id de l'article a delete ne saurait etre null");
        }
        Optional<Article> optionalArticle = articleDao.findArticleById(id);
        return optionalArticle.isPresent();
    }

    PageofArticleDto getPageofArticleDto(Page<Article> articlePage){
        PageofArticleDto pageofArticleDto = new PageofArticleDto();
        pageofArticleDto.setContent(articleMapper.entityToDto(articlePage.getContent()));
        pageofArticleDto.setCurrentPage(articlePage.getNumber());
        pageofArticleDto.setPageSize(articlePage.getSize());
        pageofArticleDto.setTotalElements(articlePage.getTotalElements());
        pageofArticleDto.setTotalPages(articlePage.getTotalPages());

        return pageofArticleDto;
    }
}
