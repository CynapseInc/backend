package school.sptech.EncantoPersonalizados.controller;

import org.apache.coyote.Response;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public ResponseEntity<ItemProduto> cadastrar(@RequestBody ItemProduto item){
        ItemProduto ProdutoRegistrado = service.cadastrar(item);
        return ResponseEntity.status(201).body(ProdutoRegistrado);
    }

    @GetMapping
    public ResponseEntity<List<ItemProduto>> listar(){
        List<ItemProduto> itens = service.listar();
        if(!itens.isEmpty()){
            return ResponseEntity.status(200).body(itens);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{preco}")
    public ResponseEntity<List<ItemProduto>> listarPrecoMenorQue(@PathVariable Double preco){
        List<ItemProduto> itensPrecoMenosQue = service.buscarPorPrecoMenorQue(preco);
        if(!itensPrecoMenosQue.isEmpty()){
            return ResponseEntity.status(200).body(itensPrecoMenosQue);
        }
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemProduto> updatePorId(@RequestBody ItemProduto item, @PathVariable Long id){
        ItemProduto itemAtualizado = service.update(id, item);
        return ResponseEntity.status(200).body(itemAtualizado);
     }

    @DeleteMapping("/{id}")
     public ResponseEntity<Void> excluirPorId(@PathVariable Long id){
         service.delete(id);
         return ResponseEntity.status(204).build();
     }
}
