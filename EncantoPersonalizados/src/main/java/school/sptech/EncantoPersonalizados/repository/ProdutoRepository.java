package school.sptech.EncantoPersonalizados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.sptech.EncantoPersonalizados.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
