package com.c2psi.bmv1.auth.config;

import com.c2psi.bmv1.auth.services.LoadUserbmService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    final LoadUserbmService loadUserbmService;

    @Bean
    public UserDetailsService userDetailsService() {
        System.err.println("construction du bean UserDetailsService charge de se rassurer que le User existe avec le " +
                "non password");
        /**********************************
         * Il faut d'abord faire la recherche du userbm en BD
         */
        return username -> loadUserbmService.loadUserByUsername(username);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        System.err.println("construction du authentication provider");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        System.err.println("Affectation du Bean userDetailsService et du bean passwordEncoder au AuthenticationProvider");
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        System.err.println("construction du authentication manager");
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        System.err.println("construction du passwordEncoder");
        return new BCryptPasswordEncoder();
    }

}
