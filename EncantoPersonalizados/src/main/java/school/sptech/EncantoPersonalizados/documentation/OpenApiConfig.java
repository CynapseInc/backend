package school.sptech.EncantoPersonalizados.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Encanto Personalizados API")
                        .version("v1")
                        .description("API Rest com intuito de servir a aplicação front end encantos personalizados," +
                                "Sistema de gestão de pedidos, controle financeiro e catálogo de produtos"));
    }
}
