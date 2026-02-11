package com.example.spring_patitas.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Server URL - Desarrollo");

        Contact contact = new Contact();
        contact.setName("Equipo Veterinaria Patitas");
        contact.setEmail("contacto@veterinariapatitas.com");
        contact.setUrl("https://www.veterinariapatitas.com");

        License license = new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.html");

        Info info = new Info()
                .title("API REST Veterinaria Patitas")
                .version("1.0.0")
                .contact(contact)
                .description("API para la gestión de citas veterinarias implementada con Clean Architecture. " +
                        "Permite crear, consultar, listar y actualizar el estado de las citas.")
                .termsOfService("https://www.veterinariapatitas.com/terms")
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}
