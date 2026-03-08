package school.sptech.EncantoPersonalizados.clean.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import school.sptech.EncantoPersonalizados.dto.fotoProduto.FotoProdutoMapper;
import school.sptech.EncantoPersonalizados.dto.fotoProduto.FotoProdutoResponseDTO;
import school.sptech.EncantoPersonalizados.service.FotoProdutoService;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/produtos")
public class FotoProdutoController {

    private final FotoProdutoService fotoProdutoService;

    public FotoProdutoController(FotoProdutoService fotoProdutoService) {
        this.fotoProdutoService = fotoProdutoService;
    }

    @PostMapping("/{id}/fotos")
    public CompletableFuture<ResponseEntity<FotoProdutoResponseDTO>> adicionarFoto(
            @PathVariable Integer id,
            @RequestParam MultipartFile file) throws IOException {
        return fotoProdutoService.store(id, file)
                .thenApply(foto -> ResponseEntity.status(201).body(FotoProdutoMapper.toDto(foto)));
    }

    @DeleteMapping("/{id}/fotos/{fotoId}")
    public CompletableFuture<ResponseEntity<Void>> removerFoto(
            @PathVariable Integer id,
            @PathVariable Integer fotoId) {
        return fotoProdutoService.deletarFoto(fotoId)
                .thenApply(v -> ResponseEntity.<Void>noContent().build());
    }
}
