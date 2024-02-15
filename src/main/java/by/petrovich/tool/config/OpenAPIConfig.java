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
    public static final String SERVER_URL_IN_DEVELOPMENT_ENVIRONMENT = "Server URL in Development environment";
    public static final String SERVER_URL_IN_PRODUCTION_ENVIRONMENT = "Server URL in Production environment";
    public static final String EMAIL = "a.piatrovich@gmail.com";
    public static final String NAME = "Alexandr Petrovich";
    public static final String URL = "https://www.measuring-tool.com";
    public static final String LICENSE_NAME = "MIT License";
    public static final String LICENSE_URL = "https://";
    public static final String TITLE = "Measuring-Tool API";
    public static final String VERSION = "1.0";
    public static final String DESCRIPTION = "The Instrument Control System is a Java-based project aimed at managing and tracking measurement" +
            " tools in warehouse facilities at a large-scale engineering enterprise. Upon registration and login to the portal," +
            " the warehouse manager gains the ability to register measurement tools based on their type, lab number, and other " +
            "parameters. Furthermore, the manager can issue tools to enterprise employees, registering the issuance via " +
            "the application. Additionally, the system is designed to send notifications to employees requiring them to submit " +
            "the tools for inspection a specific time before the scheduled verification date, ensuring timely maintenance and " +
            "quality control of the instruments.";
    public static final String TERMS_URL = "https://www.measuring-tool.com/terms";
    @Value("${petrovich.openapi.dev-url}")
    private String devUrl;

    @Value("${petrovich.openapi.prod-url}")
    private String prodUrl;

    @Bean
    public OpenAPI toolOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription(SERVER_URL_IN_DEVELOPMENT_ENVIRONMENT);

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription(SERVER_URL_IN_PRODUCTION_ENVIRONMENT);

        Contact contact = new Contact();
        contact.setEmail(EMAIL);
        contact.setName(NAME);
        contact.setUrl(URL);

        License mitLicense = new License().name(LICENSE_NAME).url(LICENSE_URL);

        Info info = new Info()
                .title(TITLE)
                .version(VERSION)
                .contact(contact)
                .description(DESCRIPTION)
                .termsOfService(TERMS_URL)
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }

}
