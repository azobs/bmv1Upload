package com.c2psi.bmv1.arrival.supplyinvoice.services;

import com.c2psi.bmv1.arrival.supplyinvoice.dao.SupplyinvoiceDao;
import com.c2psi.bmv1.arrival.supplyinvoice.errorCode.ErrorCode;
import com.c2psi.bmv1.arrival.supplyinvoice.mappers.SupplyinvoiceMapper;
import com.c2psi.bmv1.arrival.supplyinvoice.models.Supplyinvoice;
import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.enumerations.PaimentMethodEnum;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.provider.provider.mappers.ProviderMapper;
import com.c2psi.bmv1.provider.provider.service.ProviderService;
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

@Service(value = "SupplyinvoiceServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SupplyinvoiceServiceImpl implements SupplyinvoiceService{
    final AppService appService;
    final SupplyinvoiceDao siDao;
    final SupplyinvoiceValidator siValidator;
    final SupplyinvoiceMapper siMapper;
    final SupplyinvoiceSpecService siSpecService;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;
    final ProviderService providerService;
    final ProviderMapper providerMapper;



    @Override
    public SupplyinvoiceDto saveSupplyinvoice(SupplyinvoiceDto siDto) {
        log.info("The supplyinvoice saving process start by checking if it is not null");
        if (siDto == null) {
            throw new NullValueException("Le siDto envoye ne saurait etre null");
        }
        log.info("The supplyinvoice saving process continue by validate the siDto and the si associate");
        List<String> errorsDto = siValidator.validate(siDto);
        if (!errorsDto.isEmpty()) {
            throw new InvalidEntityException("Le supplyinvoiceDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.SUPPLYINVOICE_NOT_VALID.name());
        }
        List<String> errors = siValidator.validate(siMapper.dtoToEntity(siDto));
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Le supplyinvoice associe au Dto n'est pas valide ", errors,
                    ErrorCode.SUPPLYINVOICE_NOT_VALID.name());
        }
        log.info("The supplyinvoice saving process continue by ensuring that the unique constraint will not be " +
                "violated");
        if (siDto.getSiCode() != null && siDto.getSiProviderId() != null){
            if (!isSupplyinvoiceCodeforPosAndProviderUsable(siDto.getSiCode(), siDto.getSiPosId(),
                    siDto.getSiProviderId())) {
                throw new DuplicateEntityException("Il existe deja un supplyinvoice pour le pointofsale indique et le " +
                        "provider indique ", ErrorCode.SUPPLYINVOICE_DUPLICATED.name());
            }
        }
        if (siDto.getSiCode() != null && siDto.getSiProviderId() == null){
            if (!isSupplyinvoiceCodeforPosAndProviderUsable(siDto.getSiCode(), siDto.getSiPosId())) {
                throw new DuplicateEntityException("Il existe deja un supplyinvoice pour le pointofsale indique " +
                        "meme si le provider est null", ErrorCode.SUPPLYINVOICE_DUPLICATED.name());
            }
        }
        log.info("The supplyinvoice saving process continue by preparing the entity to save");
//        Supplyinvoice supplyinvoiceToSave = Supplyinvoice.builder()
//                .siCode(siDto.getSiCode())
//                .siComment(siDto.getSiComment())
//                .siPicture(siDto.getSiPicture())
//                .siDeliverydate(siDto.getSiDeliverydate())
//                .siInvoicingdate(siDto.getSiInvoicingdate())
//                .siTotalcolis(siDto.getSiTotalcolis())
//                .siExpectedamount(siDto.getSiExpectedamount())
//                .siPaidamount(siDto.getSiPaidamount())
//                .siPaymentmethod(siDto.)
//                .siPos()
//                .siProvider()
//                .build();
        Supplyinvoice supplyinvoiceSaved = siDao.save(siMapper.dtoToEntity(siDto));

        return siMapper.entityToDto(supplyinvoiceSaved);
    }

    Boolean isSupplyinvoiceCodeforPosAndProviderUsable(String siCode, Long posId, Long providerId){
        if(siCode == null || posId == null || providerId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Supplyinvoice> optionalSupplyinvoice = siDao.findSupplyinvoiceByAttributes(siCode, posId, providerId);
        return optionalSupplyinvoice.isEmpty();
    }

    Boolean isSupplyinvoiceCodeforPosAndProviderUsable(String siCode, Long posId){
        if(siCode == null || posId == null ){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Supplyinvoice> optionalSupplyinvoice = siDao.findSupplyinvoiceByAttributes(siCode, posId);
        return optionalSupplyinvoice.isEmpty();
    }

    @Override
    public SupplyinvoiceDto updateSupplyinvoice(SupplyinvoiceDto siDto) {
        if(siDto == null){
            throw new NullValueException("Le siDto to update ne saurait etre null");
        }
        if(siDto.getId() == null){
            throw new NullValueException("L'id du siDto a update ne saurait etre null");
        }
        Optional<Supplyinvoice> optionalSupplyinvoice = siDao.findSupplyinvoiceById(siDto.getId());
        if (!optionalSupplyinvoice.isPresent()){
            throw new ModelNotFoundException("Aucun Supplyinvoice n'existe dans le system avec l'id indique ",
                    ErrorCode.SUPPLYINVOICE_NOT_FOUND.name());
        }
        List<String> errorsDto = siValidator.validate(siDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le supplyinvoiceDto envoye pour update n'est pas valide ", errorsDto,
                    ErrorCode.SUPPLYINVOICE_NOT_VALID.name());
        }
        List<String> errors = siValidator.validate(siMapper.dtoToEntity(siDto));
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le supplyinvoice envoye pour update n'est pas valide ", errors,
                    ErrorCode.SUPPLYINVOICE_NOT_VALID.name());
        }

        Supplyinvoice supplyinvoiceToUpdate = optionalSupplyinvoice.get();

        if (siDto.getSiCode() != null && siDto.getSiProviderId() != null){
            if(supplyinvoiceToUpdate.getSiCode() != null && supplyinvoiceToUpdate.getSiProvider() != null) {
                if(!supplyinvoiceToUpdate.getSiCode().equalsIgnoreCase(siDto.getSiCode()) ||
                        !supplyinvoiceToUpdate.getSiProvider().equals(siDto.getSiProviderId())) {
                    if (!isSupplyinvoiceCodeforPosAndProviderUsable(siDto.getSiCode(), siDto.getSiPosId(),
                            siDto.getSiProviderId())) {
                        throw new DuplicateEntityException("Il existe deja un supplyinvoice pour le pointofsale indique et le " +
                                "provider indique ", ErrorCode.SUPPLYINVOICE_DUPLICATED.name());
                    }
                }
            }
        }


        if (siDto.getSiCode() != null && siDto.getSiProviderId() == null){
            if(supplyinvoiceToUpdate.getSiCode() != null && supplyinvoiceToUpdate.getSiProvider() == null) {
                if(!supplyinvoiceToUpdate.getSiCode().equalsIgnoreCase(siDto.getSiCode())) {
                    if (!isSupplyinvoiceCodeforPosAndProviderUsable(siDto.getSiCode(), siDto.getSiPosId())) {
                        throw new DuplicateEntityException("Il existe deja un supplyinvoice pour le pointofsale indique " +
                                "meme si le provider est null", ErrorCode.SUPPLYINVOICE_DUPLICATED.name());
                    }
                }
            }
        }

        log.info("The supplyinvoice update can now be process");


        supplyinvoiceToUpdate.setSiCode(siDto.getSiCode());
        supplyinvoiceToUpdate.setSiComment(siDto.getSiComment());
        supplyinvoiceToUpdate.setSiPicture(siDto.getSiPicture());
        supplyinvoiceToUpdate.setSiDeliverydate(siDto.getSiDeliverydate());
        supplyinvoiceToUpdate.setSiInvoicingdate(siDto.getSiInvoicingdate());
        supplyinvoiceToUpdate.setSiTotalcolis(siDto.getSiTotalcolis());
        supplyinvoiceToUpdate.setSiExpectedamount(siDto.getSiExpectedamount());
        supplyinvoiceToUpdate.setSiPaidamount(siDto.getSiPaidamount());
        supplyinvoiceToUpdate.setSiPaymentmethod(paymentMethodEnumToPaiementMethodEnum(siDto.getSiPaymentmethod()));
        supplyinvoiceToUpdate.setSiPos(posMapper.dtoToEntity(posService.getPointofsaleById(siDto.getSiPosId())));
        supplyinvoiceToUpdate.setSiProvider(siDto.getSiProviderId() != null? providerMapper.dtoToEntity(
                providerService.getProviderById(siDto.getSiProviderId())):null);

        return siMapper.entityToDto(siDao.save(supplyinvoiceToUpdate));
    }

    PaimentMethodEnum paymentMethodEnumToPaiementMethodEnum(SupplyinvoiceDto.SiPaymentmethodEnum siPaymentmethodEnum){
        switch (siPaymentmethodEnum){
            case OM :
                return PaimentMethodEnum.Om;
            case MOMO:
                return PaimentMethodEnum.Momo;
            case CASH:
                return PaimentMethodEnum.Cash;
            default:
                throw new EnumNonConvertibleException("The sending enum value is not recognized in the system");
        }
    }

    @Override
    public Boolean deleteSupplyinvoiceById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du supplyinvoice a delete ne saurait etre null");
        }
        Optional<Supplyinvoice> optionalSupplyinvoice = siDao.findSupplyinvoiceById(id);
        if(!optionalSupplyinvoice.isPresent()){
            throw new ModelNotFoundException("Aucun supplyinvoice n'existe avec l'id envoye",
                    ErrorCode.SUPPLYINVOICE_NOT_FOUND.name());
        }
        if(!isSupplyinvoiceDeleteable(optionalSupplyinvoice.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer ce supplyinvoice sana cause de conflit ",
                    ErrorCode.SUPPLYINVOICE_NOT_DELETEABLE.name());
        }
        siDao.delete(optionalSupplyinvoice.get());
        return true;
    }

    Boolean isSupplyinvoiceDeleteable(Supplyinvoice supplyinvoice){
        return true;
    }

    @Override
    public SupplyinvoiceDto getSupplyinvoiceById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du supplyinvoice a delete ne saurait etre null");
        }
        Optional<Supplyinvoice> optionalSupplyinvoice = siDao.findSupplyinvoiceById(id);
        if(!optionalSupplyinvoice.isPresent()){
            throw new ModelNotFoundException("Aucun supplyinvoice n'existe avec l'id envoye",
                    ErrorCode.SUPPLYINVOICE_NOT_FOUND.name());
        }
        return siMapper.entityToDto(optionalSupplyinvoice.get());
    }

    @Override
    public List<SupplyinvoiceDto> getListofSupplyinvoice(FilterRequest filterRequest) {
        /************************************************************************
         * On se rassure que le filterRequest n'est pas null et si c'est le cas
         * on retourne le findAll
         */
        if(filterRequest == null){
            return siMapper.entityToDto(siDao.findAll());
        }

        /************************************************************************
         * Si dans le filterRequest les filtres et les tris sont null on
         * retourne aussi le findAll
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return siMapper.entityToDto(siDao.findAll());
        }

        /**********************************************************************************
         * Si les filtres sont null mais les elements de tris non null
         * alors on retourne le findAll range dans l'ordre indique par les elements de tri
         */
        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return siMapper.entityToDto(siDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Supplyinvoice> siSpecification = siSpecService.getSiSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return siMapper.entityToDto(siDao.findAll(siSpecification));
    }

    @Override
    public PageofSupplyinvoiceDto getPageofSupplyinvoice(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Supplyinvoice> siPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            siPage = siDao.findAll(pageable);
            return getPageofSupplyinvoiceDto(siPage);
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
                siPage = siDao.findAll(pageable);
                return getPageofSupplyinvoiceDto(siPage);
            }

            /****************************************************************************************************
             * Si dans le filterRequest envoye les filtres sont nuls et les elements de tri sont non null alors
             * on retourne le findAll page par page trie selon les elements de tri envoye.
             */

            if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
                Sort sort = appService.getSortOrders(filterRequest.getOrderby());
                Pageable pageable = PageRequest.of(filterRequest.getPage().getPagenum(),
                        filterRequest.getPage().getPagesize(), sort);
                siPage = siDao.findAll(pageable);
                return getPageofSupplyinvoiceDto(siPage);
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
            Specification<Supplyinvoice> siSpecification = siSpecService.getSiSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            siPage = siDao.findAll(siSpecification, pageable);
            return getPageofSupplyinvoiceDto(siPage);

        }
    }

    @Override
    public Boolean isSupplyinvoiceExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du supplyinvoice a delete ne saurait etre null");
        }
        Optional<Supplyinvoice> optionalSupplyinvoice = siDao.findSupplyinvoiceById(id);
        return optionalSupplyinvoice.isPresent();
    }

    PageofSupplyinvoiceDto getPageofSupplyinvoiceDto(Page<Supplyinvoice> siPage){
        PageofSupplyinvoiceDto pageofSupplyinvoiceDto = new PageofSupplyinvoiceDto();
        pageofSupplyinvoiceDto.setContent(siMapper.entityToDto(siPage.getContent()));
        pageofSupplyinvoiceDto.setCurrentPage(siPage.getNumber());
        pageofSupplyinvoiceDto.setPageSize(siPage.getSize());
        pageofSupplyinvoiceDto.setTotalElements(siPage.getTotalElements());
        pageofSupplyinvoiceDto.setTotalPages(siPage.getTotalPages());

        return pageofSupplyinvoiceDto;

    }
}
