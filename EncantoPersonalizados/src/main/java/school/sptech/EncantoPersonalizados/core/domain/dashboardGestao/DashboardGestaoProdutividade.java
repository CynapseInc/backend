package school.sptech.EncantoPersonalizados.core.domain.dashboardGestao;

public record DashboardGestaoProdutividade(
        String nomeFuncionario,
        Long pedidosConcluidos,
        Long pedidosEmAndamento
) {}