package school.sptech.EncantoPersonalizados.core.domain.dashboardGestao;

public record DashboardGestaoMensal(
        String mes,
        Long quantidadeCriados,
        Long quantidadeEntregues,
        Double tempoMedioEntregaDias,
        Long quantidadeRetrabalho
) {}