package net.mikhailov.books.library.config.security;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.domain.security.authority.AuthorityType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        http.authenticationManager(authenticationManagerBuilder.build());

        http.httpBasic(withDefaults());
        http.cors(withDefaults());
        http.oauth2Login(withDefaults());
        //Временно отключил csrf
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(withDefaults());
        http.logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer.deleteCookies("JSESSIONID"));
        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/api/v1/**").hasRole(AuthorityType.ROLE_ADMIN.getRole());
                    authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE, "/api/v1/**").hasRole(AuthorityType.ROLE_ADMIN.getRole());
                    authorizationManagerRequestMatcherRegistry.anyRequest().permitAll();
                }
        );
        return http.build();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("https://library.mikhailov.net"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/v1/*", configuration);
        return source;
    }


}
