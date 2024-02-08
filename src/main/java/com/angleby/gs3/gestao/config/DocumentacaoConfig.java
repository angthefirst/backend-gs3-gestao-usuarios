package com.angleby.gs3.gestao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentacaoConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("GS3 Gestão API")
                        .description("API para gestão de usuários e perfis")
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("Angleby Vieira")
                                .email("angleby@gmail.com")));
    }

}
