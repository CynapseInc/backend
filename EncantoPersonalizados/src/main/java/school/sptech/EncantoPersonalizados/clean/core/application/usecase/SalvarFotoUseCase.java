package school.sptech.EncantoPersonalizados.clean.core.application.usecase;

import school.sptech.EncantoPersonalizados.clean.core.domain.FotoArquivo;

import java.util.concurrent.CompletableFuture;

public interface SalvarFotoUseCase {

    CompletableFuture<FotoArquivo> executar(Integer produtoId, String nomeArquivo, byte[] conteudo);
}
