package com.c2psi.bmv1.auth.config;

import com.c2psi.bmv1.auth.services.AuthValidator;
import com.c2psi.bmv1.auth.token.mappers.TokenMapper;
import com.c2psi.bmv1.auth.token.services.TokenService;
import com.c2psi.bmv1.bmapp.exceptions.BMException;
import com.c2psi.bmv1.dto.TokenDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter  {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenService tokenService;

    //@Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        System.err.println("Lancement du filtre JwtAuthenticationFilter avec sa methode doFilterInternal ");

        final String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String userName = null;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            System.err.println("authHeader " + authHeader + " bearer is also null");
//            System.err.println("La requete doit avoir un header Authorization qui est du type Bearer");

            System.err.println(authHeader != null ? authHeader + "but don't start by Bearer": "authHeader is null");

            filterChain.doFilter(request, response);
            return;
        }


        jwt = authHeader.substring(7);
        //System.err.println("On recupere donc le jwt ==" + jwt);
        try {
            userName = jwtService.extractUsername(jwt);
            System.err.println("On recupere ensuite le userName == " + userName);

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                System.err.println("userName == " + userName + " SecurityContextHolder.getContext().getAuthentication() " +
//                        SecurityContextHolder.getContext().getAuthentication());
                System.err.println("On va donc rechercher le User dans la BD");
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
                System.err.println("Le user etant trouver on va verifier si le token recuperer l'appartient vraiment et " + "n'est pas encore expire");

                TokenDto tokenDto = tokenService.getTokenByValue(jwt);

                var isTokenValidInBD = !tokenDto.getExpired() && !tokenDto.getRevoked();

                if (jwtService.isTokenValid(jwt, userDetails) && isTokenValidInBD) {
                    //System.err.println("ICI on est sur que le token est valid");
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
                            null, userDetails.getAuthorities());
                    //System.err.println("On a donc fabriquer un UsernamePasswordAuthenticationToken " + authToken);
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    //System.err.println("On a set les details de ce UsernamePasswordAuthenticationToken fabrique");
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    //System.err.println("On a place ce UsernamePasswordAuthenticationToken fabrique dans le contexte de securite");
                }
                //System.err.println("le filtre JwtAuthenticationFilter a donc fini son travail ");
            }
            System.err.println("Il laisse donc la requete http continuer sa route ");
            filterChain.doFilter(request, response);
        }
        catch (Exception e){
            log.error("Error: The error message is {} ", e.getMessage());
            filterChain.doFilter(request, response);
        }
    }
}
