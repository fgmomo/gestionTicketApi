
package com.gestionticket.ticket.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("GesTicket")
                        .description("Api de Gestion de Ticket au sein de Kalanso")
                        .version("0.0.1")
                        .contact(new Contact().name("Mohamed").email("konemohamedflo@gmail.com"))


                );
    }
}
