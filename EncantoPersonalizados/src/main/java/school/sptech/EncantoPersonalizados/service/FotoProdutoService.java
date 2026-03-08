package school.sptech.EncantoPersonalizados.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.EncantoPersonalizados.clean.core.application.usecase.DeletarFotoUseCase;
import school.sptech.EncantoPersonalizados.clean.core.application.usecase.SalvarFotoUseCase;
import school.sptech.EncantoPersonalizados.entities.FotoProduto;
import school.sptech.EncantoPersonalizados.entities.Produto;
import school.sptech.EncantoPersonalizados.exceptions.ProdutoNaoEncontradoException;
import school.sptech.EncantoPersonalizados.repository.FotoProdutoRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class FotoProdutoService {

    private final FotoProdutoRepository repository;
    private final ProdutoService produtoService;
    private final SalvarFotoUseCase salvarFotoUseCase;
    private final DeletarFotoUseCase deletarFotoUseCase;

    public FotoProdutoService(
            FotoProdutoRepository repository,
            ProdutoService produtoService,
            SalvarFotoUseCase salvarFotoUseCase,
            DeletarFotoUseCase deletarFotoUseCase
    ) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.salvarFotoUseCase = salvarFotoUseCase;
        this.deletarFotoUseCase = deletarFotoUseCase;
    }

    public CompletableFuture<FotoProduto> store(Integer produtoId, MultipartFile file) throws IOException {
        Produto produto = produtoService.findById(produtoId);
        if (produto == null) throw new ProdutoNaoEncontradoException("Produto não encontrado");

        String nomeArquivo = UUID.randomUUID() + "-" + file.getOriginalFilename();
        byte[] conteudo = file.getBytes();

        return salvarFotoUseCase.executar(produtoId, nomeArquivo, conteudo)
                .thenApply(fotoArquivo -> {
                    FotoProduto fotoProduto = new FotoProduto();
                    fotoProduto.setFoto(fotoArquivo.getCaminhoRelativo());
                    fotoProduto.setProduto(produto);
                    fotoProduto.setCreatedAt(LocalDateTime.now());
                    return repository.save(fotoProduto);
                });
    }

    public CompletableFuture<Void> deletarFoto(Integer fotoId) {
        FotoProduto foto = repository.findById(fotoId)
                .orElseThrow(() -> new RuntimeException("Foto não encontrada"));

        return deletarFotoUseCase.executar(foto.getFoto())
                .thenRun(() -> repository.delete(foto));
    }
}
