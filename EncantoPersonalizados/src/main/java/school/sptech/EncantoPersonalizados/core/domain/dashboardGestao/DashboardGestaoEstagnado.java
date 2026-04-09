package school.sptech.EncantoPersonalizados.core.domain.dashboardGestao;

public record DashboardGestaoEstagnado(
        Integer idPedido,
        String nomeCliente,
        String produto,
        String tema,
        String statusAtual,
        Integer diasParado,
        String responsavel
) {}