package school.sptech.EncantoPersonalizados.clean.core.application.gateway;

import school.sptech.EncantoPersonalizados.clean.core.domain.FotoArquivo;

import java.util.concurrent.CompletableFuture;

public interface FotoArquivoStorageGateway {

    CompletableFuture<FotoArquivo> salvar(Integer produtoId, String nomeArquivo, byte[] conteudo);

    CompletableFuture<Void> deletar(String caminhoRelativo);
}
