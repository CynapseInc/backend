package school.sptech.EncantoPersonalizados.core.application.gateway;

import school.sptech.EncantoPersonalizados.core.domain.dashboardGestao.DashboardGestaoEstagnado;
import school.sptech.EncantoPersonalizados.core.domain.dashboardGestao.DashboardGestaoKpi;
import school.sptech.EncantoPersonalizados.core.domain.dashboardGestao.DashboardGestaoMensal;
import school.sptech.EncantoPersonalizados.core.domain.dashboardGestao.DashboardGestaoProdutividade;
import school.sptech.EncantoPersonalizados.core.domain.dashboardGestao.DashboardGestaoTempoEtapa;

import java.time.LocalDate;
import java.util.List;

public interface DashboardGestaoGateway {

    DashboardGestaoKpi obterKpisGerais(LocalDate inicio, LocalDate fim, String tipo);

    List<DashboardGestaoMensal> obterDadosMensais(LocalDate inicio, LocalDate fim);

    List<DashboardGestaoTempoEtapa> obterTempoMedioPorEtapa(LocalDate inicio, LocalDate fim); // NOVO!

    List<DashboardGestaoProdutividade> obterProdutividadeEquipa(LocalDate inicio, LocalDate fim);

    List<DashboardGestaoEstagnado> obterPedidosEstagnados(Integer diasLimite);
}