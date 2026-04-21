package school.sptech.EncantoPersonalizados.infrastructure.persistence.repository.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.EncantoPersonalizados.core.domain.dashboard.DashboardPedidoSemAtualizacao;

import java.util.List;

public interface DashboardPedidoSemAtualizacaoRepository extends JpaRepository<DashboardPedidoSemAtualizacao, Long> {

    List<DashboardPedidoSemAtualizacao> findAll();
}
