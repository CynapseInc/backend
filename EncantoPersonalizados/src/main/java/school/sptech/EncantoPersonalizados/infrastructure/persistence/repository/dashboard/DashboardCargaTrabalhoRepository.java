package school.sptech.EncantoPersonalizados.infrastructure.persistence.repository.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.sptech.EncantoPersonalizados.core.domain.dashboard.DashboardCargaTrabalho;

import java.util.List;

public interface DashboardCargaTrabalhoRepository extends JpaRepository<DashboardCargaTrabalho, String> {

    @Query("SELECT d FROM DashboardCargaTrabalho d ORDER BY d.emAndamento DESC")
    List<DashboardCargaTrabalho> findAllOrderByEmAndamentoDesc();
}
