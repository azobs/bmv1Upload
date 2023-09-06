package com.c2psi.bmv1.sale.backin.backin.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.sale.backin.backin.dao.BackinDao;
import com.c2psi.bmv1.sale.backin.backin.errorCode.ErrorCode;
import com.c2psi.bmv1.sale.backin.backin.mappers.BackinMapper;
import com.c2psi.bmv1.sale.backin.backin.models.Backin;
import com.c2psi.bmv1.sale.command.mappers.CommandMapper;
import com.c2psi.bmv1.sale.command.services.CommandService;
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

@Service(value = "BackinServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BackinServiceImpl implements BackinService{
    final AppService appService;
    final BackinValidator backinValidator;
    final BackinMapper backinMapper;
    final BackinDao backinDao;
    final BackinSpecService backinSpecService;
    final UserbmService userbmService;
    final UserbmMapper userbmMapper;
    final CommandService commandService;
    final CommandMapper commandMapper;

    @Override
    public BackinDto saveBackin(BackinDto backinDto) {
        log.info("We start saving the backin by checking if the backinDto is not null");
        if(backinDto == null){
            throw new NullValueException("Le backinDto envoye ne saurait etre null");
        }
        log.info("We continue saving the backin by validate the backinDto");
        List<String> errorsDto = backinValidator.validate(backinDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le backinDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.BACKIN_NOT_VALID.name());
        }
        log.info("We continue saving the backin by validate the backin associate backinDto");
        List<String> errors = backinValidator.validate(backinMapper.dtoToEntity(backinDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le backinDto envoye n'est pas valide ", errors,
                    ErrorCode.BACKIN_NOT_VALID.name());
        }
        log.info("We continue saving the backin by ensure that the backinDto will be unique");
        if(!isBackinOfCommandExist(backinDto.getBiCommandId())){
            throw new DuplicateEntityException("Il existe deja un backin pour la command indique ",
                    ErrorCode.BACKIN_DUPLICATED.name());
        }
        log.info("We continue saving the backin by preparing the backinDto to save");
        //Backin backinToSave = backinMapper.dtoToEntity(backinDto);
        /***
         * backinToUpdate.setBiCommand(commandMapper.dtoToEntity(commandService.getCommandById(backinDto.getBiCommandId())));
         *         backinToUpdate.setBiSaler(userbmMapper.dtoToEntity(userbmService.getUserbmById(backinDto.getBiSalerId())));
         *         backinToUpdate.setBiDate(backinDto.getBiDate());
         *         backinToUpdate.setBiComment(backinDto.getBiComment());
         */

        Backin backinToSave = Backin.builder()
                .biCommand(commandMapper.dtoToEntity(commandService.getCommandById(backinDto.getBiCommandId())))
                .biSaler(userbmMapper.dtoToEntity(userbmService.getUserbmById(backinDto.getBiSalerId())))
                .biDate(backinDto.getBiDate())
                .biComment(backinDto.getBiComment())
                .build();

        return backinMapper.entityToDto(backinDao.save(backinToSave));
    }

    Boolean isBackinOfCommandExist(Long commandId){
        if(commandId == null){
            throw new NullValueException("Le commandId envoye ne saurait etre null");
        }
        Optional<Backin> optionalBackin = backinDao.findBackinOfCommand(commandId);
        return optionalBackin.isEmpty();
    }

    @Override
    public BackinDto updateBackin(BackinDto backinDto) {
        log.info("We start updating the backin by checking if the backinDto is not null");
        if(backinDto == null){
            throw new NullValueException("Le backinDto envoye ne saurait etre null");
        }
        log.info("We continue updating the backin by checking if the backinDtoId is not null");
        if(backinDto.getId() == null){
            throw new NullValueException("L'id du backinDto ne saurait etre null");
        }
        log.info("We continue updating the backin by validate the backinDto");
        List<String> errorsDto = backinValidator.validate(backinDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le backinDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.BACKIN_NOT_VALID.name());
        }
        log.info("We continue updating the backin by validate the backin associate backinDto");
        List<String> errors = backinValidator.validate(backinMapper.dtoToEntity(backinDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le backinDto envoye n'est pas valide ", errors,
                    ErrorCode.BACKIN_NOT_VALID.name());
        }
        log.info("We continue updating the backin by retrieve the backinDto to Update");
        Optional<Backin> optionalBackin = backinDao.findBackinById(backinDto.getId());
        if(!optionalBackin.isPresent()){
            throw new ModelNotFoundException("Aucun Backin n'existe dans le systeme avec l'id envoye");
        }
        Backin backinToUpdate = optionalBackin.get();
        log.info("We continue updating the backin by ensure that the backinDto will be unique");
        boolean commandToChange = !backinToUpdate.getBiCommand().equals(backinDto.getBiCommandId());
        if(commandToChange){
            if(!isBackinOfCommandExist(backinDto.getBiCommandId())){
                throw new DuplicateEntityException("Il existe deja un Backin dans le systeme pour la commande indique ",
                        ErrorCode.BACKIN_DUPLICATED.name());
            }
        }
        log.info("We continue updating the backin by preparing the backinDto to update");
        backinToUpdate.setBiCommand(commandMapper.dtoToEntity(commandService.getCommandById(backinDto.getBiCommandId())));
        backinToUpdate.setBiSaler(userbmMapper.dtoToEntity(userbmService.getUserbmById(backinDto.getBiSalerId())));
        backinToUpdate.setBiDate(backinDto.getBiDate());
        backinToUpdate.setBiComment(backinDto.getBiComment());

        return backinMapper.entityToDto(backinDao.save(backinToUpdate));
    }

    @Override
    public Boolean deleteBackinById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du Backin envoye ne saurait etre null");
        }
        Optional<Backin> optionalBackin = backinDao.findBackinById(id);
        if(!optionalBackin.isPresent()){
            throw new ModelNotFoundException("Aucun Backin n'existe dans le systeme avec l'id envoye",
                    ErrorCode.BACKIN_NOT_FOUND.name());
        }
        if(!isBackinDeleteable(optionalBackin.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer le Backin sans causer de conflit ",
                    ErrorCode.BACKIN_NOT_DELETEABLE.name());
        }
        backinDao.delete(optionalBackin.get());
        return true;
    }

    Boolean isBackinDeleteable(Backin backin){
        return true;
    }

    @Override
    public BackinDto getBackinById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du Backin envoye ne saurait etre null");
        }
        Optional<Backin> optionalBackin = backinDao.findBackinById(id);
        if(!optionalBackin.isPresent()){
            throw new ModelNotFoundException("Aucun Backin n'existe dans le systeme avec l'id envoye",
                    ErrorCode.BACKIN_NOT_FOUND.name());
        }
        return backinMapper.entityToDto(optionalBackin.get());
    }

    @Override
    public List<BackinDto> getListofBackin(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return backinMapper.entityToDto(backinDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return backinMapper.entityToDto(backinDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return backinMapper.entityToDto(backinDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Backin> backinSpecification = backinSpecService.getBackinSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return backinMapper.entityToDto(backinDao.findAll(backinSpecification));
    }

    @Override
    public PageofBackinDto getPageofBackin(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Backin> backinPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            backinPage = backinDao.findAll(pageable);
            return getPageofBackinDto(backinPage);
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
                backinPage = backinDao.findAll(pageable);
                return getPageofBackinDto(backinPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                backinPage = backinDao.findAll(pageable);
                return getPageofBackinDto(backinPage);
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
            Specification<Backin> backinSpecification = backinSpecService.getBackinSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            backinPage = backinDao.findAll(backinSpecification, pageable);
            return getPageofBackinDto(backinPage);
        }
    }

    @Override
    public Boolean isBackinExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du Backin envoye ne saurait etre null");
        }
        Optional<Backin> optionalBackin = backinDao.findBackinById(id);
        return optionalBackin.isPresent();
    }

    PageofBackinDto getPageofBackinDto(Page<Backin> backinPage){
        PageofBackinDto pageofBackinDto = new PageofBackinDto();
        pageofBackinDto.setContent(backinMapper.entityToDto(backinPage.getContent()));
        pageofBackinDto.setCurrentPage(backinPage.getNumber());
        pageofBackinDto.setPageSize(backinPage.getSize());
        pageofBackinDto.setTotalElements(backinPage.getTotalElements());
        pageofBackinDto.setTotalPages(backinPage.getTotalPages());

        return pageofBackinDto;

    }

}
