package com.udb.proyectofinalaerolinea.auth;

import com.udb.proyectofinalaerolinea.repository.UsuarioRepository;
import com.udb.proyectofinalaerolinea.service.JwtService;
import com.udb.proyectofinalaerolinea.service.JwtServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest(
        classes = JwtServiceImpl.class, // solo el servicio bajo prueba
        properties = {
                "app.jwt.secret=0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF",
                "app.jwt.expiration-ms=3600000"
        }
)
class JwtServiceTest {

    @MockBean
    private UsuarioRepository usuarioRepository;   // <- mock para satisfacer el constructor

    @Autowired
    private JwtService jwtService;

    @Test
    void generarYValidarToken() {
        // No necesitamos UID en el claim para esta prueba -> devolvemos empty
        when(usuarioRepository.findActivoByEmail("testuser")).thenReturn(Optional.empty());

        UserDetails user = User.withUsername("testuser")
                .password("1234")
                .roles("ADMIN")
                .build();

        String token = jwtService.generateToken(user);

        assertThat(token).isNotNull();
        assertThat(jwtService.isTokenValid(token, user)).isTrue();
        assertThat(jwtService.extractUsername(token)).isEqualTo("testuser");
    }
}
