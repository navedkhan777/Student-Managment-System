package Student.Mnaagment.System.Main.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

public class SwaggerConfig {

    public OpenAPI customOpenApi(){

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Student Managment System API")
                                .description("REST APIs for Student Management System")
                                .version("1.0")
                )
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "Bearer Authnetication",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                );
    }
}
