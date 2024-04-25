package br.com.dev.api.adopet.infra.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AdoPet API")
                        .description("API Rest da empresa fictícia Adopet, responsável por lidar com abrigos de animais, adoções, pets disponíveis para adoção e tutores responsáveis pelos animais")
                        .contact(new Contact()
                                .name("Time BackEnd")
                                .email("backend@4life.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")));
    }
}
