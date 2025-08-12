package com.stackroute.springcloudapigateway.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@Configuration
public class AppConfig {
	
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
        		
                .route(p -> p
                        .path("/api/v1/**")
                        .uri("lb://user-authentication-service"))
                .route(p->p
                .path("/api/v2/user/**","/api/v2/register")
                        .uri("lb://user-favorite-service"))
                .route(p->p
                        .path("/api/v3/books/**")
                                .uri("lb://recommendation-service"))
                .build();
    }
//    
//    @Bean
//    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//        http.csrf(ServerHttpSecurity.CsrfSpec::disable)
//            .authorizeExchange(exchanges -> exchanges
//                .pathMatchers("/api/v1/login", "/api/v1/userr").permitAll() // Public
//                .anyExchange().authenticated() // Everything else requires JWT
//            )
//            .oauth2ResourceServer(oauth2 -> oauth2.jwt());
//        return http.build();
//    }
//
//    @Bean
//    public ReactiveJwtDecoder jwtDecoder() {
//        String secret = "my-secret-key";
//        return NimbusReactiveJwtDecoder
//                .withSecretKey(Keys.hmacShaKeyFor(secret.getBytes()))
//                .build();
    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOrigin("http://localhost:4200");
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}





