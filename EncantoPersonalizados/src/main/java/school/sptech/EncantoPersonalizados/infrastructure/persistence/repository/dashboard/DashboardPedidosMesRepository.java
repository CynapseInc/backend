package school.sptech.EncantoPersonalizados.infrastructure.persistence.repository.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.EncantoPersonalizados.core.domain.dashboard.DashboardPedidosMes;

import java.util.List;

public interface DashboardPedidosMesRepository extends JpaRepository<DashboardPedidosMes, String> {

    @Query("SELECT d FROM DashboardPedidosMes d ORDER BY d.mes ASC")
    List<DashboardPedidosMes> findAllOrderByMesAsc();
}
