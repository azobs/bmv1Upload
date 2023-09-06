package com.c2psi.bmv1.client.client.services;

import com.c2psi.bmv1.address.mappers.AddressMapper;
import com.c2psi.bmv1.address.models.Address;
import com.c2psi.bmv1.address.services.AddressService;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.client.client.dao.ClientDao;
import com.c2psi.bmv1.client.client.mappers.ClientMapper;
import com.c2psi.bmv1.client.client.errorCode.ErrorCode;
import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.client.client.models.Client;
import com.c2psi.bmv1.client.client.models.Client;
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

@Service(value = "ClientServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService{
    final AppService appService;
    final ClientDao clientDao;
    final ClientValidator clientValidator;
    final ClientMapper clientMapper;
    final ClientSpecService clientSpecService;
    final AddressService addressService;
    final AddressMapper addressMapper;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;

    @Override
    public ClientDto saveClient(ClientDto clientDto) {
        log.info("The client saving process start by checking if the clientDto is not null");
        if(clientDto == null){
            throw new NullValueException("Le clientDto envoye est null");
        }
        log.info("We continue the process by the validation process");
        List<String> errorsDto = clientValidator.validate(clientDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le clientDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.CLIENT_NOT_VALID.name());
        }
        List<String> errors = clientValidator.validate(clientMapper.dtoToEntity(clientDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le client envoye n'est pas valide ", errors,
                    ErrorCode.CLIENT_NOT_VALID.name());
        }
        log.info("We continue the process by checking if all the constraint is not violated");
        if(!isClientAttributesUsable(clientDto.getClientName(), clientDto.getClientOthername(),
                clientDto.getClientPosId())){
            throw new DuplicateEntityException("Il existe deja un client pour le meme pointofsale avec le meme nom et " +
                    "le meme acronym ", ErrorCode.CLIENT_DUPLICATED.name());
        }
        if(!isClientCniUsableInPos(clientDto.getClientCni(), clientDto.getClientPosId())){
            throw new DuplicateEntityException("Il existe deja un client pour le meme pointofsale avec le meme cni " +
                    "number", ErrorCode.CLIENT_DUPLICATED.name());
        }
        if(!isClientEmailUsable(clientDto.getClientAddress().getEmail())){
            throw new DuplicateEntityException("Il existe deja un client avec le meme email dans le systeme ",
                    ErrorCode.CLIENT_DUPLICATED.name());
        }
        log.info("We can prepare now the client to save");

        //Client clientToSave = clientMapper.dtoToEntity(clientDto);
        /******
         *  clientToUpdate.setClientOthername(clientDto.getClientOthername());
         *         clientToUpdate.setClientBalance(clientDto.getClientBalance());
         *         clientToUpdate.setClientCni(clientDto.getClientCni());
         *         clientToUpdate.setClientName(clientDto.getClientName());
         */
        Client clientToSave = Client.builder()
                .clientAddress(Address.builder()
                        .numtel1(clientDto.getClientAddress().getNumtel1())
                        .numtel2(clientDto.getClientAddress().getNumtel2())
                        .numtel3(clientDto.getClientAddress().getNumtel3())
                        .quarter(clientDto.getClientAddress().getQuarter())
                        .country(clientDto.getClientAddress().getCountry())
                        .town(clientDto.getClientAddress().getTown())
                        .localisation(clientDto.getClientAddress().getLocalisation())
                        .email(clientDto.getClientAddress().getEmail())
                        .build())
                .clientBalance(clientDto.getClientBalance())
                .clientCni(clientDto.getClientCni())
                .clientName(clientDto.getClientName())
                .clientOthername(clientDto.getClientOthername())
                .build();

        log.info("We can prepare now register the client");
        Client clientSaved = clientDao.save(clientToSave);
        return clientMapper.entityToDto(clientSaved);
    }

    Boolean isClientAttributesUsable(String clientName, String clientOthername, Long posId){
        if(clientName == null || clientOthername == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Client> optionalClient = clientDao.findClientByFullname(clientName,
                clientOthername, posId);
        return optionalClient.isEmpty();
    }

    Boolean isClientCniUsableInPos(String clientCni, Long posId){
        if(clientCni == null ||  posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Client> optionalClient = clientDao.findClientByClientCniInPos(clientCni, posId);
        return optionalClient.isEmpty();
    }

    Boolean isClientEmailUsable(String clientEmail){
        return addressService.isEmailAddressUnique(clientEmail);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        log.info("The updating process start by ensure that clientDto is not null");
        if(clientDto == null){
            throw new NullValueException("Le clientDto envoye est null");
        }
        log.info("We continue by ensuring that the id of the clientDto is not null");
        if(clientDto.getId() == null){
            throw new NullValueException("L'id du client a update ne saurait etre null");
        }
        log.info("We continue by validate the client Dto and the client associate");
        List<String> errorsDto = clientValidator.validate(clientDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le clientDto envoye n'est pas valide ", errorsDto,
                    com.c2psi.bmv1.client.client.errorCode.ErrorCode.CLIENT_NOT_VALID.name());
        }
        List<String> errors = clientValidator.validate(clientMapper.dtoToEntity(clientDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le client envoye n'est pas valide ", errors,
                    com.c2psi.bmv1.client.client.errorCode.ErrorCode.CLIENT_NOT_VALID.name());
        }
        log.info("We continue by retrieve in the DB the client to update");
        Optional<Client> optionalClient = clientDao.findClientById(clientDto.getId());
        if(!optionalClient.isPresent()){
            throw new ModelNotFoundException("Aucun client n'existe dans le systeme avec l'id envoye ",
                    com.c2psi.bmv1.client.client.errorCode.ErrorCode.CLIENT_NOT_FOUND.name());
        }
        Client clientToUpdate = optionalClient.get();

        log.info("We continue by update the address");
        addressService.updateAddress(clientDto.getClientAddress());

        log.info("We continue by ensuring that the pointofsale has not change");
        if(!clientToUpdate.getClientPos().getId().equals(clientDto.getClientPosId())){
            throw new InvalidEntityException("client not valid car Il n'est pas possible de changer le pointofsale " +
                    "d'un client ", com.c2psi.bmv1.client.client.errorCode.ErrorCode.CLIENT_NOT_VALID.name());
        }

        log.info("We continue by ensure that all the constraint can't be violated");
        boolean clientNameToChange = !clientToUpdate.getClientName().equals(clientDto.getClientName());
        boolean clientAcronymToChange = !clientToUpdate.getClientOthername().equals(clientDto.getClientOthername());
        if(clientNameToChange || clientAcronymToChange) {
            if (!isClientAttributesUsable(clientDto.getClientName(), clientDto.getClientOthername(),
                    clientDto.getClientPosId())) {
                throw new DuplicateEntityException("Il existe deja un client dans le systeme pour le meme pointofsale " +
                        "avec le meme nom et le meme acronym ", com.c2psi.bmv1.client.client.errorCode.ErrorCode.CLIENT_DUPLICATED.name());
            }
        }

        if(clientDto.getClientCni() != null && clientToUpdate.getClientCni() != null) {
            if (!clientToUpdate.getClientCni().equals(clientDto.getClientCni())) {
                if (!isClientCniUsableInPos(clientDto.getClientCni(), clientDto.getClientPosId())) {
                    throw new DuplicateEntityException("Il existe deja un client pour le meme pointofsale avec le meme cni " +
                            "number", ErrorCode.CLIENT_DUPLICATED.name());
                }
            }
        }

        log.info("We can continue by preparing the updating process");

        clientToUpdate.setClientOthername(clientDto.getClientOthername());
        clientToUpdate.setClientBalance(clientDto.getClientBalance());
        clientToUpdate.setClientCni(clientDto.getClientCni());
        clientToUpdate.setClientName(clientDto.getClientName());


        log.info("We can now launch the updating process");
        Client clientUpdated = clientDao.save(clientToUpdate);
        return clientMapper.entityToDto(clientUpdated);
    }

    @Override
    public Boolean deleteClientById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du client a supprimer est null");
        }
        Optional<Client> optionalClient = clientDao.findClientById(id);
        if(!optionalClient.isPresent()){
            throw new ModelNotFoundException("Aucun client n'existe avec l'id envoye",
                    com.c2psi.bmv1.client.client.errorCode.ErrorCode.CLIENT_NOT_FOUND.name());
        }

        if(!isClientDeleteable(optionalClient.get())){
            throw new EntityNotDeleatableException("IL n'est pas possible de supprimer ce client sans causer " +
                    "de conflit ", com.c2psi.bmv1.client.client.errorCode.ErrorCode.CLIENT_NOT_DELETEABLE.name());
        }
        clientDao.delete(optionalClient.get());
        return true;
    }

    Boolean isClientDeleteable(Client client){
        return true;
    }

    @Override
    public ClientDto getClientById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du client a supprimer est null");
        }
        Optional<Client> optionalClient = clientDao.findClientById(id);
        if(!optionalClient.isPresent()){
            throw new ModelNotFoundException("Aucun client n'existe avec l'id envoye",
                    com.c2psi.bmv1.client.client.errorCode.ErrorCode.CLIENT_NOT_FOUND.name());
        }

        return clientMapper.entityToDto(optionalClient.get());
    }

    @Override
    public List<ClientDto> getListofClient(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return clientMapper.entityToDto(clientDao.findAll());
        }
        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return clientMapper.entityToDto(clientDao.findAll());
        }
        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return clientMapper.entityToDto(clientDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Client> clientSpecification = clientSpecService.getClientSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return clientMapper.entityToDto(clientDao.findAll(clientSpecification));
    }

    @Override
    public PageofClientDto getPageofClient(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Client> clientPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            clientPage = clientDao.findAll(pageable);
            return getPageofClientDto(clientPage);
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
                clientPage = clientDao.findAll(pageable);
                return getPageofClientDto(clientPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if (filterRequest.getFilters() == null && filterRequest.getOrderby() != null) {
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                clientPage = clientDao.findAll(pageable);
                return getPageofClientDto(clientPage);
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
            Specification<Client> clientSpecification = clientSpecService.getClientSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            clientPage = clientDao.findAll(clientSpecification, pageable);
            return getPageofClientDto(clientPage);
        }
    }

    @Override
    public Boolean isClientExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du client a supprimer est null");
        }
        Optional<Client> optionalClient = clientDao.findClientById(id);
        return optionalClient.isPresent();
    }

    PageofClientDto getPageofClientDto(Page<Client> clientPage){
        PageofClientDto pageofClientDto = new PageofClientDto();
        pageofClientDto.setContent(clientMapper.entityToDto(clientPage.getContent()));
        pageofClientDto.setCurrentPage(clientPage.getNumber());
        pageofClientDto.setPageSize(clientPage.getSize());
        pageofClientDto.setTotalElements(clientPage.getTotalElements());
        pageofClientDto.setTotalPages(clientPage.getTotalPages());

        return pageofClientDto;
    }

}
