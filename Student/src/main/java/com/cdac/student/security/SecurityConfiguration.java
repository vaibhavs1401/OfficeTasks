package com.cdac.student.security;

import com.cdac.student.security.jwt.CustomJWTFilter;
import com.cdac.student.security.jwt.JwtAuthEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private final CustomJWTFilter jwtFilter;
    private final JwtAuthEntryPoint authEntryPoint;
    private final UserDetailsService uds;

    public SecurityConfiguration(CustomJWTFilter jwtFilter,
                                 JwtAuthEntryPoint authEntryPoint,
                                 UserDetailsService uds) {
        this.jwtFilter = jwtFilter;
        this.authEntryPoint = authEntryPoint;
        this.uds = uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService uds,
                                                       PasswordEncoder encoder) {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(uds);
        provider.setPasswordEncoder(encoder);
        return new ProviderManager(provider);
    }

    /**
     * Chain 1: SECURED endpoints only.Matches /student/** and /admin/**
 Requires JWT + roles.Nothing else hits this chain.
     * @param http
     * @return 
     * @throws java.lang.Exception
     */
    @Bean
    @Order(1)
    public SecurityFilterChain securedChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/student/**", "/admin/**")
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(ex -> ex.authenticationEntryPoint(authEntryPoint))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/student/**").hasRole("STUDENT")
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /**
     * Chain 2: PUBLIC fallback. Everything else (including /Student root) is allowed.
     * You can still whitelist static/auth paths explicitly if you want.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain publicChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/**") // catch-all for everything not matched above
            .csrf(csrf -> csrf.disable())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/index", "/home", "/index.html", "/index.jsp",
                    "/auth/**",
                    "/css/**", "/js/**", "/images/**", "/includes/**",
                    "/webjars/**", "/favicon.ico"
                ).permitAll()
                .anyRequest().permitAll()   
            );
        return http.build();
    }
}
