package com.c2psi.bmv1.product.product.service;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.dto.PageofProductDto;
import com.c2psi.bmv1.product.category.mapper.CategoryMapper;
import com.c2psi.bmv1.product.category.models.Category;
import com.c2psi.bmv1.product.category.service.CategoryService;
import com.c2psi.bmv1.product.product.models.Product;
import com.c2psi.bmv1.product.product.dao.ProductDao;
import com.c2psi.bmv1.product.product.errorCode.ErrorCode;
import com.c2psi.bmv1.product.product.mapper.ProductMapper;
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

@Service(value = "ProductServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{
    final ProductDao productDao;
    final CategoryService categoryService;
    final CategoryMapper categoryMapper;
    final ProductMapper productMapper;
    final ProductValidator productValidator;
    final AppService appService;
    final ProductSpecService productSpecService;

    @Override
    public ProductDto saveProduct(ProductDto productDto) {
        if(productDto == null){
            throw new NullValueException("Le productDto envoye ne peut etre null");
        }
        List<String> errorsDto = productValidator.validate(productDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le productDto a enregistrer n'est pas valide ", errorsDto,
                    ErrorCode.PRODUCT_NOT_VALID.name());
        }
        List<String> errors = productValidator.validate(productMapper.dtoToEntity(productDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le product a enregistrer n'est pas valide ", errors,
                    ErrorCode.PRODUCT_NOT_VALID.name());
        }

        if(productDto.getProdCode() != null){
            if(!isProdCodeUsable(productDto.getProdCode())){
                throw new DuplicateEntityException("Le product code envoye est deja utilise par un autre product ",
                        ErrorCode.PRODUCT_DUPLICATED.name());
            }
        }

        Product productToSave = Product.builder()
                .prodName(productDto.getProdName())
                .prodCode(productDto.getProdCode())
                .prodDescription(productDto.getProdDescription())
                .prodAlias(productDto.getProdAlias())
                .prodPerishable(productDto.getProdPerishable())
                .prodCat(categoryMapper.dtoToEntity(categoryService.getCategoryById(productDto.getProdCatId())))
                .build();


        //Product productToSave = productMapper.dtoToEntity(productDto);

        Product productSaved = productDao.save(productToSave);

        return productMapper.entityToDto(productSaved);
    }

    Boolean isProdCodeUsable(String prodCode){
        if(prodCode == null){
            throw new NullValueException("Le prodcode envoye ne peut etre null");
        }
        Optional<Product> optionalProduct = productDao.findProductByProdCode(prodCode);
        return optionalProduct.isEmpty();
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        if(productDto == null){
            throw new NullValueException("Le productDto a update ne peut etre null");
        }
        if(productDto.getId() == null){
            throw new NullValueException("L'id du productDto a update ne peut etre null");
        }
        Optional<Product> optionalProduct = productDao.findProductById(productDto.getId());
        if(!optionalProduct.isPresent()){
            throw new ModelNotFoundException("Aucun Product n'existe en BD avec l'id envoye dans le ProductDto ",
                    ErrorCode.PRODUCT_NOT_FOUND.name());
        }
        Product productToUpdate = optionalProduct.get();

        List<String> errorsDto = productValidator.validate(productDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le productDto envoye pour update n'est pas valide ", errorsDto,
                    ErrorCode.PRODUCT_NOT_VALID.name());
        }

        List<String> errors = productValidator.validate(productMapper.dtoToEntity(productDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le product envoye pour update n'est pas valide ", errors,
                    ErrorCode.PRODUCT_NOT_VALID.name());
        }

        if(productDto.getProdCode() != null){
            if(!isProdCodeUsable(productDto.getProdCode())){
                throw new DuplicateEntityException("Le prodCode envoye est deja utilise par une autre category dans le " +
                        "pointofsale ", ErrorCode.PRODUCT_DUPLICATED.name());
            }
        }

        Category newCat = categoryMapper.dtoToEntity(categoryService.getCategoryById(productDto.getProdCatId()));
        Category oldCat = productToUpdate.getProdCat();
        if(!newCat.getCatPos().getId().equals(oldCat.getCatPos().getId())){
            throw new InvalidEntityException("La nouvelle category ne peut etre dans un pointofsale different de celui " +
                    "ou il etait ", ErrorCode.PRODUCT_NOT_VALID.name());
        }

        productToUpdate.setProdName(productDto.getProdName());
        productToUpdate.setProdCode(productDto.getProdCode());
        productToUpdate.setProdDescription(productDto.getProdDescription());
        productToUpdate.setProdAlias(productDto.getProdAlias());
        productToUpdate.setProdPerishable(productDto.getProdPerishable());
        productToUpdate.setProdCat(categoryMapper.dtoToEntity(categoryService.getCategoryById(
                productDto.getProdCatId())));

        Product productUpdated = productDao.save(productToUpdate);
        return productMapper.entityToDto(productUpdated);
    }

    @Override
    public Boolean deleteProductById(Long id) {
        if(id == null){
            throw new NullValueException("L'id envoye ne peut etre null");
        }
        Optional<Product> optionalProduct = productDao.findProductById(id);
        if(!optionalProduct.isPresent()){
            throw new ModelNotFoundException("Aucun Product n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.PRODUCT_NOT_FOUND.name());
        }
        if(!isProductDeleteable(optionalProduct.get())){
            throw new EntityNotDeleatableException("La suppression de ce product ne peut se faire sans causer de " +
                    "conflit ", ErrorCode.PRODUCT_NOT_DELETEABLE.name());
        }
        productDao.delete(optionalProduct.get());
        return true;
    }

    Boolean isProductDeleteable(Product product){
        return true;
    }

    @Override
    public ProductDto getProductById(Long id) {
        if(id == null){
            throw new NullValueException("L'id envoye ne peut etre null");
        }
        Optional<Product> optionalProduct = productDao.findProductById(id);
        if(!optionalProduct.isPresent()){
            throw new ModelNotFoundException("Aucun Product n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.PRODUCT_NOT_FOUND.name());
        }

        return productMapper.entityToDto(optionalProduct.get());
    }

    @Override
    public List<ProductDto> getListofProduct(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return productMapper.entityToDto(productDao.findAll());
        }
        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return productMapper.entityToDto(productDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return productMapper.entityToDto(productDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Product> productSpecification = productSpecService.getProductSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return productMapper.entityToDto(productDao.findAll(productSpecification));
    }

    @Override
    public PageofProductDto getPageofProduct(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Product> productPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            productPage = productDao.findAll(pageable);
            return getPageofProductDto(productPage);
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
                productPage = productDao.findAll(pageable);
                return getPageofProductDto(productPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                productPage = productDao.findAll(pageable);
                return getPageofProductDto(productPage);
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
            Specification<Product> catSpecification = productSpecService.getProductSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            productPage = productDao.findAll(catSpecification, pageable);
            return getPageofProductDto(productPage);
        }
    }

    @Override
    public Boolean isProductExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id envoye ne peut etre null");
        }
        Optional<Product> optionalProduct = productDao.findProductById(id);
        return optionalProduct.isPresent();
    }

    PageofProductDto getPageofProductDto(Page<Product> productPage){
        PageofProductDto pageofProductDto = new PageofProductDto();
        pageofProductDto.setContent(productMapper.entityToDto(productPage.getContent()));
        pageofProductDto.setCurrentPage(productPage.getNumber());
        pageofProductDto.setPageSize(productPage.getSize());
        pageofProductDto.setTotalElements(productPage.getTotalElements());
        pageofProductDto.setTotalPages(productPage.getTotalPages());

        return pageofProductDto;
    }
}
