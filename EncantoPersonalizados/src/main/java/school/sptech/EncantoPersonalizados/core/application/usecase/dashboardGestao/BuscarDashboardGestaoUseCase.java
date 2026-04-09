package school.sptech.EncantoPersonalizados.core.application.usecase.dashboardGestao;

import java.time.LocalDate;
import java.util.Map;

public interface BuscarDashboardGestaoUseCase {
    Map<String, Object> obterDashboard(LocalDate inicio, LocalDate fim, String tipoPedido);
}