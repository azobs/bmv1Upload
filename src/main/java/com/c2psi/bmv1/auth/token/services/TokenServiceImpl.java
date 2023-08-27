package com.c2psi.bmv1.auth.token.services;

import com.c2psi.bmv1.auth.config.JwtService;
import com.c2psi.bmv1.auth.models.ExtendedUser;
import com.c2psi.bmv1.auth.token.dao.TokenDao;
import com.c2psi.bmv1.auth.token.mappers.TokenMapper;
import com.c2psi.bmv1.auth.token.models.Token;
import com.c2psi.bmv1.bmapp.enumerations.TokenType;
import com.c2psi.bmv1.bmapp.exceptions.*;
import com.c2psi.bmv1.dto.TokenDto;
import com.c2psi.bmv1.auth.token.errorCode.ErrorCode;
import com.c2psi.bmv1.userbm.dao.UserbmDao;
import com.c2psi.bmv1.userbm.models.Userbm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "TokenServiceV1")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{
    final TokenDao tokenDao;
    final TokenValidator tokenValidator;
    final TokenMapper tokenMapper;
    final UserbmDao userbmDao;
    final JwtService jwtService;

    @Override
    public TokenDto saveToken(TokenDto tokenDto) {
        if(tokenDto == null){
            throw new NullValueException("Le token envoye est null");
        }
        /********************************************************
         * On valide les parametres saisis grace au validateur
         */
        List<String> errorsDto = tokenValidator.validate(tokenDto);
        if(!errorsDto.isEmpty()){
            log.error("Entity Token not valid because of {}", errorsDto);
            throw new InvalidEntityException("Les entites lies sont inexistantes en BD ", errorsDto,
                    ErrorCode.TOKEN_NOT_VALID.name());
        }

        List<String> errors = tokenValidator.validate(tokenMapper.dtoToEntity(tokenDto));
        if(!errors.isEmpty()){
            log.error("Entity Token not valid because of {}", errors);
            throw new InvalidEntityException("Le token a enregistrer n'est pas valide ", errors,
                    ErrorCode.TOKEN_NOT_VALID.name());
        }

        /**********************************************************
         * On se rassure que les contraintes d'unicite ne seront
         * pas violees
         */
        if(!isTokenValueUsable(tokenDto.getTokenValue())){
            throw new DuplicateEntityException("Un autre Token existe deja avec la meme valeur en BD ",
                    ErrorCode.TOKEN_DUPLICATED.name());
        }

        /**********************************************************
         * Si tout est bon on effectue l'enregistrement
         */

        List<String> roleAndPermissionListofUserString = new ArrayList<>();
        Userbm userbmFound = userbmDao.findUserbmById(tokenDto.getUserbmId()).get();
        ExtendedUser extendedUser = ExtendedUser.builder()
                .userbmId(userbmFound.getId())
                .password(userbmFound.getUserPassword())
                .username(userbmFound.getUserLogin())
                .roleAndPermissions(roleAndPermissionListofUserString)
                .build();

        log.info("After all verification, the token can be saved safely");
        Token tokenToSaved = Token.builder()
                .userbm(userbmFound)
                .tokenValue(jwtService.generateToken(extendedUser))
                .tokenType(TokenType.Bearer)
                .revoked(false)
                .expired(false)
                .build();

        Token tokenSaved = tokenDao.save(tokenToSaved);
        return tokenMapper.entityToDto(tokenSaved);
    }

    @Override
    public TokenDto saveToken(Long userbmId) {

        List<String> roleAndPermissionListofUserString = new ArrayList<>();

        Userbm userbmFound = userbmDao.findUserbmById(userbmId).get();
        ExtendedUser extendedUser = ExtendedUser.builder()
                .userbmId(userbmFound.getId())
                .password(userbmFound.getUserPassword())
                .username(userbmFound.getUserLogin())
                .roleAndPermissions(roleAndPermissionListofUserString)
                .build();

        Token tokenToSaved = Token.builder()
                .userbm(userbmFound)
                .tokenValue(jwtService.generateToken(extendedUser))
                .tokenType(TokenType.Bearer)
                .revoked(false)
                .expired(false)
                .build();

        Token tokenSaved = tokenDao.save(tokenToSaved);
        return tokenMapper.entityToDto(tokenSaved);
    }

    @Override
    public TokenDto expireToken(TokenDto tokenDto) {
        if(tokenDto == null){
            throw new NullValueException("le token a update est null");
        }
        if(tokenDto.getId() == null){
            throw new NullValueException("The id of the tokenDto sent is null");
        }
        Optional<Token> optionalToken = tokenDao.findTokenById(tokenDto.getId());
        if(!optionalToken.isPresent()){
            throw new ModelNotFoundException("Aucun token avec l'id envoye n'a ete trouve", ErrorCode.TOKEN_NOT_FOUND.name());
        }
        optionalToken.get().setExpired(true);

        return tokenMapper.entityToDto(tokenDao.save(optionalToken.get()));
    }

    @Override
    public TokenDto revokeToken(TokenDto tokenDto) {
        if(tokenDto == null){
            throw new NullValueException("le token a update est null");
        }
        if(tokenDto.getId() == null){
            throw new NullValueException("The id of the tokenDto sent is null");
        }
        Optional<Token> optionalToken = tokenDao.findTokenById(tokenDto.getId());
        if(!optionalToken.isPresent()){
            throw new ModelNotFoundException("Aucun token avec l'id envoye n'a ete trouve", ErrorCode.TOKEN_NOT_FOUND.name());
        }
        optionalToken.get().setRevoked(true);

        return tokenMapper.entityToDto(tokenDao.save(optionalToken.get()));
    }

    @Override
    public Boolean isTokenValid(TokenDto tokenDto) {
        if(tokenDto == null){
            throw new NullValueException("The token sent is null");
        }

        return tokenDto.getExpired().booleanValue() && tokenDto.getRevoked().booleanValue();
    }

    Boolean isTokenValueUsable(String tokenValue){
        Optional<Token> optionalToken = tokenDao.findTokenByTokenValue(tokenValue);
        return optionalToken.isEmpty();
    }

    @Override
    public Boolean deleteToken(Long id) {
        if(id == null){
            throw new NullValueException("La valeur de l'id envoye est null");
        }
        Optional<Token> optionalToken = tokenDao.findTokenById(id);
        if(!optionalToken.isPresent()){
            throw new ModelNotFoundException("Aucun token n'existe avec l'id envoye", ErrorCode.TOKEN_NOT_FOUND.name());
        }

        if(!isTokenDeleteable(optionalToken.get())){
            throw new EntityNotDeleatableException("Le token envoye est en cours d'utilisation et ne peut etre " +
                    "supprime ", ErrorCode.TOKEN_NOT_DELETEABLE.name());
        }

        tokenDao.delete(optionalToken.get());
        return true;
    }

    Boolean isTokenDeleteable(Token token){
        if(token.getRevoked().booleanValue() == true || token.getExpired().booleanValue() == true){
            return false;
        }
        return true;
    }

    @Override
    public TokenDto getTokenById(Long id) {
        if(id == null){
            throw new NullValueException("La valeur de l'id envoye est null");
        }
        Optional<Token> optionalToken = tokenDao.findTokenById(id);
        if(!optionalToken.isPresent()){
            throw new ModelNotFoundException("Aucun token n'existe avec l'id envoye", ErrorCode.TOKEN_NOT_FOUND.name());
        }

        return tokenMapper.entityToDto(optionalToken.get());
    }

    @Override
    public TokenDto getTokenByValue(String tokenValue) {
        if(tokenValue == null){
            throw new NullValueException("La valeur de token envoye est null");
        }
        Optional<Token> optionalToken = tokenDao.findTokenByTokenValue(tokenValue);
        if(!optionalToken.isPresent()){
            throw new ModelNotFoundException("Aucun Token n'existe avec la valeur envoye ",
                    ErrorCode.TOKEN_NOT_FOUND.name());
        }

        return tokenMapper.entityToDto(optionalToken.get());
    }

    @Override
    public List<TokenDto> findAllValidTokenListByUserbm(Long userbmId) {
        var validTokenofUserbm = new ArrayList<TokenDto>();
        var validUserbmTokenList = tokenDao.findAllValidTokenListByUser(userbmId);


        List<String> roleAndPermissionListofUserString = new ArrayList<>();

        Userbm userbmFound = userbmDao.findUserbmById(userbmId).get();
        ExtendedUser extendedUser = ExtendedUser.builder()
                .userbmId(userbmFound.getId())
                .password(userbmFound.getUserPassword())
                .username(userbmFound.getUserLogin())
                .roleAndPermissions(roleAndPermissionListofUserString)
                .build();

        for(Token token: validUserbmTokenList){
            try {
                if (jwtService.isTokenValid(token.getTokenValue(), extendedUser)) {
                    validTokenofUserbm.add(tokenMapper.entityToDto(token));
                }
            }
            catch (Exception e){

            }
        }
        return validTokenofUserbm;
    }
}
