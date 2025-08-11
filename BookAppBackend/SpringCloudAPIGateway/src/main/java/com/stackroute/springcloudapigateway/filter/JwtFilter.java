//package com.stackroute.springcloudapigateway.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.oauth2.jwt.JwtException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import reactor.core.publisher.Mono;
//
//@Component
//public class JwtFilter implements GlobalFilter {
//
//    private final String secret = "my-secret-key";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//    	
//        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7);
//            try {
//                Claims claims = Jwts.parser()
//                        .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
//                        .build()
//                        .parseClaimsJws(token)
//                        .getBody();
//
//                exchange = exchange.mutate().request(r -> r
//                        .headers(h -> {
//                            h.add("X-User-Id", claims.getSubject());
//                            h.add("X-User-Roles", claims.get("roles").toString());
//                        })
//                ).build();
//
//            } catch (JwtException e) {
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                return exchange.getResponse().setComplete();
//            }
//        } else {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();
//        }
//
//        return chain.filter(exchange);
//    }
//}
