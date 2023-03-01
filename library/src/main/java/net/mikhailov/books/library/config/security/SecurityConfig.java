package net.mikhailov.books.library.config.security;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.domain.security.authority.AuthorityType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        http.authenticationManager(authenticationManagerBuilder.build());

        http.httpBasic();
        http.oauth2Login();
        //Временно отключил csrf
        http.csrf().disable();
        http.formLogin().and().logout().deleteCookies("JSESSIONID");
        http.authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole(AuthorityType.ROLE_ADMIN.getRole())
                .requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole(AuthorityType.ROLE_ADMIN.getRole())
                .anyRequest().permitAll();
        return http.build();
    }

}
