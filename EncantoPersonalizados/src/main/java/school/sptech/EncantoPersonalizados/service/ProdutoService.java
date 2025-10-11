package school.sptech.EncantoPersonalizados.service;

import org.springframework.stereotype.Service;
import school.sptech.EncantoPersonalizados.dto.produto.ProdutoMapper;
import school.sptech.EncantoPersonalizados.dto.produto.ProdutoRequestDTO;
import school.sptech.EncantoPersonalizados.dto.produto.ProdutoResponseDTO;
import school.sptech.EncantoPersonalizados.entities.Produto;
import school.sptech.EncantoPersonalizados.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    public final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto findById(Integer id){
        Optional<Produto> optionalProduto = repository.findById(id);
        if(optionalProduto.isEmpty()) return null;
        return optionalProduto.get();
    }

    public List<ProdutoResponseDTO> get(){
        List<Produto> produtos = repository.findAll();
        return ProdutoMapper.toDto(produtos);
    }

    public Produto store(Produto entity){
        return repository.save(entity);
    }
}
