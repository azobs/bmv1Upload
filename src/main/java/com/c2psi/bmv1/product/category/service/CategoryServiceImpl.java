package com.c2psi.bmv1.product.category.service;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.product.category.dao.CategoryDao;
import com.c2psi.bmv1.product.category.errorCode.ErrorCode;
import com.c2psi.bmv1.product.category.mapper.CategoryMapper;
import com.c2psi.bmv1.product.category.models.Category;
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

@Service(value = "CategoryServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    final CategoryDao categoryDao;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;
    final CategoryValidator categoryValidator;
    final CategoryMapper categoryMapper;
    final AppService appService;
    final CategorySpecService categorySpecService;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        /*****************************************************
         * On se rassure que l'argument envoye n'est pas null
         */
        if(categoryDto == null){
            throw new NullValueException("The categoryDto sent can't be null");
        }

        /*****************************************************
         * On valide le Dto envoye
         */
        List<String> errorsDto = categoryValidator.validate(categoryDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le CategoryDto envoye pour enregistrement n'est pas valide.", errorsDto,
                    ErrorCode.CATEGORY_NOT_VALID.name());
        }

        /******************************************************
         * On valide le Entity associe au Dto
         */
        List<String> errors = categoryValidator.validate(categoryMapper.dtoToEntity(categoryDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le Category associe au Dto n'est pas valide ", errors,
                    ErrorCode.CATEGORY_NOT_VALID.name());
        }

        /***********************************************************
         * On verifie que la contrainte d'unicite ne sera pas viole
         */
        if(categoryDto.getCatCode() != null){
            if(!isCategoryCodeInPosUsable(categoryDto.getCatCode(), categoryDto.getCatPosId())){
                throw new DuplicateEntityException("Il existe deja dans le pointofsale indique une category avec le " +
                        "meme code", ErrorCode.CATEGORY_DUPLICATED.name());
            }
        }
        Category catParent = null;
        if(categoryDto.getCatParentId() != null){
            catParent = categoryDao.findCategoryById(categoryDto.getCatParentId()).get();//Car la validation est deja sur de l'existance
        }

        /***********************************************************
         * Si tout s'est bien passe on effectue l'enregistrement
         */
        Category categoryToSave = Category.builder()
                .catName(categoryDto.getCatName())
                .catShortname(categoryDto.getCatShortname())
                .catCode(categoryDto.getCatCode())
                .catDescription(categoryDto.getCatDescription())
                .catParent(catParent)
                .catPos(posMapper.dtoToEntity(posService.getPointofsaleById(categoryDto.getCatPosId())))
                .build();
        //Category categoryToSave = categoryMapper.dtoToEntity(categoryDto);
        Category categorySaved = categoryDao.save(categoryToSave);

        return categoryMapper.entityToDto(categorySaved);
    }

    Boolean isCategoryCodeInPosUsable(String catCode, Long posId){
        if(catCode == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nulls");
        }
        Optional<Category> optionalCategory = categoryDao.findCategoryByCodeInPos(catCode, posId);
        return optionalCategory.isEmpty();
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        if(categoryDto == null){
            throw new NullValueException("L'argument categoryDto envoye ne peut etre null");
        }
        if(categoryDto.getId() == null){
            throw new NullValueException("L'id de la categoryDto a update ne peut etre null");
        }
        List<String> errorsDto = categoryValidator.validate(categoryDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le categoryDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.CATEGORY_NOT_VALID.name());
        }
        List<String> errors = categoryValidator.validate(categoryMapper.dtoToEntity(categoryDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le category envoye n'est pas valide ", errors,
                    ErrorCode.CATEGORY_NOT_VALID.name());
        }

        Optional<Category> optionalCategory = categoryDao.findCategoryById(categoryDto.getId());
        if(!optionalCategory.isPresent()){
            throw new ModelNotFoundException("Aucune Category n'existe dans le systeme avec l'id envoye dans le dto",
                    ErrorCode.CATEGORY_NOT_FOUND.name());
        }
        Category categoryToUpdate = optionalCategory.get();

        if(!categoryToUpdate.getCatPos().getId().equals(categoryDto.getCatPosId())){
            throw new InvalidEntityException("Il n'est pas possible de modifier le Pointofsale d'une category ",
                    ErrorCode.CATEGORY_NOT_VALID.name());
        }

        if(!categoryToUpdate.getCatCode().equalsIgnoreCase(categoryDto.getCatCode())){
            if(!isCategoryCodeInPosUsable(categoryDto.getCatCode(), categoryDto.getCatPosId())){
                throw new DuplicateEntityException("Une category existe deja dans ce pointofsale avec le meme code",
                        ErrorCode.CATEGORY_DUPLICATED.name());
            }
            categoryToUpdate.setCatCode(categoryDto.getCatCode());
        }
        Category newCatParent = categoryToUpdate.getCatParent();
        if(!categoryToUpdate.getCatParent().getId().equals(categoryDto.getCatParentId())){
            //Ici on est sur toutes les categories sont dans le meme pointofsale grace au validateur
            newCatParent = categoryDao.findCategoryById(categoryDto.getCatParentId()).get();
        }

        /******************************
         * Le reste des donnees peut etre mis a jour sans souci
         */
        categoryToUpdate.setCatName(categoryDto.getCatName());
        categoryToUpdate.setCatShortname(categoryDto.getCatShortname());
        categoryToUpdate.setCatDescription(categoryDto.getCatDescription());
        categoryToUpdate.setCatParent(newCatParent);

        Category categoryUpdated = categoryDao.save(categoryToUpdate);

        return categoryMapper.entityToDto(categoryUpdated);
    }

    @Override
    public Boolean deleteCategoryById(Long id) {
        if(id == null){
            throw new NullValueException("L'id de la category envoye ne peut etre null");
        }
        Optional<Category> optionalCategory = categoryDao.findCategoryById(id);
        if(!optionalCategory.isPresent()){
            throw new ModelNotFoundException("Aucune Category n'existe avec l'Id envoye",
                    ErrorCode.CATEGORY_NOT_FOUND.name());
        }
        if(!isCategoryDeleteable(optionalCategory.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer cette Category sans causer de conflits",
                    ErrorCode.CATEGORY_NOT_DELETEABLE.name());
        }
        categoryDao.delete(optionalCategory.get());
        return true;
    }

    Boolean isCategoryDeleteable(Category category){
        return true;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        if(id == null){
            throw new NullValueException("L'id envoye pour la recherche de la category ne saurait etre null");
        }
        Optional<Category> optionalCategory = categoryDao.findCategoryById(id);
        if(!optionalCategory.isPresent()){
            throw new ModelNotFoundException("Aucune Category n'existe avec l'Id envoye",
                    ErrorCode.CATEGORY_NOT_FOUND.name());
        }
        return categoryMapper.entityToDto(optionalCategory.get());
    }

    @Override
    public List<CategoryDto> getListofCategory(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return categoryMapper.entityToDto(categoryDao.findAll());
        }
        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return categoryMapper.entityToDto(categoryDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return categoryMapper.entityToDto(categoryDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Category> catSpecification = categorySpecService.getCategorySpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return categoryMapper.entityToDto(categoryDao.findAll(catSpecification));
    }

    @Override
    public PageofCategoryDto getPageofCategory(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Category> catPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            catPage = categoryDao.findAll(pageable);
            return getPageofCategoryDto(catPage);
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
                catPage = categoryDao.findAll(pageable);
                return getPageofCategoryDto(catPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                catPage = categoryDao.findAll(pageable);
                return getPageofCategoryDto(catPage);
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
            Specification<Category> catSpecification = categorySpecService.getCategorySpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            catPage = categoryDao.findAll(catSpecification, pageable);
            return getPageofCategoryDto(catPage);
        }

    }

    @Override
    public Boolean isCategoryExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id de la category envoye est null");
        }
        Optional<Category> optionalCategory = categoryDao.findCategoryById(id);
        return optionalCategory.isPresent();
    }

    PageofCategoryDto getPageofCategoryDto(Page<Category> catPage){
        PageofCategoryDto pageofCatDto = new PageofCategoryDto();
        pageofCatDto.setContent(categoryMapper.entityToDto(catPage.getContent()));
        pageofCatDto.setCurrentPage(catPage.getNumber());
        pageofCatDto.setPageSize(catPage.getSize());
        pageofCatDto.setTotalElements(catPage.getTotalElements());
        pageofCatDto.setTotalPages(catPage.getTotalPages());

        return pageofCatDto;
    }

}
