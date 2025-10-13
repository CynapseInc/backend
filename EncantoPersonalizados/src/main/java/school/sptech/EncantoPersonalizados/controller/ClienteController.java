package school.sptech.EncantoPersonalizados.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.EncantoPersonalizados.dto.cliente.CreateClienteDTO;
import school.sptech.EncantoPersonalizados.dto.cliente.ResponseClienteDTO;
import school.sptech.EncantoPersonalizados.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(description = "Cria um cliente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso ao criar cliente"),
            @ApiResponse(responseCode = "204", description = "Lista de clientes vazia")
    })
    @PostMapping
    public final ResponseEntity<ResponseClienteDTO> criar (@RequestBody CreateClienteDTO dto){

        if(dto == null) return ResponseEntity.status(204).build();

        ResponseClienteDTO response = clienteService.store(dto);
        return ResponseEntity.status(200).body(response);


    }

    @Operation(description = "Lista todos os clientes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna lista de clientes"),
            @ApiResponse(responseCode = "204", description = "Lista de clientes vazia")
    })
    @GetMapping()
    public final ResponseEntity<List<ResponseClienteDTO>> listar(){
        
        List<ResponseClienteDTO> lista = clienteService.getAll();
        if (lista.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(201).body(lista);
        
    }

    @Operation(description = "Exclui um cliente por id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Cliente excluido com sucesso")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id) {
        clienteService.removerPorId(id);
        return ResponseEntity.status(204).build();
    }
}
