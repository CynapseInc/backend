package school.sptech.EncantoPersonalizados.dto.cliente;

import school.sptech.EncantoPersonalizados.entities.Cliente;

import java.time.LocalDateTime;
import java.util.List;

public class ClienteMapper {

    public static Cliente toEntity(CreateClienteDTO dto){
        if (dto == null)return null;

        Cliente entity = new Cliente();
        entity.setNome(dto.nome());
        entity.setTelefone(dto.telefone());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        return entity;


    }

    public static ResponseClienteDTO toDto(Cliente entity){

        if (entity == null) return null;

        ResponseClienteDTO dto = new ResponseClienteDTO(
                entity.getId(),
                entity.getNome(),
                entity.getTelefone(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
                );


        return dto;
    }

    public static List<ResponseClienteDTO> toDto(List<Cliente> entity){

        if (entity == null) return null;

     return entity.stream()
                .map(e -> {

                    ResponseClienteDTO dto = new ResponseClienteDTO(
                            e.getId(),
                            e.getNome(),
                            e.getTelefone(),
                            e.getCreatedAt(),
                            e.getUpdatedAt());
                    return dto;
                })

                .toList();


    }

}
