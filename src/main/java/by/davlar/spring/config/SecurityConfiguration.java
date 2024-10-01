package by.davlar.spring.config;

import by.davlar.spring.service.dto.RoleDto;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static by.davlar.spring.service.dto.RoleDto.ADMIN;
import static by.davlar.spring.service.dto.RoleDto.OPERATOR;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/users/login", "/users/registration"
                        ).permitAll()
                        .requestMatchers(
                                "/users"
                        ).hasAnyAuthority(
                                ADMIN.getAuthority(), OPERATOR.getAuthority()
                        )
                        .anyRequest().authenticated())
                .formLogin(login -> login
                        .loginPage("/users/login")
                        .defaultSuccessUrl("/users")
                        .permitAll());
        return http.build();
    }
}
