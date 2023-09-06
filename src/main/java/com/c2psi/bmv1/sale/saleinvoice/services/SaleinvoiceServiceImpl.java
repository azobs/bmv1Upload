package com.c2psi.bmv1.sale.saleinvoice.services;

import com.c2psi.bmv1.bmapp.dto.BmPageDto;
import com.c2psi.bmv1.bmapp.enumerations.PaimentMethodEnum;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.sale.saleinvoice.errorCode.ErrorCode;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.sale.saleinvoice.dao.SaleinvoiceDao;
import com.c2psi.bmv1.sale.saleinvoice.mappers.SaleinvoiceMapper;
import com.c2psi.bmv1.sale.saleinvoice.models.Saleinvoice;
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

@Service(value = "SaleinvoiceServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SaleinvoiceServiceImpl implements SaleinvoiceService{

    final AppService appService;
    final SaleinvoiceDao siDao;
    final SaleinvoiceValidator siValidator;
    final SaleinvoiceMapper siMapper;
    final SaleinvoiceSpecService siSpecService;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;


    @Override
    public SaleinvoiceDto saveSaleinvoice(SaleinvoiceDto siDto) {
        log.info("The saleinvoice saving process start by checking if it is not null");
        if (siDto == null) {
            throw new NullValueException("Le siDto envoye ne saurait etre null");
        }
        log.info("The saleinvoice saving process continue by validate the siDto and the si associate");
        List<String> errorsDto = siValidator.validate(siDto);
        if (!errorsDto.isEmpty()) {
            throw new InvalidEntityException("Le saleinvoiceDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.SALEINVOICE_NOT_VALID.name());
        }
        List<String> errors = siValidator.validate(siMapper.dtoToEntity(siDto));
        if (!errors.isEmpty()) {
            throw new InvalidEntityException("Le saleinvoice associe au Dto n'est pas valide ", errors,
                    ErrorCode.SALEINVOICE_NOT_VALID.name());
        }
        log.info("The saleinvoice saving process continue by ensuring that the unique constraint will not be " +
                "violated");

        if (siDto.getSiCode() != null && siDto.getSiPosId() == null){
            if (!isSaleinvoiceCodeforPosUsable(siDto.getSiCode(), siDto.getSiPosId())) {
                throw new DuplicateEntityException("Il existe deja un saleinvoice pour le pointofsale indique ",
                        ErrorCode.SALEINVOICE_DUPLICATED.name());
            }
        }
        log.info("The saleinvoice saving process continue by preparing the entity to save");

        /**
         *
         * saleinvoiceToUpdate.setSiCode(siDto.getSiCode());
         *         saleinvoiceToUpdate.setSiComment(siDto.getSiComment());
         *         saleinvoiceToUpdate.setSiInvoicingdate(siDto.getSiInvoicingdate());
         *         saleinvoiceToUpdate.setSiTotalcolis(siDto.getSiTotalcolis());
         *         saleinvoiceToUpdate.setSiExpectedamount(siDto.getSiExpectedamount());
         *         saleinvoiceToUpdate.setSiPaidamount(siDto.getSiPaidamount());
         *         saleinvoiceToUpdate.setSiPaymentmethod(paymentMethodEnumToPaiementMethodEnum(siDto.getSiPaymentmethod()));
         *         saleinvoiceToUpdate.setSiPos(posMapper.dtoToEntity(posService.getPointofsaleById(siDto.getSiPosId())));
         */

        Saleinvoice saleinvoiceToSave = Saleinvoice.builder()
                .siCode(siDto.getSiCode())
                .siComment(siDto.getSiComment())
                .siInvoicingdate(siDto.getSiInvoicingdate())
                .siTotalcolis(siDto.getSiTotalcolis())
                .siExpectedamount(siDto.getSiExpectedamount())
                .siPaidamount(siDto.getSiPaidamount())
                .siPaymentmethod(paymentMethodEnumToPaiementMethodEnum(siDto.getSiPaymentmethod()))
                .siPos(posMapper.dtoToEntity(posService.getPointofsaleById(siDto.getSiPosId())))
                .build();
        Saleinvoice saleinvoiceSaved = siDao.save(saleinvoiceToSave);

        return siMapper.entityToDto(saleinvoiceSaved);
    }

    Boolean isSaleinvoiceCodeforPosUsable(String siCode, Long posId){
        if(siCode == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Saleinvoice> optionalSaleinvoice = siDao.findSaleinvoiceByCodeInPos(siCode, posId);
        return optionalSaleinvoice.isEmpty();
    }

    @Override
    public SaleinvoiceDto updateSaleinvoice(SaleinvoiceDto siDto) {
        if(siDto == null){
            throw new NullValueException("Le siDto to update ne saurait etre null");
        }
        if(siDto.getId() == null){
            throw new NullValueException("L'id du siDto a update ne saurait etre null");
        }
        Optional<Saleinvoice> optionalSaleinvoice = siDao.findSaleinvoiceById(siDto.getId());
        if (!optionalSaleinvoice.isPresent()){
            throw new ModelNotFoundException("Aucun Saleinvoice n'existe dans le system avec l'id indique ",
                    ErrorCode.SALEINVOICE_NOT_FOUND.name());
        }
        List<String> errorsDto = siValidator.validate(siDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le saleinvoiceDto envoye pour update n'est pas valide ", errorsDto,
                    ErrorCode.SALEINVOICE_NOT_VALID.name());
        }
        List<String> errors = siValidator.validate(siMapper.dtoToEntity(siDto));
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le saleinvoice envoye pour update n'est pas valide ", errors,
                    ErrorCode.SALEINVOICE_NOT_VALID.name());
        }

        Saleinvoice saleinvoiceToUpdate = optionalSaleinvoice.get();


        if (siDto.getSiCode() != null){
            if(saleinvoiceToUpdate.getSiCode() != null) {
                if(!saleinvoiceToUpdate.getSiCode().equalsIgnoreCase(siDto.getSiCode())) {
                    if (!isSaleinvoiceCodeforPosUsable(siDto.getSiCode(), siDto.getSiPosId())) {
                        throw new DuplicateEntityException("Il existe deja un saleinvoice pour le pointofsale indique " +
                                "meme si le provider est null", ErrorCode.SALEINVOICE_DUPLICATED.name());
                    }
                }
            }
        }

        log.info("The saleinvoice update can now be process");


        saleinvoiceToUpdate.setSiCode(siDto.getSiCode());
        saleinvoiceToUpdate.setSiComment(siDto.getSiComment());
        saleinvoiceToUpdate.setSiInvoicingdate(siDto.getSiInvoicingdate());
        saleinvoiceToUpdate.setSiTotalcolis(siDto.getSiTotalcolis());
        saleinvoiceToUpdate.setSiExpectedamount(siDto.getSiExpectedamount());
        saleinvoiceToUpdate.setSiPaidamount(siDto.getSiPaidamount());
        saleinvoiceToUpdate.setSiPaymentmethod(paymentMethodEnumToPaiementMethodEnum(siDto.getSiPaymentmethod()));
        saleinvoiceToUpdate.setSiPos(posMapper.dtoToEntity(posService.getPointofsaleById(siDto.getSiPosId())));

        return siMapper.entityToDto(siDao.save(saleinvoiceToUpdate));
    }

    PaimentMethodEnum paymentMethodEnumToPaiementMethodEnum(SaleinvoiceDto.SiPaymentmethodEnum siPaymentmethodEnum){
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
    public Boolean deleteSaleinvoiceById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du saleinvoice a delete ne saurait etre null");
        }
        Optional<Saleinvoice> optionalSaleinvoice = siDao.findSaleinvoiceById(id);
        if(!optionalSaleinvoice.isPresent()){
            throw new ModelNotFoundException("Aucun saleinvoice n'existe avec l'id envoye",
                    ErrorCode.SALEINVOICE_NOT_FOUND.name());
        }
        if(!isSaleinvoiceDeleteable(optionalSaleinvoice.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer ce saleinvoice sana cause de conflit ",
                    ErrorCode.SALEINVOICE_NOT_DELETEABLE.name());
        }
        siDao.delete(optionalSaleinvoice.get());
        return true;
    }

    Boolean isSaleinvoiceDeleteable(Saleinvoice saleinvoice){
        return true;
    }

    @Override
    public SaleinvoiceDto getSaleinvoiceById(Long id) {
        if(id == null){
            throw new NullValueException("L'id du saleinvoice a delete ne saurait etre null");
        }
        Optional<Saleinvoice> optionalSaleinvoice = siDao.findSaleinvoiceById(id);
        if(!optionalSaleinvoice.isPresent()){
            throw new ModelNotFoundException("Aucun saleinvoice n'existe avec l'id envoye",
                    ErrorCode.SALEINVOICE_NOT_FOUND.name());
        }
        return siMapper.entityToDto(optionalSaleinvoice.get());
    }

    @Override
    public List<SaleinvoiceDto> getListofSaleinvoice(FilterRequest filterRequest) {
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

        Specification<Saleinvoice> siSpecification = siSpecService.getSiSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return siMapper.entityToDto(siDao.findAll(siSpecification));
    }

    @Override
    public PageofSaleinvoiceDto getPageofSaleinvoice(FilterRequest filterRequest) {
        /*****************************************************************
         * On prepare un element page de notre bmapp
         */
        Pagebm pagebm = new Pagebm();
        /***********************************************
         * On declare une page pour notre element
         */
        Page<Saleinvoice> siPage = null;
        /***************************************************************************************
         * Si le filterRequest envoye est null alors c'est le findAll qu'on retourne
         * page par page. On va donc retourner la page 0 avec une taille de 10 pour la page
         */
        if(filterRequest == null){
            pagebm.setPagenum(0);
            pagebm.setPagesize(10);
            Pageable pageable = new BmPageDto().getPageable(pagebm);
            siPage = siDao.findAll(pageable);
            return getPageofSaleinvoiceDto(siPage);
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
                return getPageofSaleinvoiceDto(siPage);
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
                return getPageofSaleinvoiceDto(siPage);
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
            Specification<Saleinvoice> siSpecification = siSpecService.getSiSpecification(filterRequest.getFilters(),
                    filterRequest.getLogicOperator(), filterRequest.getOrderby());
            Pageable pageable = new BmPageDto().getPageable(filterRequest.getPage());
            siPage = siDao.findAll(siSpecification, pageable);
            return getPageofSaleinvoiceDto(siPage);

        }
    }

    @Override
    public Boolean isSaleinvoiceExistWith(Long id) {
        if(id == null){
            throw new NullValueException("L'id du saleinvoice a delete ne saurait etre null");
        }
        Optional<Saleinvoice> optionalSaleinvoice = siDao.findSaleinvoiceById(id);
        return optionalSaleinvoice.isPresent();
    }

    PageofSaleinvoiceDto getPageofSaleinvoiceDto(Page<Saleinvoice> siPage){
        PageofSaleinvoiceDto pageofSaleinvoiceDto = new PageofSaleinvoiceDto();
        pageofSaleinvoiceDto.setContent(siMapper.entityToDto(siPage.getContent()));
        pageofSaleinvoiceDto.setCurrentPage(siPage.getNumber());
        pageofSaleinvoiceDto.setPageSize(siPage.getSize());
        pageofSaleinvoiceDto.setTotalElements(siPage.getTotalElements());
        pageofSaleinvoiceDto.setTotalPages(siPage.getTotalPages());

        return pageofSaleinvoiceDto;

    }
}
