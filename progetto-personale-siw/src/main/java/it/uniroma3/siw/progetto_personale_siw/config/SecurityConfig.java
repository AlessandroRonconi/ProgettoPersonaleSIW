package it.uniroma3.siw.progetto_personale_siw.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//fai errrori con global extections handler
//questo è backend, decido chi puo fare cosa
@Configuration
@EnableWebSecurity // Attiva Spring Security.
public class SecurityConfig {

    private final DataSource dataSource;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    public SecurityConfig(DataSource dataSource,
            CustomAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.dataSource = dataSource;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        // le due query sotto servono a:
        // Quando un utente fa login, vai nel database e usa queste query per trovare
        // password e ruolo
        manager.setUsersByUsernameQuery(
                "SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
        manager.setAuthoritiesByUsernameQuery(
                "SELECT username, ruolo FROM credentials WHERE username=?");

        return manager;
    }

    @Bean // il metodo seguente produce un oggetto che Spring deve gestire"
    public SecurityFilterChain filterChain(HttpSecurity httpSecuity) throws Exception {

        httpSecuity.authorizeHttpRequests(authorize -> {
            /*
             * authorize.requestMatchers().hasAuthority();
             * authorize.requestMatchers().hasAuthority();
             * authorize.requestMatchers().hasAnyAuthority();
             * authorize.requestMatchers().hasAnyAuthority();
             */
            authorize.requestMatchers(HttpMethod.GET,
                    "/", "/index", "/login", "/register",
                    "/css/**", "/images/**", "/favicon.ico","/corsi","/tipi_abbonamenti","/corsi/*/commenti").permitAll();
            authorize.requestMatchers(HttpMethod.POST, "/register", "/login").permitAll();

            authorize.anyRequest().authenticated();

        });
        httpSecuity.formLogin(form -> {
            form.loginPage("/login").permitAll();
            form.defaultSuccessUrl("/", true);
            form.failureHandler(customAuthenticationFailureHandler);// gestisce errori di login
        });

        httpSecuity.logout(logout -> { // implemetna qualcosa per logout
            logout.logoutUrl("/logout");
            logout.logoutSuccessUrl("/");
            logout.invalidateHttpSession(true);
            logout.deleteCookies("JSESSIONID");
            logout.clearAuthentication(true);
            logout.permitAll();
        });

        return httpSecuity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}