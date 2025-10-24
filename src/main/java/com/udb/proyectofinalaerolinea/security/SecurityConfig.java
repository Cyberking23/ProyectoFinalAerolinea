package com.udb.proyectofinalaerolinea.security;

import com.udb.proyectofinalaerolinea.filters.JwtAuthFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserDetailsService userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    // Rutas públicas de Swagger/OpenAPI
    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**"
    };

    // Recursos estáticos y páginas públicas (ajusta según tu app)
    private static final String[] STATIC_PUBLIC = {
            "/", "/index", "/login",
            "/auth", "/auth/registro",
            "/css/**", "/js/**", "/img/**", "/webjars/**", "/favicon.ico"
    };

    // Vistas MVC públicas de tu flujo de cliente
    private static final String[] MVC_PUBLIC = {
            "/VueloCliente",
            "/VerListadoVuelos",
            "/InsertarDatosComprador",
            "/ConfirmacionVuelo",
            "/MetodoPago",
            "/CancelacionVuelo",
            "/Reclamo"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex.authenticationEntryPoint(
                        (req, res, e) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
                .authorizeHttpRequests(auth -> auth
                        // Swagger/OpenAPI sin autenticación
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        // Recursos estáticos y páginas públicas
                        .requestMatchers(STATIC_PUBLIC).permitAll()
                        .requestMatchers(MVC_PUBLIC).permitAll()
                        // Login JSON público
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        // Rutas con roles (si aplican)
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/cliente/**").hasRole("CLIENTE")
                        // Todo lo demás, requiere autenticación/JWT
                                // ====== SOLO PARA PRUEBAS RÁPIDAS ======
                                .requestMatchers("/api/**").permitAll()

                                .anyRequest().authenticated()
                )
                .authenticationProvider(daoAuthProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider daoAuthProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}