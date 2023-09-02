package com.c2psi.bmv1.price.specialprice.services;

import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.dto.SpecialpriceDto;
import com.c2psi.bmv1.price.baseprice.mappers.BasepriceMapper;
import com.c2psi.bmv1.price.baseprice.services.BasepriceService;
import com.c2psi.bmv1.price.specialprice.dao.SpecialpriceDao;
import com.c2psi.bmv1.price.specialprice.errorCode.ErrorCode;
import com.c2psi.bmv1.price.specialprice.mappers.SpecialpriceMapper;
import com.c2psi.bmv1.price.specialprice.models.Specialprice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service(value = "SpecialpriceServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SpecialpriceServiceImpl implements SpecialpriceService{

    final AppService appService;
    final SpecialpriceDao specialpriceDao;
    final SpecialpriceMapper specialpriceMapper;
    final SpecialpriceValidator specialpriceValidator;
    final BasepriceService basepriceService;
    final BasepriceMapper basepriceMapper;

    @Override
    public SpecialpriceDto saveSpecialprice(SpecialpriceDto specialpriceDto) {
        log.info("we start by ensure that specialpriceDto sent is not null");
        if(specialpriceDto == null){
            throw new NullValueException("Le specialpriceDto envoye ne saurait etre null");
        }
        log.info("We continue by validate the specialpriceDto");
        List<String> errorsDto = specialpriceValidator.validate(specialpriceDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le specialpriceDto envoye n'est pas valide ", errorsDto,
                    ErrorCode.SPECIALPRICE_NOT_VALID.name());
        }
        log.info("We continue by validate the specialprice");
        List<String> errors = specialpriceValidator.validate(specialpriceMapper.dtoToEntity(specialpriceDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le specialprice envoye n'est pas valide ", errors,
                    ErrorCode.SPECIALPRICE_NOT_VALID.name());
        }
        log.info("We continue by ensure that the unique constraint can't be violated");
        if(specialpriceDto.getSpCode() != null){
            if(!isSpecialpriceCodeUsable(specialpriceDto.getSpCode(), specialpriceDto.getSpBasepriceId())){
                throw new DuplicateEntityException("Il existe deja un specialprice associe a ce baseprice avec le " +
                        "meme code ", ErrorCode.SPECIALPRICE_DUPLICATED.name());
            }
        }
        log.info("After all verification we can prepare the specialprice saving process");
//        Specialprice specialpriceToSave = Specialprice.builder()
//                .spCode(specialpriceDto.getSpCode())
//                .spWholeprice(specialpriceDto.getSpWholeprice())
//                .spSemiwholeprice(specialpriceDto.getSpSemiwholeprice())
//                .spDetailsprice(specialpriceDto.getSpDetailsprice())
//                .spRistourne(specialpriceDto.getSpRistourne())
//                .spPrecompte(specialpriceDto.getSpPrecompte())
//                .spBaseprice(basepriceMapper.dtoToEntity(basepriceService.getBasepriceById(
//                        specialpriceDto.getSpBasepriceId())))
//                .build();
        Specialprice specialpriceToSave = specialpriceMapper.dtoToEntity(specialpriceDto);
        log.info("After all verification the specialprice can be saved safely");
        Specialprice specialpriceSaved = specialpriceDao.save(specialpriceToSave);
        return specialpriceMapper.entityToDto(specialpriceSaved);
    }

    Boolean isSpecialpriceCodeUsable(String spCode, Long bpId){
        if(spCode == null || bpId == null){
            throw new NullValueException("les arguments envoyes sont nuls");
        }
        Optional<Specialprice> optionalSpecialprice = specialpriceDao.findSpecialpriceBySpCodeForBaseprice(spCode, bpId);
        return optionalSpecialprice.isEmpty();
    }

    @Override
    public SpecialpriceDto updateSpecialprice(SpecialpriceDto specialpriceDto) {
        log.info("The update process start by checking if the specialprice is not null");
        if(specialpriceDto == null){
            throw new NullValueException("Le specialpriceDto envoye ne saurait etre null");
        }
        log.info("The update process continue by checking if the id of the specialprice id is not null");
        if(specialpriceDto.getId() == null){
            throw new NullValueException("The id of the specialprice to update can't be null");
        }
        log.info("The update process continue by validate the specialpriceDto");
        List<String> errorsDto = specialpriceValidator.validate(specialpriceDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le specialpriceDto envoye pour update n'est pas valide ", errorsDto,
                    ErrorCode.SPECIALPRICE_NOT_VALID.name());
        }
        log.info("The update process continue by validate the specialprice");
        List<String> errors = specialpriceValidator.validate(specialpriceMapper.dtoToEntity(specialpriceDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le specialprice associe pour update n'est pas valide ", errors,
                    ErrorCode.SPECIALPRICE_NOT_VALID.name());
        }
        Optional<Specialprice> optionalSpecialprice = specialpriceDao.findSpecialpriceById(specialpriceDto.getId());
        if(!optionalSpecialprice.isPresent()){
            throw new ModelNotFoundException("Aucun specialprice n'existe dans le systeme avec l'id envoye",
                    ErrorCode.SPECIALPRICE_NOT_FOUND.name());
        }
        Specialprice specialpriceToUpdate = optionalSpecialprice.get();
        log.info("The update process continue by ensure that the constraint is not violated if the code is not null " +
                "and different than the existing one");
        if(specialpriceDto.getSpCode() != null){
            if(specialpriceToUpdate.getSpCode() != null) {
                if(!specialpriceDto.getSpCode().equals(specialpriceToUpdate.getSpCode())) {
                    if (!isSpecialpriceCodeUsable(specialpriceDto.getSpCode(), specialpriceDto.getSpBasepriceId())) {
                        throw new DuplicateEntityException("Il existe deja un specialprice avec le meme code pour le " +
                                "baseprice associe ", ErrorCode.SPECIALPRICE_DUPLICATED.name());
                    }
                }
            }
        }

        log.info("The update process can be prepared");
        specialpriceToUpdate.setSpCode(specialpriceDto.getSpCode());
        specialpriceToUpdate.setSpWholeprice(specialpriceDto.getSpWholeprice());
        specialpriceToUpdate.setSpSemiwholeprice(specialpriceDto.getSpSemiwholeprice());
        specialpriceToUpdate.setSpDetailsprice(specialpriceDto.getSpDetailsprice());
        specialpriceToUpdate.setSpRistourne(specialpriceDto.getSpRistourne());
        specialpriceToUpdate.setSpPrecompte(specialpriceDto.getSpPrecompte());
        specialpriceToUpdate.setSpBaseprice(basepriceMapper.dtoToEntity(basepriceService.getBasepriceById(
                specialpriceDto.getSpBasepriceId())));

        Specialprice specialpriceUpdated = specialpriceDao.save(specialpriceToUpdate);

        return specialpriceMapper.entityToDto(specialpriceUpdated);
    }

    @Override
    public Boolean deleteSpecialpriceById(Long id) {
        if(id == null){
            throw new NullValueException("The id sent is null");
        }
        Optional<Specialprice> optionalSpecialprice = specialpriceDao.findSpecialpriceById(id);
        if(!optionalSpecialprice.isPresent()){
            throw new ModelNotFoundException("Aucun specialprice n'existe en BD avec l'id envoye",
                    ErrorCode.SPECIALPRICE_NOT_FOUND.name());
        }
        if(!isSpecialpriceDeleteable(optionalSpecialprice.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer ce specialprice sans causer de conflit ",
                    ErrorCode.SPECIALPRICE_NOT_DELETEABLE.name());
        }

        specialpriceDao.delete(optionalSpecialprice.get());
        return true;
    }

    Boolean isSpecialpriceDeleteable(Specialprice specialprice){
        return true;
    }

    @Override
    public SpecialpriceDto getSpecialpriceById(Long id) {
        if(id == null){
            throw new NullValueException("The id sent is null");
        }
        Optional<Specialprice> optionalSpecialprice = specialpriceDao.findSpecialpriceById(id);
        if(!optionalSpecialprice.isPresent()){
            throw new ModelNotFoundException("Aucun specialprice n'existe en BD avec l'id envoye",
                    ErrorCode.SPECIALPRICE_NOT_FOUND.name());
        }

        return specialpriceMapper.entityToDto(optionalSpecialprice.get());
    }

    @Override
    public Boolean isSpecialpriceExistWith(Long id) {
        if(id == null){
            throw new NullValueException("The id sent is null");
        }
        Optional<Specialprice> optionalSpecialprice = specialpriceDao.findSpecialpriceById(id);
        return optionalSpecialprice.isPresent();
    }
}
