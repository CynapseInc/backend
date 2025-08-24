package school.sptech.EncantoPersonalizados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.EncantoPersonalizados.entities.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findUsuarioByEmail(String email);

}
