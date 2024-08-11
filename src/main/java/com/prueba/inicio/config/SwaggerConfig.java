package com.prueba.inicio.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;

import java.lang.annotation.Target;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		final String securitySchemeName = "bearerAuth";
		return new OpenAPI()
				.info(new Info().title("PRUEBA TÉCNICA Tecnologías sinergia")
						.description("Esto es un servicio restfull").version("v0.0.1")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("SpringBoot Wiki Documentation")
						.url("https://springboot.wiki.github.org/docs"))
				.addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(new Components().addSecuritySchemes(securitySchemeName, new SecurityScheme()
				.name(securitySchemeName)
				.type(SecurityScheme.Type.HTTP)
				.scheme("bearer")
				.bearerFormat("JWT")))
                .addTagsItem(new Tag().name("Usuarios"))
                .addTagsItem(new Tag().name("Productos"))
				.addTagsItem(new Tag().name("Bodegas"))
				.addTagsItem(new Tag().name("Clientes"))
				.addTagsItem(new Tag().name("Envíos Marítimos"))
				.addTagsItem(new Tag().name("Envíos Terrestres"))
				.addTagsItem(new Tag().name("Autenticación"))
				;
	}
}