package dev.luanfernandes.gameawards.core.openapi;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {

    @Bean
    public GroupedOpenApi groupedOpenApiV1() {
        return GroupedOpenApi.builder()
                .group("V1")
                .packagesToScan("dev.luanfernandes.gameawards.api.v1")
                .build();
    }

    @Bean
    public OpenAPI openAPIV1() {
        return new OpenAPI()
                .tags(List.of(
                        new Tag().name("Game").description("Endpoint de games")
                ))
                .info(new Info()
                        .title("Game Awards APi")
                        .description("Game Awards Api Application")
                        .version("v1.0.1")
                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
                        .contact(new Contact()
                                .name("Luan Fernandes")
                                .email("souluanf@icloud.com")
                                .url("https://linkedin.com/in/souluanf"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("SpringShop Wiki Documentation")
                        .url("https://springshop.wiki.github.org/docs"));
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui/index.html");
        registry.addRedirectViewController("/swagger-ui", "/swagger-ui/index.html");
    }
}