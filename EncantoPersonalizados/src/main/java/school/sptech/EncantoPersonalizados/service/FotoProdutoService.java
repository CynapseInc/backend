package school.sptech.EncantoPersonalizados.service;

import org.springframework.stereotype.Service;
import school.sptech.EncantoPersonalizados.dto.fotoProduto.FotoProdutoMapper;
import school.sptech.EncantoPersonalizados.dto.fotoProduto.FotoProdutoRequestDTO;
import school.sptech.EncantoPersonalizados.entities.FotoProduto;
import school.sptech.EncantoPersonalizados.entities.Produto;
import school.sptech.EncantoPersonalizados.repository.FotoProdutoRepository;

@Service
public class FotoProdutoService {

    private final FotoProdutoRepository repository;

    public FotoProdutoService(FotoProdutoRepository repository) {
        this.repository = repository;
    }

    public void store(FotoProdutoRequestDTO dto, Produto produto){
        FotoProduto entity = FotoProdutoMapper.toEntity(dto);
        entity.setProduto(produto);
        repository.save(entity);
    }
}
