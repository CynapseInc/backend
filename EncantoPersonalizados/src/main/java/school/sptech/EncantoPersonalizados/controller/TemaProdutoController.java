package school.sptech.EncantoPersonalizados.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.EncantoPersonalizados.dto.temaProduto.TemaProdutoRequestDTO;
import school.sptech.EncantoPersonalizados.dto.temaProduto.TemaProdutoResponseDTO;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioResponseDTO;
import school.sptech.EncantoPersonalizados.service.TemaProdutoService;

import java.util.List;

@RestController
@RequestMapping("/temas")
public class TemaProdutoController {
    public final TemaProdutoService service;

    public TemaProdutoController(TemaProdutoService service) {
        this.service = service;
    }
    @Operation(description = "Listar todos os temas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de temas",
                    content = @Content(array = @ArraySchema(schema =  @Schema(implementation = UsuarioResponseDTO.class)))),
            @ApiResponse(responseCode = "204", description = "Não encontrou usuários")
    })
    @GetMapping
    public ResponseEntity<List<TemaProdutoResponseDTO>> get(){
        List<TemaProdutoResponseDTO> temas = service.get();
        if(temas.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(temas);
    }

    @Operation
    @ApiResponse(responseCode = "201", description = "Cria um tema",
            content = @Content(mediaType = "application/json"))
    @PostMapping
    public ResponseEntity<TemaProdutoResponseDTO> store(
            @RequestBody @Valid TemaProdutoRequestDTO dto
            )
    {
        TemaProdutoResponseDTO response = service.store(dto);
        return ResponseEntity.status(201).body(response);
    }
}
