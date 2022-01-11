package net.mikhailov.books.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    /**
//     * Первый вариант своей реализации UserDetailsService и PasswordEncoder через @Bean
//     *
//     * @return
//     */
//    @Bean
//    public UserDetailsService userDetailsService() {
//        var userDetailService = new InMemoryUserDetailsManager();
//        var user = User.withUsername("evgenii")
//                .password("test")
//                .authorities("read")
//                .build();
//        userDetailService.createUser(user);
//        return userDetailService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()); //Включаем CSRF в Cookie
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().and().logout().deleteCookies("JSESSIONID");
//        http.authorizeRequests().anyRequest().permitAll(); //разрешить все
    }
//
    /**
     * Второй вариант  реализации UserDetailsService и PasswordEncoder через configure(auth)
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var userDetailService = new InMemoryUserDetailsManager();
        var user = User.withUsername("evgenii")
                .password("$2a$12$XUmcAD.cbCo9qM9LDO6Tmuq5/63wQrRjBiMslth68wcUtyldGYVX6")
                .authorities("read")
                .build();
        userDetailService.createUser(user);

        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
//        Еще вариант :
//        auth.inMemoryAuthentication()
//                    .withUser("evgenii")
//                    .password("test")
//                    .authorities("read")
//                .and()
//                    .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
