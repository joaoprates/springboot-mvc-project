package com.pratesdev.config;

import com.pratesdev.util.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/health",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/products/**",
                                "/categories/**"
                        ).permitAll() // Permitir acesso público ao /health e rotas do Swagger
                        .anyRequest().authenticated() // Requer autenticação para outros endpoints
                )
                .csrf(AbstractHttpConfigurer::disable) // Opcional: desabilita proteção CSRF (não recomendado em produção)
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class); // Adiciona o filtro JWT
        return http.build();
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }
}