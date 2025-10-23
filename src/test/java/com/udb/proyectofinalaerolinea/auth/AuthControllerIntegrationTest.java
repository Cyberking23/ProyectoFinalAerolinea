package com.udb.proyectofinalaerolinea.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udb.proyectofinalaerolinea.model.Administrador;
import com.udb.proyectofinalaerolinea.model.Rol;
import com.udb.proyectofinalaerolinea.model.Usuario;
import com.udb.proyectofinalaerolinea.repository.AdministradorRepository;
import com.udb.proyectofinalaerolinea.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@ActiveProfiles("integration")
@Transactional
class AuthControllerIntegrationTest {

    @Autowired MockMvc mockMvc;
    @Autowired UsuarioRepository usuarioRepo;
    @Autowired AdministradorRepository adminRepo;
    @Autowired PasswordEncoder passwordEncoder;

    private final ObjectMapper mapper = new ObjectMapper();

    private static final String ADMIN_EMAIL = "admin@correo.com";
    private static final String ADMIN_RAW_PASS = "secreto";

    @BeforeEach
    void seedAdmin() {
        if (adminRepo.findByEmail(ADMIN_EMAIL).isEmpty()) {
            Usuario u = new Usuario();
            u.setPassword(passwordEncoder.encode(ADMIN_RAW_PASS));
            u.setRol(Rol.ADMIN);        // enum: ADMIN / CLIENTE
            u.setEstado("Activo");
            usuarioRepo.save(u);

            Administrador a = new Administrador();
            a.setEmail(ADMIN_EMAIL);
            a.setNombre("Admin Test");
            a.setUsuario(u);
            adminRepo.save(a);
        }
    }

    @Test
    void loginOk_devuelveToken() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {"username":"admin@correo.com","password":"secreto"}
                """))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.rol").value("ADMIN"));
    }

    @Test
    void tokenSinJwt_401() throws Exception {
        mockMvc.perform(get("/auth/token"))
                .andExpect(status().isUnauthorized()); // Si tu filtro devuelve 403, cambia a isForbidden()
    }

    @Test
    void tokenConJwt_200() throws Exception {
        // 1) Login para obtener token
        String loginJson = mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {"username":"admin@correo.com","password":"secreto"}
                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        String token = extractToken(loginJson);
        assertThat(StringUtils.hasText(token)).isTrue();

        // 2) Llamar /auth/token con Bearer
        mockMvc.perform(get("/auth/token")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.rol").value("ADMIN"));
    }

    private String extractToken(String json) throws Exception {
        JsonNode node = mapper.readTree(json);
        return node.path("token").asText(null);
    }
}
