package school.sptech.EncantoPersonalizados.dto.produto;

import school.sptech.EncantoPersonalizados.dto.fotoProduto.FotoProdutoResponseDTO;
import school.sptech.EncantoPersonalizados.dto.itemProduto.ItemProdutoResponseDTO;
import school.sptech.EncantoPersonalizados.dto.temaProduto.TemaProdutoResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ProdutoResponseDTO(
        Integer id,
        String titulo,
        String descricao,
        List<FotoProdutoResponseDTO> fotos,
        TemaProdutoResponseDTO tema,
        ItemProdutoResponseDTO item,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {
}
