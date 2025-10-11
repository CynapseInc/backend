package school.sptech.EncantoPersonalizados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.EncantoPersonalizados.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
