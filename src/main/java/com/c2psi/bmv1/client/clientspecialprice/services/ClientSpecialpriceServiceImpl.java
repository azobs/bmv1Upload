package com.c2psi.bmv1.client.clientspecialprice.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.client.client.mappers.ClientMapper;
import com.c2psi.bmv1.client.client.services.ClientService;
import com.c2psi.bmv1.client.clientspecialprice.dao.ClientSpecialpriceDao;
import com.c2psi.bmv1.client.clientspecialprice.errorCode.ErrorCode;
import com.c2psi.bmv1.client.clientspecialprice.mappers.ClientSpecialpriceMapper;
import com.c2psi.bmv1.client.clientspecialprice.models.ClientSpecialprice;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.price.specialprice.mappers.SpecialpriceMapper;
import com.c2psi.bmv1.price.specialprice.services.SpecialpriceService;
import com.c2psi.bmv1.product.article.mappers.ArticleMapper;
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

@Service(value = "ClientSpecialpriceServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClientSpecialpriceServiceImpl implements ClientSpecialpriceService{

    final AppService appService;
    final ClientSpecialpriceValidator clientSpecialpriceValidator;
    final ClientSpecialpriceMapper clientSpecialpriceMapper;
    final ClientSpecialpriceDao clientSpecialpriceDao;
    final ClientSpecialpriceSpecService clientSpecialpriceSpecService;
    final ClientService clientService;
    final ClientMapper clientMapper;
    final ArticleService articleService;
    final ArticleMapper articleMapper;
    final SpecialpriceService specialpriceService;
    final SpecialpriceMapper specialpriceMapper;

    @Override
    public ClientSpecialpriceDto saveClientSpecialprice(ClientSpecialpriceDto clientSpecialpriceDto) {
        log.info("The saving process start by ensure that ClientSpecialpriceDto is not null");
        if(clientSpecialpriceDto == null){
            throw new NullValueException("Le clientspecialpriceDto envoye ne saurait etre null");
        }
        log.info("The saving process continue by validate the clientspecialpriceDto");
        List<String> errorsDto = clientSpecialpriceValidator.validate(clientSpecialpriceDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le clientspecialpriceDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.CLIENTSPECIALPRICE_NOT_VALID.name());
        }
        log.info("The saving process continue by validate the clientspecialprice");
        List<String> errors = clientSpecialpriceValidator.validate(clientSpecialpriceMapper.dtoToEntity(clientSpecialpriceDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le clientspecialprice envoye n'est pas valide ", errors,
                    ErrorCode.CLIENTSPECIALPRICE_NOT_VALID.name());
        }
        log.info("The saving process continue by ensure that clientspecialprice will be unique");
        if(!isClientSpecialpriceUnique(clientSpecialpriceDto.getClientId(), clientSpecialpriceDto.getSpecialpriceId(),
                clientSpecialpriceDto.getArticleId())){
            throw new DuplicateEntityException("Il existe deja un clientspecialprice avec les meme attributs dans le " +
                    "systeme ", ErrorCode.CLIENTSPECIALPRICE_DUPLICATED.name());
        }
        log.info("The saving process continue by preparing the clientspecialprice to save");
        //ClientSpecialprice clientSpecialpriceToSave = clientSpecialpriceMapper.dtoToEntity(clientSpecialpriceDto);
        /******************
         * clientSpecialpriceToUpdate.setSpecialprice(specialpriceMapper.dtoToEntity(specialpriceService.
         *                 getSpecialpriceById(clientSpecialpriceDto.getSpecialpriceId())));
         *         clientSpecialpriceToUpdate.setClient(clientMapper.dtoToEntity(clientService.getClientById(
         *                 clientSpecialpriceDto.getClientId())));
         *         clientSpecialpriceToUpdate.setArticle(articleMapper.dtoToEntity(articleService.getArticleById(
         *                 clientSpecialpriceDto.getArticleId())));
         *         clientSpecialpriceToUpdate.setAppliedDate(clientSpecialpriceDto.getAppliedDate());
         */
        ClientSpecialprice clientSpecialpriceToSave = ClientSpecialprice.builder()
                .specialprice(specialpriceMapper.dtoToEntity(specialpriceService.getSpecialpriceById(
                        clientSpecialpriceDto.getSpecialpriceId())))
                .client(clientMapper.dtoToEntity(clientService.getClientById(clientSpecialpriceDto.getClientId())))
                .article(articleMapper.dtoToEntity(articleService.getArticleById(clientSpecialpriceDto.getArticleId())))
                .appliedDate(clientSpecialpriceDto.getAppliedDate())
                .build();
        return clientSpecialpriceMapper.entityToDto(clientSpecialpriceDao.save(clientSpecialpriceToSave));
    }

    Boolean isClientSpecialpriceUnique(Long clientId, Long specialpriceId, Long articleId){
        if(clientId == null || specialpriceId == null || articleId == null){
            throw new NullValueException("les arguments envoyes sont nuls");
        }
        Optional<ClientSpecialprice> optionalClientSpecialprice = clientSpecialpriceDao.
                findClientSpecialpriceByAttributes(clientId, specialpriceId, articleId);
        return optionalClientSpecialprice.isEmpty();
    }

    @Override
    public ClientSpecialpriceDto updateClientSpecialprice(ClientSpecialpriceDto clientSpecialpriceDto) {
        log.info("The updating process start by ensure that ClientSpecialpriceDto is not null");
        if(clientSpecialpriceDto == null){
            throw new NullValueException("Le cleintspecialpriceDto a update ne saurait etre null");
        }
        log.info("The updating process continue by ensure that ClientSpecialpriceDtoId is not null");
        if(clientSpecialpriceDto.getId() == null){
            throw new NullValueException("L'id du clientspecialpriceDto a valider ne saurait etre null");
        }
        log.info("The updating process continue by validate the clientspecialpriceDto");
        List<String> errorsDto = clientSpecialpriceValidator.validate(clientSpecialpriceDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le clientspecialpriceDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.CLIENTSPECIALPRICE_NOT_VALID.name());
        }
        log.info("The updating process continue by validate the clientspecialprice");
        List<String> errors = clientSpecialpriceValidator.validate(clientSpecialpriceMapper.dtoToEntity(clientSpecialpriceDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le clientspecialprice envoye n'est pas valide ", errors,
                    ErrorCode.CLIENTSPECIALPRICE_NOT_VALID.name());
        }
        log.info("The updating process continue by retrieve the clientspecialprice to validate");
        Optional<ClientSpecialprice> optionalClientSpecialprice = clientSpecialpriceDao.findClientSpecialpriceById(
                clientSpecialpriceDto.getId());
        if(!optionalClientSpecialprice.isPresent()){
            throw new ModelNotFoundException("Aucun clientspecialprice n'existe dans le systeme avec l'id envoye dans " +
                    "le Dto ", ErrorCode.CLIENTSPECIALPRICE_NOT_FOUND.name());
        }
        ClientSpecialprice clientSpecialpriceToUpdate = optionalClientSpecialprice.get();
        log.info("The updating process continue by ensure that if the clientspecialpriceDto will be unique if modified");
        boolean clientToUpdate = !clientSpecialpriceToUpdate.getClient().getId().equals(clientSpecialpriceDto.getClientId());
        boolean specialpriceToUpdate = !clientSpecialpriceToUpdate.getSpecialprice().getId().equals(clientSpecialpriceDto.getSpecialpriceId());
        boolean articleToUpdate = !clientSpecialpriceToUpdate.getArticle().getId().equals(clientSpecialpriceDto.getArticleId());
        if(clientToUpdate || specialpriceToUpdate || articleToUpdate){
            if(!isClientSpecialpriceUnique(clientSpecialpriceDto.getClientId(), clientSpecialpriceDto.getSpecialpriceId(),
                    clientSpecialpriceDto.getArticleId())){
                throw new DuplicateEntityException("Il existe deja un clientspecialprice avec les meme attributs dans le " +
                        "systeme ", ErrorCode.CLIENTSPECIALPRICE_DUPLICATED.name());
            }
        }
        log.info("The updating process continue by preparing the clientspecialprice to Update");
        clientSpecialpriceToUpdate.setSpecialprice(specialpriceMapper.dtoToEntity(specialpriceService.
                getSpecialpriceById(clientSpecialpriceDto.getSpecialpriceId())));
        clientSpecialpriceToUpdate.setClient(clientMapper.dtoToEntity(clientService.getClientById(
                clientSpecialpriceDto.getClientId())));
        clientSpecialpriceToUpdate.setArticle(articleMapper.dtoToEntity(articleService.getArticleById(
                clientSpecialpriceDto.getArticleId())));
        clientSpecialpriceToUpdate.setAppliedDate(clientSpecialpriceDto.getAppliedDate());

        return clientSpecialpriceMapper.entityToDto(clientSpecialpriceDao.save(clientSpecialpriceToUpdate));
    }

    @Override
    public Boolean deleteClientSpecialpriceById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du clientspecialprice a delete envoye ne saurait etre null");
        }
        Optional<ClientSpecialprice> optionalClientSpecialprice = clientSpecialpriceDao.findClientSpecialpriceById(id);
        if(!optionalClientSpecialprice.isPresent()){
            throw new ModelNotFoundException("Aucun clientspecialprice n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.CLIENTSPECIALPRICE_NOT_FOUND.name());
        }
        if(!isClientspecialpriceDeleteable(optionalClientSpecialprice.get())){
            throw new EntityNotDeleatableException("On ne peut delete ce clientspecialprice sans causer de conflit ",
                    ErrorCode.CLIENTSPECIALPRICE_NOT_DELETEABLE.name());
        }
        clientSpecialpriceDao.delete(optionalClientSpecialprice.get());
        return true;
    }

    Boolean isClientspecialpriceDeleteable(ClientSpecialprice clientSpecialprice){
        return true;
    }

    @Override
    public ClientSpecialpriceDto getClientSpecialpriceById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du clientspecialprice a delete envoye ne saurait etre null");
        }
        Optional<ClientSpecialprice> optionalClientSpecialprice = clientSpecialpriceDao.findClientSpecialpriceById(id);
        if(!optionalClientSpecialprice.isPresent()){
            throw new ModelNotFoundException("Aucun clientspecialprice n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.CLIENTSPECIALPRICE_NOT_FOUND.name());
        }
        return clientSpecialpriceMapper.entityToDto(optionalClientSpecialprice.get());
    }

    @Override
    public List<ClientSpecialpriceDto> getListofClientSpecialprice(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return clientSpecialpriceMapper.entityToDto(clientSpecialpriceDao.findAll());
        }
        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return clientSpecialpriceMapper.entityToDto(clientSpecialpriceDao.findAll());
        }
        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return clientSpecialpriceMapper.entityToDto(clientSpecialpriceDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<ClientSpecialprice> clientspecialpriceSpecification = clientSpecialpriceSpecService.
                getClientSpecialpriceSpecification(filterRequest.getFilters(), filterRequest.getLogicOperator(),
                        filterRequest.getOrderby());
        return clientSpecialpriceMapper.entityToDto(clientSpecialpriceDao.findAll(clientspecialpriceSpecification));
    }

    @Override
    public PageofClientSpecialpriceDto getPageofClientSpecialprice(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<ClientSpecialprice> clientspecialpricePage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            clientspecialpricePage = clientSpecialpriceDao.findAll(pageable);
            return getPageofClientSpecialpriceDto(clientspecialpricePage);
        }
        else {
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
                clientspecialpricePage = clientSpecialpriceDao.findAll(pageable);
                return getPageofClientSpecialpriceDto(clientspecialpricePage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if (filterRequest.getFilters() == null && filterRequest.getOrderby() != null) {
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                clientspecialpricePage = clientSpecialpriceDao.findAll(pageable);
                return getPageofClientSpecialpriceDto(clientspecialpricePage);
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
            Specification<ClientSpecialprice> clientSpecification = clientSpecialpriceSpecService.
                    getClientSpecialpriceSpecification(filterRequest.getFilters(), filterRequest.getLogicOperator(),
                            filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            clientspecialpricePage = clientSpecialpriceDao.findAll(clientSpecification, pageable);
            return getPageofClientSpecialpriceDto(clientspecialpricePage);
        }
    }

    @Override
    public Boolean isClientSpecialpriceExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du clientspecialprice a delete envoye ne saurait etre null");
        }
        Optional<ClientSpecialprice> optionalClientSpecialprice = clientSpecialpriceDao.findClientSpecialpriceById(id);
        return optionalClientSpecialprice.isPresent();
    }

    PageofClientSpecialpriceDto getPageofClientSpecialpriceDto(Page<ClientSpecialprice> clientspecialpricePage){
        PageofClientSpecialpriceDto pageofClientSpecialpriceDto = new PageofClientSpecialpriceDto();
        pageofClientSpecialpriceDto.setContent(clientSpecialpriceMapper.entityToDto(clientspecialpricePage.getContent()));
        pageofClientSpecialpriceDto.setCurrentPage(clientspecialpricePage.getNumber());
        pageofClientSpecialpriceDto.setPageSize(clientspecialpricePage.getSize());
        pageofClientSpecialpriceDto.setTotalElements(clientspecialpricePage.getTotalElements());
        pageofClientSpecialpriceDto.setTotalPages(clientspecialpricePage.getTotalPages());

        return pageofClientSpecialpriceDto;
    }

}
