package me.jincrates.api.global.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    private final String SECURITY_SCHEME_KET = "bearerAuth";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info())
                .components(securityScheme())
                .security(securityList());
    }

    private Info info() {
        return new Info()
                .title("boiler-plate")
                .description("보일러 플레이트 API")
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
