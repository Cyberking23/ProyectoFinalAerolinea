package com.udb.proyectofinalaerolinea.repository;

import com.udb.proyectofinalaerolinea.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /** Usuario ACTIVO cuyo email exista en clientes o administradores */
    @Query(value = """
        SELECT u.* FROM usuarios u
        WHERE u.estado = 'Activo'
        AND (
            u.id IN (SELECT c.usuario_id FROM clientes c WHERE LOWER(c.email) = LOWER(:email))
            OR
            u.id IN (SELECT a.usuario_id FROM administradores a WHERE LOWER(a.email) = LOWER(:email))
        )
    """, nativeQuery = true)
    Optional<Usuario> findActivoByEmail(@Param("email") String email);

    /** ¿El email ya existe en clientes o administradores? */
    @Query(value = """
        SELECT CASE 
            WHEN EXISTS (SELECT 1 FROM clientes WHERE LOWER(email) = LOWER(:email)) 
              OR EXISTS (SELECT 1 FROM administradores WHERE LOWER(email) = LOWER(:email))
            THEN 1 
            ELSE 0 
        END
    """, nativeQuery = true)
    int existsEmailGlobalInt(@Param("email") String email);

    default boolean existsEmailGlobal(String email) {
        return existsEmailGlobalInt(email) == 1;
    }
}