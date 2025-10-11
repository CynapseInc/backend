package school.sptech.EncantoPersonalizados.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioMapper;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioRequestDTO;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioResponseDTO;
import school.sptech.EncantoPersonalizados.entities.Usuario;
import school.sptech.EncantoPersonalizados.service.UsuarioService;

import java.util.List;


    @RestController
    @RequestMapping("/usuarios")
    public class UsuarioController {

        private final UsuarioService service;
        private final UsuarioService usuarioService;

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
        @SecurityRequirement(name = "Bearer")
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
        @SecurityRequirement(name = "Bearer")
        public ResponseEntity<UsuarioResponseDTO> store(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO){

            Usuario usuario = UsuarioMapper.toEntity(usuarioRequestDTO);

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

        public UsuarioController(UsuarioService service, UsuarioService usuarioService) {
            this.service = service;
            this.usuarioService = usuarioService;
        }

        @GetMapping("/ordenadosPorNome")
        public ResponseEntity<List<Usuario>> getUsuariosOrdenados() {
            List<Usuario> usuariosOrdenados = usuarioService.getUsuariosComMergeSortPorNome();

            if (usuariosOrdenados.isEmpty()) {
                return ResponseEntity.status(204).build();
            }

            return ResponseEntity.status(200).body(usuariosOrdenados);
        }
    }

