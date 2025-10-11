package school.sptech.EncantoPersonalizados.dto.temaProduto;

import java.time.LocalDateTime;

public record TemaProdutoResponseDTO(
        Integer id,
        String descricao,
        LocalDateTime created_at,
        LocalDateTime updated_at
) {
}
