package school.sptech.EncantoPersonalizados.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.EncantoPersonalizados.dto.usuario.LoginRequestDTO;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioRequestDTO;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioResponseDTO;
import school.sptech.EncantoPersonalizados.entities.Usuario;
import school.sptech.EncantoPersonalizados.service.UsuarioService;


import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @Operation(description = "Lista usuário por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Retorna o usuário",
        content = @Content(schema = @Schema(implementation = UsuarioResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Não encontra o usuário")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(
            @Parameter(description = "Id para encontrar o usuário", example = "1")
            @PathVariable Integer id
    ){
        UsuarioResponseDTO dto = service.getById(id);
        if(dto == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(dto);
    }

    @Operation(description = "Listar todos os usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna uma lista de usuários",
            content = @Content(array = @ArraySchema(schema =  @Schema(implementation = UsuarioResponseDTO.class)))),
            @ApiResponse(responseCode = "204", description = "Não encontrou usuários")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> get(){
        List<UsuarioResponseDTO> usuarios = service.get();
        if(!usuarios.isEmpty()){
            return ResponseEntity.status(200).body(usuarios);
        }
        return ResponseEntity.status(204).build();
    }


    @Operation
    @ApiResponse(responseCode = "201", description = "Cria um usuário",
    content = @Content(mediaType = "application/json"))
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> store(@RequestBody UsuarioRequestDTO usuario){

        UsuarioResponseDTO responseDto = service.store(usuario);
        return ResponseEntity.status(201).body(responseDto);
    }

    @Operation(description = "Atualizar usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso ao atualizar",
                    content =  @Content(schema = @Schema(implementation = UsuarioResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Não encontra o usuário")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(

            @RequestBody Usuario usuario,
            @Parameter(description = "Id do usuário", example = "1")
            @PathVariable Integer id
    ){
        UsuarioResponseDTO dto = service.update(usuario, id);
        if(dto == null) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(dto);
    }
    @Operation(description = "Atualizar usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso ao deletar"),
            @ApiResponse(responseCode = "404", description = "Não encontra o usuário")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(
            @Parameter(description = "Id do usuário", example = "1")
            @PathVariable Integer id
    ){
        if(service.destroy(id)){
            return ResponseEntity.status(204).build();
        };
        return ResponseEntity.status(404).build();
    }
    @Operation(description = "Atualizar senha do usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Sucesso ao atualizar"),
            @ApiResponse(responseCode = "404", description = "Não encontra o usuário")
    })
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Integer id,
                                               @RequestBody Usuario usuario){
        if(service.updatePassword(usuario, id)){
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(404).build();
    }

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Operation(description = "Autenticar usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucesso ao autenticar"),
            @ApiResponse(responseCode = "404", description = "Falha ao autenticar")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request){
        boolean validator = service.validateLogin(request.getEmail(), request.getPassword());

        if(!validator){
            return ResponseEntity.status(404).body("Invalid username or password");
        }

        return ResponseEntity.status(200).body("Login com sucesso");
    }

}
