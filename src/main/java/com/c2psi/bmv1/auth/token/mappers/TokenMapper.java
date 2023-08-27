package com.c2psi.bmv1.auth.token.mappers;

import com.c2psi.bmv1.auth.token.models.Token;
import com.c2psi.bmv1.dto.TokenDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TokenMapper {
    @Mapping(source = "userbm.id", target = "userbmId")
    @ValueMappings({
            @ValueMapping(source = "Bearer", target = "BEARER"),
            @ValueMapping(source = "Jwt", target = "JWT")
    })
    TokenDto entityToDto(Token token);
    List<TokenDto> entityToDto(List<Token> token);
    @ValueMappings({
            @ValueMapping(source = "BEARER", target = "Bearer"),
            @ValueMapping(source = "JWT", target = "Jwt")
    })
    Token dtoToEntity(TokenDto tokenDto);
    List<Token> dtoToEntity(List<TokenDto> token);
}
