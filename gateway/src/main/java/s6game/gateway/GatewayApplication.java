package s6game.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    private static String AUTH_URI = "http://localhost:8081";
    private static String LOBBY_URI = "http://localhost:8082";

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder)
    {
        return builder.routes()
                      .route(a -> a
                              .path("/users/**")
                              .filters(f -> f.addRequestHeader("test", "Hello World"))
                              .uri(AUTH_URI))
                      .route(l -> l
                              .path("/api/**")
                              .filters(f -> f.addRequestHeader("test", "Hello World"))
                              .uri(LOBBY_URI))
                      .build();
    }
}
