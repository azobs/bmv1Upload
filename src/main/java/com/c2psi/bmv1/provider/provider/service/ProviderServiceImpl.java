package com.c2psi.bmv1.provider.provider.service;

import com.c2psi.bmv1.address.models.Address;
import com.c2psi.bmv1.address.services.AddressService;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.provider.provider.dao.ProviderDao;
import com.c2psi.bmv1.provider.provider.errorCode.ErrorCode;
import com.c2psi.bmv1.provider.provider.mappers.ProviderMapper;
import com.c2psi.bmv1.provider.provider.models.Provider;
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

@Service(value = "ProviderServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProviderServiceImpl implements ProviderService{
    final AppService appService;
    final ProviderDao providerDao;
    final ProviderValidator providerValidator;
    final ProviderMapper providerMapper;
    final ProviderSpecService providerSpecService;
    final AddressService addressService;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;

    @Override
    public ProviderDto saveProvider(ProviderDto providerDto) {
        log.info("The provider saving process start by checking if the providerDto is not null");
        if(providerDto == null){
            throw new NullValueException("Le providerDto envoye est null");
        }
        log.info("We continue the process by the validation process");
        List<String> errorsDto = providerValidator.validate(providerDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le providerDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.PROVIDER_NOT_VALID.name());
        }
        List<String> errors = providerValidator.validate(providerMapper.dtoToEntity(providerDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le provider envoye n'est pas valide ", errors,
                    ErrorCode.PROVIDER_NOT_VALID.name());
        }
        log.info("We continue the process by checking if all the constraint is not violated");
        if(!isProviderAttributesUsable(providerDto.getProviderName(), providerDto.getProviderAcronym(),
                providerDto.getProviderPosId())){
            throw new DuplicateEntityException("Il existe deja un provider pour le meme pointofsale avec le meme nom et " +
                    "le meme acronym ", ErrorCode.PROVIDER_DUPLICATED.name());
        }
        if(!isProviderEmailUsable(providerDto.getProviderAddress().getEmail())){
            throw new DuplicateEntityException("Il existe deja un provider avec le meme email dans le systeme ",
                    ErrorCode.PROVIDER_DUPLICATED.name());
        }
        log.info("We can prepare now the provider to save");

        Provider providerToSave = Provider.builder()
                .providerName(providerDto.getProviderName())
                .providerAcronym(providerDto.getProviderAcronym())
                .providerDescription(providerDto.getProviderDescription())
                .providerBalance(providerDto.getProviderBalance())
                .providerPos(posMapper.dtoToEntity(posService.getPointofsaleById(providerDto.getProviderPosId())))
                .providerAddress(
                        Address.builder()
                                .numtel1(providerDto.getProviderAddress().getNumtel1())
                                .numtel2(providerDto.getProviderAddress().getNumtel2())
                                .numtel3(providerDto.getProviderAddress().getNumtel3())
                                .quarter(providerDto.getProviderAddress().getQuarter())
                                .country(providerDto.getProviderAddress().getCountry())
                                .town(providerDto.getProviderAddress().getTown())
                                .localisation(providerDto.getProviderAddress().getLocalisation())
                                .email(providerDto.getProviderAddress().getEmail())
                                .build()
                )
                .build();

        //Provider providerToSave = providerMapper.dtoToEntity(providerDto);

        log.info("We can prepare now register the provider");
        Provider providerSaved = providerDao.save(providerToSave);
        return providerMapper.entityToDto(providerSaved);
    }

    Boolean isProviderAttributesUsable(String providerName, String providerAcronym, Long posId){
        if(providerName == null || providerAcronym == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Provider> optionalProvider = providerDao.findProviderByNameAndAcronymInPos(providerName,
                providerAcronym, posId);
        return optionalProvider.isEmpty();
    }

    Boolean isProviderEmailUsable(String providerEmail){
        return addressService.isEmailAddressUnique(providerEmail);
    }

    @Override
    public ProviderDto updateProvider(ProviderDto providerDto) {
        log.info("The updating process start by ensure that providerDto is not null");
        if(providerDto == null){
            throw new NullValueException("Le providerDto envoye est null");
        }
        log.info("We continue by ensuring that the id of the providerDto is not null");
        if(providerDto.getId() == null){
            throw new NullValueException("L'id du provider a update ne saurait etre null");
        }
        log.info("We continue by validate the provider Dto and the provider associate");
        List<String> errorsDto = providerValidator.validate(providerDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le providerDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.PROVIDER_NOT_VALID.name());
        }
        List<String> errors = providerValidator.validate(providerMapper.dtoToEntity(providerDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le provider envoye n'est pas valide ", errors,
                    ErrorCode.PROVIDER_NOT_VALID.name());
        }
        log.info("We continue by retrieve in the DB the provider to update");
        Optional<Provider> optionalProvider = providerDao.findProviderById(providerDto.getId());
        if(!optionalProvider.isPresent()){
            throw new ModelNotFoundException("Aucun provider n'existe dans le systeme avec l'id envoye ",
                    ErrorCode.PROVIDER_NOT_FOUND.name());
        }
        Provider providerToUpdate = optionalProvider.get();

        log.info("We continue by update the address");
        addressService.updateAddress(providerDto.getProviderAddress());

        log.info("We continue by ensuring that the pointofsale has not change");
        if(!providerToUpdate.getProviderPos().getId().equals(providerDto.getProviderPosId())){
            throw new InvalidEntityException("provider not valid car Il n'est pas possible de changer le pointofsale " +
                    "d'un provider ", ErrorCode.PROVIDER_NOT_VALID.name());
        }

        log.info("We continue by ensure that all the constraint can't be violated");
        boolean providerNameToChange = !providerToUpdate.getProviderName().equals(providerDto.getProviderName());
        boolean providerAcronymToChange = !providerToUpdate.getProviderAcronym().equals(providerDto.getProviderAcronym());
        if(providerNameToChange || providerAcronymToChange) {
            if (!isProviderAttributesUsable(providerDto.getProviderName(), providerDto.getProviderAcronym(),
                    providerDto.getProviderPosId())) {
                throw new DuplicateEntityException("Il existe deja un provider dans le systeme pour le meme pointofsale " +
                        "avec le meme nom et le meme acronym ", ErrorCode.PROVIDER_DUPLICATED.name());
            }
        }
        log.info("We can continue by preparing the updating process");

        providerToUpdate.setProviderAcronym(providerDto.getProviderAcronym());
        providerToUpdate.setProviderBalance(providerDto.getProviderBalance());
        providerToUpdate.setProviderDescription(providerDto.getProviderDescription());
        providerToUpdate.setProviderName(providerDto.getProviderName());
        log.info("We can now launch the updating process");
        Provider providerUpdated = providerDao.save(providerToUpdate);
        return providerMapper.entityToDto(providerUpdated);
    }

    @Override
    public Boolean deleteProviderById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du provider a supprimer est null");
        }
        Optional<Provider> optionalProvider = providerDao.findProviderById(id);
        if(!optionalProvider.isPresent()){
            throw new ModelNotFoundException("Aucun provider n'existe avec l'id envoye",
                    ErrorCode.PROVIDER_NOT_FOUND.name());
        }

        if(!isProviderDeleteable(optionalProvider.get())){
            throw new EntityNotDeleatableException("IL n'est pas possible de supprimer ce provider sans causer " +
                    "de conflit ", ErrorCode.PROVIDER_NOT_DELETEABLE.name());
        }
        providerDao.delete(optionalProvider.get());
        return true;
    }

    Boolean isProviderDeleteable(Provider provider){
        return true;
    }

    @Override
    public ProviderDto getProviderById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du provider a supprimer est null");
        }
        Optional<Provider> optionalProvider = providerDao.findProviderById(id);
        if(!optionalProvider.isPresent()){
            throw new ModelNotFoundException("Aucun provider n'existe avec l'id envoye",
                    ErrorCode.PROVIDER_NOT_FOUND.name());
        }

        return providerMapper.entityToDto(optionalProvider.get());
    }

    @Override
    public List<ProviderDto> getListofProvider(FilterRequest filterRequest) {

        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return providerMapper.entityToDto(providerDao.findAll());
        }
        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return providerMapper.entityToDto(providerDao.findAll());
        }
        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return providerMapper.entityToDto(providerDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }
        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Provider> providerSpecification = providerSpecService.getProviderSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return providerMapper.entityToDto(providerDao.findAll(providerSpecification));
    }

    @Override
    public PageofProviderDto getPageofProvider(FilterRequest filterRequest) {

        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Provider> providerPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            providerPage = providerDao.findAll(pageable);
            return getPageofProviderDto(providerPage);
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
                providerPage = providerDao.findAll(pageable);
                return getPageofProviderDto(providerPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                providerPage = providerDao.findAll(pageable);
                return getPageofProviderDto(providerPage);
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
            Specification<Provider> providerSpecification = providerSpecService.getProviderSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            providerPage = providerDao.findAll(providerSpecification, pageable);
            return getPageofProviderDto(providerPage);

        }
    }

    @Override
    public Boolean isProviderExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du provider a supprimer est null");
        }
        Optional<Provider> optionalProvider = providerDao.findProviderById(id);
        return optionalProvider.isPresent();
    }

    PageofProviderDto getPageofProviderDto(Page<Provider> providerPage){
        PageofProviderDto pageofProviderDto = new PageofProviderDto();
        pageofProviderDto.setContent(providerMapper.entityToDto(providerPage.getContent()));
        pageofProviderDto.setCurrentPage(providerPage.getNumber());
        pageofProviderDto.setPageSize(providerPage.getSize());
        pageofProviderDto.setTotalElements(providerPage.getTotalElements());
        pageofProviderDto.setTotalPages(providerPage.getTotalPages());

        return pageofProviderDto;
    }

}
