package net.mikhailov.books.library.config.security;

import lombok.RequiredArgsConstructor;
import net.mikhailov.books.library.domain.security.authority.AuthorityType;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationProvider authenticationProvider;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        //Разрешаем CORS GET запросы с origin library.mikhailov.net
//        http.cors(c -> {
//            CorsConfigurationSource corsConfig = req -> {
//                var config = new CorsConfiguration();
//                config.setAllowedOrigins(List.of("https://library.mikhailov.net"));
//                config.setAllowedMethods(List.of("GET"));
//                return config;
//            };
//            c.configurationSource(corsConfig);
//        });
        http.httpBasic();
        http.oauth2Login();
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); //Включаем CSRF в Cookie
        http.formLogin().and().logout().deleteCookies("JSESSIONID");
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.POST,"/api/v1/**").hasRole(AuthorityType.ROLE_ADMIN.getRole())
                .mvcMatchers(HttpMethod.DELETE,"/api/v1/**").hasRole(AuthorityType.ROLE_ADMIN.getRole())
                .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}
