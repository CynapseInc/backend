package school.sptech.EncantoPersonalizados.clean.core.application.usecase;

import org.springframework.stereotype.Component;
import school.sptech.EncantoPersonalizados.clean.core.application.gateway.FotoArquivoStorageGateway;
import school.sptech.EncantoPersonalizados.clean.core.domain.FotoArquivo;

import java.util.concurrent.CompletableFuture;

@Component
public class SalvarFotoUseCaseImpl implements SalvarFotoUseCase {

    private final FotoArquivoStorageGateway gateway;

    public SalvarFotoUseCaseImpl(FotoArquivoStorageGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public CompletableFuture<FotoArquivo> executar(Integer produtoId, String nomeArquivo, byte[] conteudo) {
        return gateway.salvar(produtoId, nomeArquivo, conteudo);
    }
}
