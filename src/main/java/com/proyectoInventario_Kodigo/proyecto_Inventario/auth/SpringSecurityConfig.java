package com.proyectoInventario_Kodigo.proyecto_Inventario.auth;

import com.proyectoInventario_Kodigo.proyecto_Inventario.auth.filters.JwtAuthenticationFilter;
import com.proyectoInventario_Kodigo.proyecto_Inventario.auth.filters.JwtValidationFilter;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        //EndPoint Users
                        .requestMatchers(HttpMethod.GET, "/users", "/users/page/{page}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        //EndPoint category
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/code/{code}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/categories/name/{name}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/categories").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/categories/{code}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/categories/{code}").hasRole("ADMIN")

                        //EndPoint brand
                        .requestMatchers(HttpMethod.GET, "/api/v1/bands").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/bands/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/bands/byCode/{code}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/bands/byName/{name}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/bands").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/bands/{code}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/bands/{code}").hasRole("ADMIN")

                        //Endpoint para supplier
                        .requestMatchers(HttpMethod.GET, "/api/v1/suppliers").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/suppliers/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/suppliers/code/{code}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/suppliers/name/{name}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/suppliers").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/suppliers/{code}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/suppliers/{code}").hasRole("ADMIN")

                        //EndPoint stores
                        .requestMatchers(HttpMethod.GET, "/api/v1/stores").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/stores/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/stores/code/{code}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/stores/name/{name}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/stores/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/stores/{code}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/stores/{code}").hasRole("ADMIN")

                        //Endpoint Purchase
                        .requestMatchers(HttpMethod.POST, "/api/v1/purchases/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/purchases/{purchaseId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/purchases/{purchaseId}").hasRole("ADMIN")

                        //Endpoint Receive
                        .requestMatchers(HttpMethod.POST, "/api/v1/receives/store/{storeId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/receives/{receiveId}/store/{storeId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/receives/{receiveId}").hasRole("ADMIN")

                        //Endpoint Sale
                        .requestMatchers(HttpMethod.POST, "/api/v1/sales").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/sales/{saleId}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/sales/{saleId}").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:8080"));
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
                new CorsFilter());
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
