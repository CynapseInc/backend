package school.sptech.EncantoPersonalizados.service;

import org.springframework.stereotype.Service;
import school.sptech.EncantoPersonalizados.entities.ItemProduto;
import school.sptech.EncantoPersonalizados.exceptions.EntidadeConflitoException;
import school.sptech.EncantoPersonalizados.exceptions.EntidadeNaoEncontradaException;
import school.sptech.EncantoPersonalizados.repository.ItemProdutoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemProdutoService {

    private final ItemProdutoRepository repository;

    public ItemProdutoService (ItemProdutoRepository repository){
        this.repository = repository;
    }

    public ItemProduto cadastrar(ItemProduto itemParaCadastro){
        itemParaCadastro.setCreatedAt(LocalDateTime.now());
        ItemProduto itemRegistrado = repository.save(itemParaCadastro);
        return itemRegistrado;
    }

    public List<ItemProduto> listar(){
        return repository.findAll();
    }

    public List<ItemProduto> buscarPorPrecoMenorQue(Double preco){
        List<ItemProduto> itensEncontrados = repository.findByPrecoVendaLessThan(preco).get();
        return itensEncontrados;
    }

    public ItemProduto update(Long id, ItemProduto itemAtualizado){
        Optional<ItemProduto> itemOpt = repository.findById(id);
        if (!itemOpt.isPresent()) {
            throw new EntidadeNaoEncontradaException("Item não encontrado!");
        }
        var itemAtual = itemOpt.get();
        itemAtual.setDescricao(itemAtualizado.getDescricao());
        itemAtual.setPrecoVenda(itemAtualizado.getPrecoVenda());
        itemAtual.setCustoProducao(itemAtualizado.getCustoProducao());
        itemAtual.setPrazoProducao(itemAtualizado.getPrazoProducao());
        itemAtual.setLargura(itemAtualizado.getLargura());
        itemAtual.setPeso(itemAtualizado.getPeso());
        itemAtual.setComprimento(itemAtualizado.getComprimento());
        itemAtual.setMaterial(itemAtualizado.getMaterial());
        itemAtual.setUpdatedAt(LocalDateTime.now());
        repository.save(itemAtual);
        return itemAtual;
    }

    public void delete(Long id){
        Optional<ItemProduto> itemOpt = repository.findById(id);
        if (!itemOpt.isPresent()) {
            throw new EntidadeNaoEncontradaException("Item não encontrado!");
        }
        repository.deleteById(id);
    }

}
