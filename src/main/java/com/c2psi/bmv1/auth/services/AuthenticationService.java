package com.c2psi.bmv1.auth.services;

import com.c2psi.bmv1.auth.config.JwtService;
import com.c2psi.bmv1.auth.models.ExtendedUser;
import com.c2psi.bmv1.auth.models.UserbmRolePermission;
import com.c2psi.bmv1.auth.permission.services.PermissionService;
import com.c2psi.bmv1.auth.token.mappers.TokenMapper;
import com.c2psi.bmv1.auth.token.models.Token;
import com.c2psi.bmv1.auth.token.services.TokenService;
import com.c2psi.bmv1.bmapp.enumerations.TokenType;
import com.c2psi.bmv1.bmapp.exceptions.FailedAuthenticationException;
import com.c2psi.bmv1.bmapp.exceptions.JwtBearerAuthenticationException;
import com.c2psi.bmv1.dto.*;
import com.c2psi.bmv1.userbm.mappers.UserbmMapper;
import com.c2psi.bmv1.userbm.services.UserbmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "AuthenticationServiceV1")
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    final UserbmService userbmService;
    final UserbmMapper userbmMapper;
    final PasswordEncoder passwordEncoder;
    final JwtService jwtService;
    final AuthenticationManager authenticationManager;
    final TokenService tokenService;
    final TokenMapper tokenMapper;
    final LoadUserbmService loadUserbmService;
    final AuthValidator authValidator;

    public AuthResponse authenticate(AuthRequest request) {
        System.err.println("Lancement de la fonction authenticate du service AuthenticationService ");
        System.err.println("Et dans authenticate on a  email = " + request.getLogin() + " et password =" + request.getPassword());
        List<String> errors = authValidator.validate(request);
        boolean isUserbmAuthenticated = true;
        if (!errors.isEmpty()) {
            throw new JwtBearerAuthenticationException("BAD REQUEST");
        }
        try {
            System.err.println("on lance le authenticate du authenticationManager");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getLogin(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            //e.printStackTrace();
            isUserbmAuthenticated = false;
            log.error("Echec de l'authentification avec les parametres saisis " + request.toString() + " " +
                    "message d'exception " + e.getMessage());
        }

        if (isUserbmAuthenticated){
            log.error("Reussite de l'authentification grace a l'authenticationManager ");
            ExtendedUser extendedUser = (ExtendedUser) loadUserbmService.loadUserByUsername(request.getLogin());

            var jwtToken = jwtService.generateToken(extendedUser);
            revokeAllExistingTokenListofUser(extendedUser.getUserbmId());
            TokenDto tokenDto = tokenService.saveToken(extendedUser.getUserbmId());

            AuthResponse authResponse = new AuthResponse();
            authResponse.setAccessToken(tokenDto.getTokenValue());
            return authResponse;
        }
        else{
            log.error("Echec de l'authentification grace a l'authenticationManager ");
            throw new FailedAuthenticationException("Echec de l'authentification : " +
                    "Verifier les parametres de connexion envoyes");
        }
    }

    private void revokeAllExistingTokenListofUser(Long userbmId){
        var validUserTokenList = tokenService.findAllValidTokenListByUserbm(userbmId);
        if(validUserTokenList.isEmpty()) return;
        validUserTokenList.forEach(token -> {
            tokenService.expireToken(token);
            tokenService.revokeToken(token);
        });
    }


}
