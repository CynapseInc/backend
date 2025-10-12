package school.sptech.EncantoPersonalizados.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.sptech.EncantoPersonalizados.dto.cliente.ClienteMapper;
import school.sptech.EncantoPersonalizados.dto.cliente.CreateClienteDTO;
import school.sptech.EncantoPersonalizados.dto.cliente.ResponseClienteDTO;
import school.sptech.EncantoPersonalizados.entities.Cliente;
import school.sptech.EncantoPersonalizados.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

 public ResponseClienteDTO store(CreateClienteDTO dto){
        Cliente entity = repository.save(ClienteMapper.toEntity(dto));
        return ClienteMapper.toDto(entity);
    }

    public List<ResponseClienteDTO> getAll(){

        List<Cliente> clientes = repository.findAll();
        return ClienteMapper.toDto(clientes);


    }

}
