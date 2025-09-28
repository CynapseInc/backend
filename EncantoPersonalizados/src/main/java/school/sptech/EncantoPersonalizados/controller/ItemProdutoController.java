package school.sptech.EncantoPersonalizados.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.EncantoPersonalizados.dto.itemProduto.ItemProdutoRequestDTO;
import school.sptech.EncantoPersonalizados.dto.itemProduto.ItemProdutoResponseDTO;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioResponseDTO;
import school.sptech.EncantoPersonalizados.entities.ItemProduto;
import school.sptech.EncantoPersonalizados.entities.Usuario;
import school.sptech.EncantoPersonalizados.service.ItemProdutoService;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemProdutoController {

    private final ItemProdutoService service;

    public ItemProdutoController(ItemProdutoService service) {
        this.service = service;
    }

    @Operation(description = "Cadastra um produto")
    @ApiResponses(
            @ApiResponse(responseCode = "201", description = "Retorna o produto")
    )

    @PostMapping
    public ResponseEntity<ItemProdutoResponseDTO> cadastrar(@RequestBody ItemProdutoRequestDTO item){
        ItemProdutoResponseDTO ProdutoRegistrado = service.cadastrar(item);
        return ResponseEntity.status(201).body(ProdutoRegistrado);
    }

    @Operation(description = "Lista todos os produtos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Retorna a lista de produtos vazia"),
            @ApiResponse(responseCode = "204", description = "Sucesso ao listar, sem conteúdo")
    })
    @GetMapping
    public ResponseEntity<List<ItemProdutoResponseDTO>> listar(){
        List<ItemProdutoResponseDTO> itens = service.listar();
        if(!itens.isEmpty()){
            return ResponseEntity.status(200).body(itens);
        }
        return ResponseEntity.status(204).build();
    }

    @Operation(description = "Lista produtos menor do que o valor especificado")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Retorna a lista de produtos vazia"),
            @ApiResponse(responseCode = "204", description = "Sucesso ao listar, sem conteúdo")
    })
    @GetMapping("/{preco}")
    public ResponseEntity<List<ItemProdutoResponseDTO>> listarPrecoMenorQue(@PathVariable Double preco){
        List<ItemProdutoResponseDTO> itensPrecoMenosQue = service.buscarPorPrecoMenorQue(preco);
        if(!itensPrecoMenosQue.isEmpty()){
            return ResponseEntity.status(200).body(itensPrecoMenosQue);
        }
        return ResponseEntity.status(204).build();
    }

    @Operation(description = "Atualiza produto")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "Retorna produto atualizado")
    )
    @PutMapping("/{id}")
    public ResponseEntity<ItemProdutoResponseDTO> updatePorId(
            @RequestBody ItemProduto item,

            @Parameter(description = "Id do produto", example = "1", required = true)
            @PathVariable Long id
    ){
        ItemProdutoResponseDTO itemAtualizado = service.update(id, item);
        return ResponseEntity.status(200).body(itemAtualizado);
     }

     @Operation(description = "Exclui produto")
     @ApiResponses(@ApiResponse(responseCode = "204", description = "Produto excluído, sem conteúdo"))
    @DeleteMapping("/{id}")
     public ResponseEntity<Void> excluirPorId(
            @Parameter(description = "Id do produto", example = "1", required = true)
            @PathVariable Long id
    ){
         service.delete(id);
         return ResponseEntity.status(204).build();
     }
}
