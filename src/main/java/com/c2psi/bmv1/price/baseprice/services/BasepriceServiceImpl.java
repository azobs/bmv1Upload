package com.c2psi.bmv1.price.baseprice.services;

import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.bmapp.services.AppService;
import com.c2psi.bmv1.currency.mappers.CurrencyMapper;
import com.c2psi.bmv1.currency.services.CurrencyService;
import com.c2psi.bmv1.dto.BasepriceDto;
import com.c2psi.bmv1.pos.pos.mapper.PointofsaleMapper;
import com.c2psi.bmv1.pos.pos.service.PointofsaleService;
import com.c2psi.bmv1.price.baseprice.dao.BasepriceDao;
import com.c2psi.bmv1.price.baseprice.errorCode.ErrorCode;
import com.c2psi.bmv1.price.baseprice.mappers.BasepriceMapper;
import com.c2psi.bmv1.price.baseprice.models.Baseprice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service(value = "BasepriceServiceV1")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BasepriceServiceImpl implements BasepriceService{

    final AppService appService;
    final BasepriceDao basepriceDao;
    final BasepriceMapper basepriceMapper;
    final BasepriceValidator basepriceValidator;
    final CurrencyService currencyService;
    final CurrencyMapper currencyMapper;
    final PointofsaleService posService;
    final PointofsaleMapper posMapper;

    @Override
    public BasepriceDto saveBaseprice(BasepriceDto basepriceDto) {
        log.info("Tout commence par verifier que le dto envoye n'est pas null");
        if(basepriceDto == null){
            throw new NullValueException("Le baseprice envoye ne peut etre null");
        }
        log.info("On va valider le basepriceDto envoye");
        List<String> errorsDto = basepriceValidator.validate(basepriceDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le basepriceDto envoye pour enregistrement n'est pas valide ", errorsDto,
                    ErrorCode.BASEPRICE_NOT_VALID.name());
        }
        log.info("On va valider l'entity assicie au Dto envoye");
        List<String> errors = basepriceValidator.validate(basepriceMapper.dtoToEntity(basepriceDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le baseprice envoye pour enregistrement n'est pas valide ", errors,
                    ErrorCode.BASEPRICE_NOT_VALID.name());
        }

        if(basepriceDto.getBpCode() != null) {
            if (!isBasepriceCodeUsableInPos(basepriceDto.getBpCode(), basepriceDto.getBpPosId())) {
                throw new DuplicateEntityException("Un baseprice existe deja dans le systeme avec le meme code ",
                        ErrorCode.BASEPRICE_DUPLICATED.name());
            }
        }

        Baseprice basepriceToSave = Baseprice.builder()
                .bpPos(posMapper.dtoToEntity(posService.getPointofsaleById(basepriceDto.getBpPosId())))
                .bpCurrency(currencyMapper.dtoToEntity(basepriceDto.getBpCurrency()))
                .bpCode(basepriceDto.getBpCode())
                .bpDetailsprice(basepriceDto.getBpDetailsprice())
                .bpPrecompte(basepriceDto.getBpPrecompte())
                .bpPurchaseprice(basepriceDto.getBpPurchaseprice())
                .bpWholeprice(basepriceDto.getBpWholeprice())
                .bpRistourne(basepriceDto.getBpRistourne())
                .bpSemiwholeprice(basepriceDto.getBpSemiwholeprice())
                .build();
        //Baseprice basepriceToSave = basepriceMapper.dtoToEntity(basepriceDto);
        log.info("On peut donc tout enregistrer dans la BD");
        Baseprice basepriceSaved = basepriceDao.save(basepriceToSave);

        return basepriceMapper.entityToDto(basepriceSaved);
    }

    Boolean isBasepriceCodeUsableInPos(String bpCode, Long posId){
        if(bpCode == null || posId == null){
            throw new NullValueException("Les arguments envoyes sont nuls");
        }
        Optional<Baseprice> optionalBaseprice = basepriceDao.findBasepriceByCodeInPos(bpCode, posId);
        return optionalBaseprice.isEmpty();
    }

    @Override
    public BasepriceDto updateBaseprice(BasepriceDto basepriceDto) {
        log.info("Tout commenece par se rassurer que le Dto envoye n'est pas null");
        if(basepriceDto == null){
            throw new NullValueException("Le basepriceDto envoye ne saurait etre null");
        }
        log.info("On se rassure que l'id du baseprice a update n'est pas null");
        if(basepriceDto.getId() == null){
            throw new NullValueException("L'id du baseprice a update est null");
        }
        log.info("On peut donc rechercher le Baseprice a update en BD");
        Optional<Baseprice> optionalBaseprice = basepriceDao.findBasepriceById(basepriceDto.getId());
        if(!optionalBaseprice.isPresent()){
            throw new ModelNotFoundException("Aucun baseprice n'existe dans le systeme avec l'id envoye",
                    ErrorCode.BASEPRICE_NOT_FOUND.name());
        }
        Baseprice basepriceToUpdate = optionalBaseprice.get();
        log.info("On commence les validations ");
        List<String> errorsDto = basepriceValidator.validate(basepriceDto);
        if(!errorsDto.isEmpty()){
            throw new InvalidEntityException("Le basepriceDto envoye pour enregistrement n'est pas valide ", errorsDto,
                    ErrorCode.BASEPRICE_NOT_VALID.name());
        }
        log.info("On va valider l'entity assicie au Dto envoye");
        List<String> errors = basepriceValidator.validate(basepriceMapper.dtoToEntity(basepriceDto));
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Le baseprice envoye pour enregistrement n'est pas valide ", errors,
                    ErrorCode.BASEPRICE_NOT_VALID.name());
        }
        log.info("On prepare donc les mises a jour");

        if(basepriceDto.getBpCode() != null){
            if(basepriceToUpdate.getBpCode() != null){
                if(!basepriceDto.getBpCode().equals(basepriceToUpdate.getBpCode())){
                    if(!isBasepriceCodeUsableInPos(basepriceDto.getBpCode(), basepriceDto.getBpPosId())){
                        throw new DuplicateEntityException("Il existe deja un baseprice avec ce code dans ce pointofsale ",
                                ErrorCode.BASEPRICE_DUPLICATED.name());
                    }
                }
            }
        }

        basepriceToUpdate.setBpCode(basepriceDto.getBpCode());

        basepriceToUpdate.setBpCurrency(currencyMapper.dtoToEntity(basepriceDto.getBpCurrency()));
        basepriceToUpdate.setBpDetailsprice(basepriceDto.getBpDetailsprice());
        basepriceToUpdate.setBpPrecompte(basepriceDto.getBpPrecompte());
        basepriceToUpdate.setBpPurchaseprice(basepriceDto.getBpPurchaseprice());
        basepriceToUpdate.setBpWholeprice(basepriceDto.getBpWholeprice());
        basepriceToUpdate.setBpRistourne(basepriceDto.getBpRistourne());
        basepriceToUpdate.setBpSemiwholeprice(basepriceDto.getBpSemiwholeprice());

        log.info("On peut donc enregistrer les mise a jour");
        Baseprice basepriceUpdated = basepriceDao.save(basepriceToUpdate);

        return basepriceMapper.entityToDto(basepriceUpdated);
    }

    @Override
    public Boolean deleteBasepriceById(Long id) {
        log.info("On se rassure de l'id n'est pas null");
        if(id == null){
            throw new NullValueException("L'id envoye ne saurait etre null");
        }
        log.info("On fait la recherche du baseprice a supprimer");
        Optional<Baseprice> optionalBaseprice = basepriceDao.findBasepriceById(id);
        if(!optionalBaseprice.isPresent()){
            throw new ModelNotFoundException("Aucun baseprice n'existe avec l'id envoye ",
                    ErrorCode.BASEPRICE_NOT_FOUND.name());
        }
        log.info("on se rassure que le baseprice peut etre supprimer sans conflits");
        if(!isBasepriceDeleteable(optionalBaseprice.get())){
            throw new EntityNotDeleatableException("On ne peut supprimer le baseprise sans causer de conflit",
                    ErrorCode.BASEPRICE_NOT_DELETEABLE.name());
        }
        log.info("On peut donc effectuer la suppression");
        basepriceDao.delete(optionalBaseprice.get());
        return true;
    }

    Boolean isBasepriceDeleteable(Baseprice baseprice){
        return true;
    }

    @Override
    public BasepriceDto getBasepriceById(Long id) {
        log.info("On se rassure de l'id n'est pas null");
        if(id == null){
            throw new NullValueException("L'id envoye ne saurait etre null");
        }
        log.info("On fait la recherche du baseprice a supprimer");
        Optional<Baseprice> optionalBaseprice = basepriceDao.findBasepriceById(id);
        if(!optionalBaseprice.isPresent()){
            throw new ModelNotFoundException("Aucun baseprice n'existe avec l'id envoye ",
                    ErrorCode.BASEPRICE_NOT_FOUND.name());
        }
        log.info("On peut donc retourner le baseprice retrouver en BD");
        return basepriceMapper.entityToDto(optionalBaseprice.get());
    }

    @Override
    public Boolean isBasepriceExistWith(Long id) {
        log.info("On se rassure de l'id n'est pas null");
        if(id == null){
            throw new NullValueException("L'id envoye ne saurait etre null");
        }
        log.info("On fait la recherche du baseprice a supprimer");
        Optional<Baseprice> optionalBaseprice = basepriceDao.findBasepriceById(id);
        return optionalBaseprice.isPresent();
    }
}
