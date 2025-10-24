package com.udb.proyectofinalaerolinea.filters;

import com.udb.proyectofinalaerolinea.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);
    private static final String BEARER_PREFIX = "Bearer ";
    private static final AntPathMatcher matcher = new AntPathMatcher();

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Rutas que NO deben pasar por validación JWT
     * (Swagger/OpenAPI, estáticos, auth público y vistas públicas).
     */
    private static final String[] SKIP_URLS = {
            // Swagger / OpenAPI
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",

            // Recursos estáticos
            "/css/**", "/js/**", "/img/**", "/webjars/**", "/favicon.ico",

            // Páginas públicas
            "/", "/index",
            "/VueloCliente", "/VerListadoVuelos", "/InsertarDatosComprador",
            "/ConfirmacionVuelo", "/MetodoPago", "/CancelacionVuelo", "/Reclamo",

            // Auth público
            "/auth/login", "/auth/registro",

            // Errores / health (opcional)
            "/error"
    };

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Evitar filtrar preflight CORS
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) return true;

        String path = request.getServletPath();
        for (String p : SKIP_URLS) {
            if (matcher.match(p, path)) {
                return true; // NO aplicar el filtro en estas rutas
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // Si no trae Authorization o no es Bearer, continua sin autenticar (no forzamos error)
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(BEARER_PREFIX.length());

        String username;
        try {
            username = jwtService.extractUsername(token);
        } catch (Exception ex) {
            log.debug("No se pudo extraer username del JWT: {}", ex.getMessage());
            filterChain.doFilter(request, response);
            return;
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails;
            try {
                userDetails = userDetailsService.loadUserByUsername(username);
            } catch (Exception ex) {
                log.debug("Usuario no encontrado para username extraído del token: {}", username);
                filterChain.doFilter(request, response);
                return;
            }

            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                log.debug("JWT inválido para usuario: {}", username);
            }
        }
        filterChain.doFilter(request, response);
    }
}
