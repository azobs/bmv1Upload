package com.c2psi.bmv1.product.format.service;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.product.format.models.Format;
import com.c2psi.bmv1.product.format.dao.FormatDao;
import com.c2psi.bmv1.product.format.errorCode.ErrorCode;
import com.c2psi.bmv1.product.format.mapper.FormatMapper;
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

@Service(value = "FormatServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FormatServiceImpl implements FormatService{
    final FormatDao formatDao;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;
    final FormatValidator formatValidator;
    final FormatMapper formatMapper;
    final AppService appService;
    final FormatSpecService formatSpecService;

    @Override
    public FormatDto saveFormat(FormatDto formatDto) {
        if(formatDto == null){
            throw new NullValueException("Le formatDto envoye pour enregistrement ne peut etre null");
        }
        List<String> errorsDto = formatValidator.validate(formatDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le formatDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.FORMAT_NOT_VALID.name());
        }
        List<String> errors = formatValidator.validate(formatMapper.dtoToEntity(formatDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("le format associe au formatDto n'est pas valide ", errors,
                    ErrorCode.FORMAT_NOT_VALID.name());
        }

        if(!isFormatnameInPosUsable(formatDto.getFormatName(), formatDto.getFormatPosId())){
            throw new DuplicateEntityException("Un format existe deja dans le pointofsale avec le meme nom ",
                    ErrorCode.FORMAT_DUPLICATED.name());
        }

//        Format formatToSave = Format.builder()
//                .formatCapacity(formatDto.getFormatCapacity())
//                .formatName(formatDto.getFormatName())
//                .formatPos(posMapper.dtoToEntity(posService.getPointofsaleById(formatDto.getFormatPosId())))
//                .build();
        Format formatToSave = formatMapper.dtoToEntity(formatDto);
        Format formatSaved = formatDao.save(formatToSave);
        return formatMapper.entityToDto(formatSaved);
    }

    Boolean isFormatnameInPosUsable(String formatName, Long posId){
        if((formatName == null) || (posId == null)){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Format> optionalFormat = formatDao.findFormatByAttributes(formatName, posId);
        return optionalFormat.isEmpty();
    }

    @Override
    public FormatDto updateFormat(FormatDto formatDto) {
        if(formatDto == null){
            throw new NullValueException("Le formatDto envoye pour update est null");
        }
        if(formatDto.getId() == null){
            throw new NullValueException("L'id du formatDto a update est null");
        }
        Optional<Format> optionalFormat = formatDao.findFormatById(formatDto.getId());
        if(!optionalFormat.isPresent()){
            throw new ModelNotFoundException("Aucun Format n'existe dans le systeme avec le Dto envoe",
                    ErrorCode.FORMAT_NOT_FOUND.name());
        }
        Format formatToUpdate = optionalFormat.get();
        List<String> errorsDto = formatValidator.validate(formatDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le formatDto envoye pour update n'est pas valide ", errorsDto,
                    ErrorCode.FORMAT_NOT_VALID.name());
        }

        List<String> errors = formatValidator.validate(formatMapper.dtoToEntity(formatDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("le format associe au formatDto n'est pas valide ", errors,
                    ErrorCode.FORMAT_NOT_VALID.name());
        }

        if(!formatToUpdate.getFormatPos().getId().equals(formatDto.getFormatPosId())){
            throw new InvalidEntityException("Il n'est pas possible de modifier le Pointofsale d'un format ",
                    ErrorCode.FORMAT_NOT_VALID.name());
        }

        if(!formatToUpdate.getFormatName().equalsIgnoreCase(formatDto.getFormatName())){
            if(!isFormatnameInPosUsable(formatDto.getFormatName(), formatDto.getFormatPosId())){
                throw new DuplicateEntityException("Il existe deja un format dans le pointofsale indique avec le meme " +
                        "nom", ErrorCode.FORMAT_DUPLICATED.name());
            }
            formatToUpdate.setFormatName(formatDto.getFormatName());
        }

        formatToUpdate.setFormatCapacity(formatDto.getFormatCapacity());
        Format formatUpdated = formatDao.save(formatToUpdate);

        return formatMapper.entityToDto(formatUpdated);
    }

    @Override
    public Boolean deleteFormatById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du format a supprimer est null");
        }
        Optional<Format> optionalFormat = formatDao.findFormatById(id);
        if(!optionalFormat.isPresent()){
            throw new ModelNotFoundException("Aucun Format n'existe avec l'id envoye ",
                    ErrorCode.FORMAT_NOT_FOUND.name());
        }
        if(!isFormatDeleteable(optionalFormat.get())){
            throw new EntityNotDeleatableException("Il n'est pas possible de supprimer ce format sans causer de conflit ",
                    ErrorCode.FORMAT_NOT_DELETEABLE.name());
        }
        formatDao.delete(optionalFormat.get());
        return true;
    }

    Boolean isFormatDeleteable(Format format){
        return true;
    }

    @Override
    public FormatDto getFormatById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du format a supprimer est null");
        }
        Optional<Format> optionalFormat = formatDao.findFormatById(id);
        if(!optionalFormat.isPresent()){
            throw new ModelNotFoundException("Aucun Format n'existe avec l'id envoye ",
                    ErrorCode.FORMAT_NOT_FOUND.name());
        }

        return formatMapper.entityToDto(optionalFormat.get());
    }

    @Override
    public List<FormatDto> getListofFormat(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return formatMapper.entityToDto(formatDao.findAll());
        }
        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return formatMapper.entityToDto(formatDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return formatMapper.entityToDto(formatDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Format> formatSpecification = formatSpecService.getFormatSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return formatMapper.entityToDto(formatDao.findAll(formatSpecification));
    }

    @Override
    public PageofFormatDto getPageofFormat(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Format> formatPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            formatPage = formatDao.findAll(pageable);
            return getPageofFormatDto(formatPage);
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
                formatPage = formatDao.findAll(pageable);
                return getPageofFormatDto(formatPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                formatPage = formatDao.findAll(pageable);
                return getPageofFormatDto(formatPage);
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
            Specification<Format> formatSpecification = formatSpecService.getFormatSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            formatPage = formatDao.findAll(formatSpecification, pageable);
            return getPageofFormatDto(formatPage);
        }
    }

    @Override
    public Boolean isFormatExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du format envoye est null");
        }
        Optional<Format> optionalFormat = formatDao.findFormatById(id);
        return optionalFormat.isPresent();
    }

    PageofFormatDto getPageofFormatDto(Page<Format> formatPage){
        PageofFormatDto pageofFormatDto = new PageofFormatDto();
        pageofFormatDto.setContent(formatMapper.entityToDto(formatPage.getContent()));
        pageofFormatDto.setCurrentPage(formatPage.getNumber());
        pageofFormatDto.setPageSize(formatPage.getSize());
        pageofFormatDto.setTotalElements(formatPage.getTotalElements());
        pageofFormatDto.setTotalPages(formatPage.getTotalPages());

        return pageofFormatDto;
    }

}
