package com.c2psi.bmv1.auth.token.services;

import com.c2psi.bmv1.dto.TokenDto;

import java.util.List;

public interface TokenService {
    TokenDto saveToken(TokenDto tokenDto);
    TokenDto saveToken(Long userbmId);
    TokenDto expireToken(TokenDto tokenDto);
    TokenDto revokeToken(TokenDto tokenDto);
    Boolean isTokenValid(TokenDto tokenDto);
    Boolean deleteToken(Long id);
    TokenDto getTokenById(Long id);
    TokenDto getTokenByValue(String tokenValue);
    List<TokenDto> findAllValidTokenListByUserbm(Long userbmId);
}
