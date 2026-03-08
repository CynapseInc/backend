package school.sptech.EncantoPersonalizados.clean.core.application.usecase;

import java.util.concurrent.CompletableFuture;

public interface DeletarFotoUseCase {

    CompletableFuture<Void> executar(String caminhoRelativo);
}
