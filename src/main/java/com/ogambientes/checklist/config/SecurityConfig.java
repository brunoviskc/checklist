package com.ogambientes.checklist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
 * @Configuration: Diz ao Spring que este é um manual de configurações da fábrica.
 * @EnableWebSecurity: Ativa a personalização da segurança.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desativa a proteção CSRF. Em APIs REST (que se comunicam por JSON),
                // essa proteção padrão de formulários web não é necessária e bloqueia nossos testes.
                .csrf(csrf -> csrf.disable())

                // Configura as catracas da portaria
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // "Permita a entrada de todas as requisições, sem perguntar quem é"
                );

        return http.build();
    }
}