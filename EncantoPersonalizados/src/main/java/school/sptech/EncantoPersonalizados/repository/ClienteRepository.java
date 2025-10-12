package school.sptech.EncantoPersonalizados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.EncantoPersonalizados.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
