package me.jincrates.global.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    private final String SECURITY_SCHEME_KET = "bearerAuth";

    @Bean
    public GroupedOpenApi ecommerceApi() {
        return GroupedOpenApi.builder()
                .group("ecommerce-api")
                .pathsToMatch(
                        "/api/v1/login",
                        "/api/v1/members/**",
                        "/api/v1/products/**",
                        "/api/v1/orders/**",
                        "/api/v1/carts/**"
                )
                .build();
    }

    @Bean
    public GroupedOpenApi communityApi() {
        return GroupedOpenApi.builder()
                .group("community-api")
                .pathsToMatch(
                        "/api/v1/login",
                        "/api/v1/follows/**"
                )
                .build();
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info())
                .components(securityScheme())
                .security(securityList());
    }

    private Info info() {
        return new Info()
                .title("Jincrates-Project")
                .description("이커머스 + 커뮤니티 API")
                .version("1.0");
    }

    private Components securityScheme() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");

        return new Components().addSecuritySchemes(SECURITY_SCHEME_KET, securityScheme);
    }

    private List<SecurityRequirement> securityList() {
        return Collections.singletonList(new SecurityRequirement().addList(SECURITY_SCHEME_KET));
    }
}
