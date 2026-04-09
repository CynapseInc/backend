package school.sptech.EncantoPersonalizados.core.domain.dashboardGestao;

public record DashboardGestaoTempoEtapa(
        String etapa, // Ex: "Produção", "Qualidade", "Análise"
        Double dias   // Ex: 5.2, 1.5
) {}