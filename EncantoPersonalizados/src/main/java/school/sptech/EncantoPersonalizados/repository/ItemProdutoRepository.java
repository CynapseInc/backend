package school.sptech.EncantoPersonalizados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.EncantoPersonalizados.entities.ItemProduto;

import java.util.List;
import java.util.Optional;

public interface ItemProdutoRepository extends JpaRepository<ItemProduto, Long> {
    Optional<List<ItemProduto>> findByPrecoLessThan(Double preco);
}
