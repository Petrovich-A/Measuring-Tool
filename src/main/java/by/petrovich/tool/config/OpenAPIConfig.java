package by.petrovich.tool.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${petrovich.openapi.dev-url}")
    private String devUrl;

    @Value("${petrovich.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI toolOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("a.piatrovich@gmail.com");
        contact.setName("Alexandr Petrovich");
        contact.setUrl("https://www.measuring-tool.com");

        License mitLicense = new License().name("MIT License").url("https://");

        Info info = new Info()
                .title("Measuring-Tool API")
                .version("1.0")
                .contact(contact)
                .description("The Instrument Control System is a Java-based project aimed at managing and tracking measurement" +
                        " tools in warehouse facilities at a large-scale engineering enterprise. Upon registration and login to the portal," +
                        " the warehouse manager gains the ability to register measurement tools based on their type, lab number, and other " +
                        "parameters. Furthermore, the manager can issue tools to enterprise employees, registering the issuance via " +
                        "the application. Additionally, the system is designed to send notifications to employees requiring them to submit " +
                        "the tools for inspection a specific time before the scheduled verification date, ensuring timely maintenance and " +
                        "quality control of the instruments.")
                .termsOfService("https://www.measuring-tool.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }

}
