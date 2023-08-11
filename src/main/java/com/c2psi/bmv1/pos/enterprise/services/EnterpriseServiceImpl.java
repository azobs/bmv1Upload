package com.c2psi.bmv1.pos.enterprise.services;

import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.pos.enterprise.dao.EnterpriseDao;
import com.c2psi.bmv1.bmapp.enumerations.EntRegimeEnum;
import com.c2psi.bmv1.bmapp.enumerations.EnterpriseErrorCode;
import com.c2psi.bmv1.pos.enterprise.mappers.EnterpriseMapper;
import com.c2psi.bmv1.pos.enterprise.models.Enterprise;
import com.c2psi.bmv1.pos.enterprise.validators.EnterpriseValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service(value = "EnterpriseServiceV1")
@Slf4j
@Transactional
@RequiredArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseService{
    final EnterpriseDao enterpriseDao;
    final EnterpriseMapper enterpriseMapper;
    final EnterpriseValidator enterpriseValidator;
    final AppService appService;
    final EnterpriseSpecService enterpriseSpecService;

    @Override
    public EnterpriseDto saveEnterprise(EnterpriseDto enterpriseDto) {
        if(enterpriseDto == null){
            throw new NullValueException("L'enterprise envoye est null");
        }
        /**************************************************************************
         * On valide les parametres saisis et envoyes depuis l'interface client
         **************************************************************************/
        List<String> errors = enterpriseValidator.validate(enterpriseMapper.dtoToEntity(enterpriseDto));
        if(!errors.isEmpty()){
            log.error("Entity Enterprise not valid because of {}", errors);
            throw new InvalidEntityException("Le Enterprise a enregistrer n'est pas valide ", errors,
                    EnterpriseErrorCode.ENTERPRISE_NOT_VALID.name());
        }

        /***********************************************************************
         * Il faut se rassurer qu'aucune contrainte d'unicite ne sera vile en BD
         */
        if(enterpriseDto.getEntNiu() != null){
            if(!isEnterpriseNiuUsable(enterpriseDto.getEntNiu())){
                log.error("The Niu sent is already used in the DB");
                throw new DuplicateEntityException("L'identifiant unique envoye est deja utilise",
                        EnterpriseErrorCode.ENTERPRISE_DUPLICATED.name());
            }
        }

        if(enterpriseDto.getEntName() != null && enterpriseDto.getEntAcronym() != null){
            if(!isEnterpriseNameUsable(enterpriseDto.getEntName(), enterpriseDto.getEntAcronym())){
                log.error("The couple name and acronym sent is already used in the DB");
                throw new DuplicateEntityException("Le couple (name, acronym) envoye est deja utilise",
                        EnterpriseErrorCode.ENTERPRISE_DUPLICATED.name());
            }
        }

        log.info("After all verification, the Enterprise {} sent can be safely saved in the DB ", enterpriseDto);

        Enterprise enterpriseSaved = enterpriseDao.save(enterpriseMapper.dtoToEntity(enterpriseDto));

        return enterpriseMapper.entityToDto(enterpriseSaved);
    }

    Boolean isEnterpriseNiuUsable(String niu){
        Optional<Enterprise> optionalEnterprise = enterpriseDao.findEnterpriseByEntNiu(niu);
        return optionalEnterprise.isEmpty();
    }

    Boolean isEnterpriseNameUsable(String name, String acronym){
        Optional<Enterprise> optionalEnterprise = enterpriseDao.findEnterpriseByFullname(name, acronym);
        return optionalEnterprise.isEmpty();
    }

    @Override
    public EnterpriseDto updateEnterprise(EnterpriseDto enterpriseDto) {
        if(enterpriseDto == null){
            throw new NullValueException("L'enterprise envoye est null");
        }

        List<String> errors = enterpriseValidator.validate(enterpriseMapper.dtoToEntity(enterpriseDto));
        if(!errors.isEmpty()){
            log.error("Entity Enterprise not valid because of {}", errors);
            throw new InvalidEntityException("Le Enterprise a enregistrer n'est pas valide ", errors,
                    EnterpriseErrorCode.ENTERPRISE_NOT_VALID.name());
        }

        if(enterpriseDto.getId() == null){
            throw new NullValueException("L'enterprise envoye a un identifiant null et ne saurait donc etre mis a jour");
        }

        Optional<Enterprise> optionalEnterprise = enterpriseDao.findEnterpriseById(enterpriseDto.getId());
        if(!optionalEnterprise.isPresent()){
            log.error("There is no enterprise with the precised id");
            throw new ModelNotFoundException("Aucune entreprise n'existe en BD avec l'Id precise",
                    EnterpriseErrorCode.ENTERPRISE_NOT_FOUND.name());
        }
        Enterprise enterpriseToUpdate = optionalEnterprise.get();

        /**********************************************************************************
         * On va se rassurer que les contraintes ne seront pas viole en cas de mis a jour
         */
        if(enterpriseDto.getEntNiu() != null){
            if(!enterpriseToUpdate.getEntNiu().equalsIgnoreCase(enterpriseDto.getEntNiu())){
                //Alors on veut modifier le Niu
                if(!isEnterpriseNiuUsable(enterpriseDto.getEntNiu())){
                    log.error("Le nouveau Niu envoye est deja utilise");
                    throw new DuplicateEntityException("Le Niu envoye est deja utilise",
                            EnterpriseErrorCode.ENTERPRISE_DUPLICATED.name());
                }
            }
        }

        if(enterpriseDto.getEntName() != null && enterpriseDto.getEntAcronym() != null){
            if(!enterpriseToUpdate.getEntName().equalsIgnoreCase(enterpriseDto.getEntName()) ||
                !enterpriseToUpdate.getEntAcronym().equalsIgnoreCase(enterpriseDto.getEntAcronym())){
                if(!isEnterpriseNameUsable(enterpriseDto.getEntName(), enterpriseDto.getEntAcronym())){
                    log.error("Le nouveau de l'entreprise est deja utilise");
                    throw new DuplicateEntityException("Le nouveau non de l'entreprise est deja utilise",
                            EnterpriseErrorCode.ENTERPRISE_DUPLICATED.name());
                }
            }
        }

        log.info("After all verification, The reamining update can be done without any problem");
        enterpriseToUpdate.setEntAcronym(enterpriseDto.getEntAcronym());
        enterpriseToUpdate.setEntName(enterpriseDto.getEntName());
        enterpriseToUpdate.setEntLogo(enterpriseDto.getEntLogo());
        enterpriseToUpdate.setEntNiu(enterpriseDto.getEntNiu());
        enterpriseToUpdate.setEntSocialreason(enterpriseDto.getEntSocialreason());
        enterpriseToUpdate.setEntDescription(enterpriseDto.getEntDescription());
        switch (enterpriseDto.getEntRegime()){
            case IL:
                enterpriseToUpdate.setEntRegime(EntRegimeEnum.IL);
                break;
            case SA:
                enterpriseToUpdate.setEntRegime(EntRegimeEnum.SA);
                break;
            case SI:
                enterpriseToUpdate.setEntRegime(EntRegimeEnum.SI);
                break;
            case SARL:
                enterpriseToUpdate.setEntRegime(EntRegimeEnum.SARL);
                break;
            case GRP:
                enterpriseToUpdate.setEntRegime(EntRegimeEnum.GRP);
                break;
            default:
                throw new InvalidArgumentException("Le regime de l'entreprise n'est pas valide ou n'est pas encore " +
                        "pris par le systeme");
        }

        Enterprise enterpriseUpdated = enterpriseDao.save(enterpriseToUpdate);

        return enterpriseMapper.entityToDto(enterpriseUpdated);
    }

    @Override
    public Boolean deleteEnterpriseById(Long id) {
        /*****************************************
         * On se rassure que l'id n'est pas null
         */
        if(id == null){
            throw new NullValueException("The id of the enterprise to be deleted can't be null");
        }
        /********************************************************
         * On se rassure qu'une entreprise existe avec cet id
         */
        Optional<Enterprise> optionalEnterprise = enterpriseDao.findEnterpriseById(id);
        if(!optionalEnterprise.isPresent()){
            throw new ModelNotFoundException("Aucune entreprise n'existe avec l'id envoye");
        }

        enterpriseDao.deleteById(id);

        return true;
    }

    @Override
    public EnterpriseDto getEnterpriseById(Long id) {
        /**********
         * On se rassure que l'id n'est pas null
         */
        if(id == null){
            throw new NullValueException("L'id de l'entreprise recherche ne peut etre null");
        }
        /*******************
         * On fait donc la recherche de l'entreprise en question
         */
        Optional<Enterprise> optionalEnterprise = enterpriseDao.findEnterpriseById(id);
        if(!optionalEnterprise.isPresent()){
            throw new ModelNotFoundException("Aucune entreprise n'existe avec l'id envoye");
        }

        return enterpriseMapper.entityToDto(optionalEnterprise.get());
    }

    @Override
    public List<EnterpriseDto> getListofEnterprise(FilterRequest filterRequest) {
        if(filterRequest == null){
            return enterpriseMapper.entityToDto(enterpriseDao.findAll());
        }

        if(filterRequest.getFilters() == null && filterRequest.getOrderby() == null){
            return enterpriseMapper.entityToDto(enterpriseDao.findAll());
        }

        if(filterRequest.getFilters() == null && filterRequest.getOrderby() != null){
            return enterpriseMapper.entityToDto(enterpriseDao.findAll(appService.getSortOrders(filterRequest.getOrderby())));
        }

        /*****************************************************************
         * A ce niveau on est sur que filterRequest.getFilters() != null
         * Maintenant si filterRequest.getOrderby() == null cela ne cause
         * aucun souci la liste aura juste un ordre par defaut.
         */

        if(filterRequest.getLogicOperator() == null && filterRequest.getFilters().size() > 1){
            throw new NullValueException("L'operateur logique permettant de lier les filtres ne peut etre null");
        }

        Specification<Enterprise> enterpriseSpecification = enterpriseSpecService.getEnterpriseSpecification(filterRequest.getFilters(),
                filterRequest.getLogicOperator(), filterRequest.getOrderby());
        return enterpriseMapper.entityToDto(enterpriseDao.findAll(enterpriseSpecification));
    }

    @Override
    public PageofEnterpriseDto getPageofEnterprise(FilterRequest filterRequest) {
        return null;
    }

}
