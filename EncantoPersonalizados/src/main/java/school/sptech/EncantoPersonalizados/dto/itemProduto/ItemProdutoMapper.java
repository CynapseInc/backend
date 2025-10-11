package school.sptech.EncantoPersonalizados.dto.itemProduto;

import school.sptech.EncantoPersonalizados.entities.ItemProduto;
import school.sptech.EncantoPersonalizados.service.ItemProdutoService;

import java.util.List;

public class ItemProdutoMapper {
    public static ItemProduto toEntity(ItemProdutoRequestDTO dto){
        if (dto == null) return null;
        ItemProduto entity = new ItemProduto();
        entity.setDescricao(dto.descricao());
        entity.setPrecoVenda(dto.precoVenda());
        entity.setPrazoProducao(dto.prazoProducao());
        entity.setLargura(dto.largura());
        entity.setAltura(dto.altura());
        entity.setPeso(dto.peso());
        entity.setComprimento(dto.comprimento());
        entity.setMaterial(dto.material());
        entity.setCustoProducao(dto.custoProducao());
        entity.setPrecoPromocional(dto.precoPromocional());
        return entity;

    }

    public static ItemProdutoResponseDTO toDto(ItemProduto entity){
        if(entity == null) return null;
        ItemProdutoResponseDTO dto = new ItemProdutoResponseDTO(
                entity.getId(),
                entity.getDescricao(),
                entity.getPrecoVenda(),
                entity.getCustoProducao(),
                entity.getPrazoProducao(),
                entity.getLargura(),
                entity.getAltura(),
                entity.getPeso(),
                entity.getComprimento(),
                entity.getMaterial(),
                entity.getPrecoPromocional(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
        return dto;
    }
    public static List<ItemProdutoResponseDTO> toDto(List<ItemProduto> entities){
        if(entities == null) return null;

        return entities
                .stream()
                .map(entity -> {
                    ItemProdutoResponseDTO dto = new ItemProdutoResponseDTO(
                            entity.getId(),
                            entity.getDescricao(),
                            entity.getPrecoVenda(),
                            entity.getCustoProducao(),
                            entity.getPrazoProducao(),
                            entity.getLargura(),
                            entity.getAltura(),
                            entity.getPeso(),
                            entity.getComprimento(),
                            entity.getMaterial(),
                            entity.getPrecoPromocional(),
                            entity.getCreatedAt(),
                            entity.getUpdatedAt()
                    );
                    return dto;
                })
                .toList();
    }
}
