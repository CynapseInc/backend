package school.sptech.EncantoPersonalizados.core.domain.dashboardGestao;

public record DashboardGestaoKpi(
        Long totalPedidos,
        Long pedidosEntregues,
        Long pedidosAtrasados,
        Long pedidosSemAtualizacao,
        Long pedidosEmRetrabalho,
        Double tempoMedioEntregaDias
) {}