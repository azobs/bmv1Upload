package com.c2psi.bmv1.auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class OpenAPIConfig {

    @Value("${bmv1.openapi.dev-url}")
    private String devUrl;

    @Value("${bmv1.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("contact@c2psi.com");
        contact.setName("Cedric Azobou");
        contact.setUrl("https://www.c2psi.com");

        License mitLicense = new License().name("C2PSI License").url("https://c2psi.com/licenses/c2psi/");

        Info info = new Info()
                .title("Bussiness Management Service API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage bussiness.").termsOfService("https://www.c2psi.com")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }
}
