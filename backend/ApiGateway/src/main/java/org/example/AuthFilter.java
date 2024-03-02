package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {
    @Value("${spring.cloud.gateway.routes[0].uri}")
    private String baseUri;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {



        System.out.println(exchange.getRequest().getMethod());
        System.out.println(exchange.getRequest().getPath());
        System.out.println(exchange.getRequest().getHeaders().get("Authorization"));

        if (permitAll(exchange)) return chain.filter(exchange);
        if (permitAdmin(exchange)) return chain.filter(exchange);


        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    public List<String> getUserRoles(String token) {
        try {
            String encodedToken = URLEncoder.encode(token, StandardCharsets.UTF_8);

            URI uri = new URI( baseUri + "/user/get-user-roles?token=" + encodedToken);
            var restTemplate = new RestTemplate();
            var response = restTemplate.getForObject(uri, List.class);
            log.info("roles  are => "+response);
            return response;
        } catch (URISyntaxException | HttpClientErrorException e) {
            log.info("URI syntax error!");
            return new ArrayList<>();
        }
    }

    public boolean permitAll(ServerWebExchange exchange) {
        return exchange.getRequest().getPath().toString().equals("/user/get-all") ||
                exchange.getRequest().getPath().toString().equals("/yacht/get-all") ||
                exchange.getRequest().getPath().toString().equals("/user/login");
    }

    public boolean permitAdmin(ServerWebExchange exchange) {
        var header = exchange.getRequest().getHeaders().get("Authorization");
        var token = header.get(0).split(" ")[1];
        System.out.println(token);
        var roles = getUserRoles(token);

        return roles.contains(Roles.ADMIN.toString()) && (exchange.getRequest().getMethod().equals(HttpMethod.DELETE));
    }
}
