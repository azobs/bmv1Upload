package com.c2psi.bmv1.auth.config;

import com.c2psi.bmv1.bmapp.enumerations.RoleTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.err.println("Configuration de la securite sur l'application ");
        String[] pathAllowedToAll = new String[]{"/auth/bm/v1/**", "/address/bm/v1/**", "/userbm/bm/v1/**",
                "/role/bm/v1/**", "/userbm_role/bm/v1/**", "/category/bm/v1/**", "/product/bm/v1/**", "/format/bm/v1/**",
                "/pf/bm/v1/**", "/unit/bm/v1/**", "/article/bm/v1/**", "/bp/bm/v1/**", "/sp/bm/v1/**", "/loading/bm/v1/**",
                "/arrival/bm/v1/**", "/supplyinvoice/bm/v1/**", "/command/bm/v1/**", "/sale/bm/v1/**", "/delivery/bm/v1/**",
                "/backin/bm/v1/**", "/saleinvoice/bm/v1/**", "/pos/bm/v1/**", "/enterprise/bm/v1/**", "/client/bm/v1/**",
                "/clientspecialprice/bm/v1/**", "/provider/bm/v1/**", "/currency/bm/v1/**", "/account/bm/v1/**",
                "/operation/bm/v1/**", "/inventory/bm/v1/**", "/packaging/bm/v1/**", "/upload/bm/v1/**", "/load/bm/v1/**",
                "/swagger-ui.html","/**/v2/api-docs","/swagger-resources","/swagger-resources/**","/configuration/ui",
                "/configuration/security","/webjars/**","/v3/api-docs/**","/swagger-ui/**"};
        http
                .csrf()
                .disable()
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests()
                //.requestMatchers("/bm/v1/auth/**")
                .requestMatchers(pathAllowedToAll)
                .permitAll()

                //Ajout du 29-07-2023 pour la gestion des permissions sur les endpoint
                //Secure all the management endpoint
                .requestMatchers("/test/non_secure").hasAnyRole(RoleTypeEnum.ADMINBM.name(), RoleTypeEnum.EMPLOYE.name())
                //.requestMatchers("/test/secure").hasAnyRole(RoleTypeEnum.ADMINBM.name())
//
//                //Secure each method on that management endpoint
                .requestMatchers(GET, "/test/secure").hasAnyAuthority("ALLOW_TO_CREATE")
//                .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_POST.name(), MANAGER_POST.name())
//                .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_PUT.name(), MANAGER_PUT.name())
//                .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())

                //About the admin endpoint where only admin can acess
                //.requestMatchers("/api/v1/admin/**").hasRole(Admin.name())

                //Secure each method on that admin endpoint
                /*.requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
                .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_POST.name())
                .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_PUT.name())
                .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())*/

                /*****
                 * Les lignes ci-dessus concernant /api/v1/admin/** ajouter le 29-07-2023
                 * seront mise en commentaire pour ajouter plutot obtenir le meme resultat
                 * en decentralisant la securite au niveau des controller en utilisant les
                 * annotations
                 */

                //Fin des ajouts du 29-07-2023
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                //Ajout du 28-07-2023 pour gerer le logout
                .logout()
                .logoutUrl("/api/v1/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        //fin des ajouts du 28-07-2023
        ;
        return http.build();
    }

}
