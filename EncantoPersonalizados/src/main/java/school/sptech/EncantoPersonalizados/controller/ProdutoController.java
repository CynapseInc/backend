package school.sptech.EncantoPersonalizados.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import school.sptech.EncantoPersonalizados.dto.produto.ProdutoRequestDTO;
import school.sptech.EncantoPersonalizados.dto.produto.ProdutoResponseDTO;
import school.sptech.EncantoPersonalizados.facade.ProdutoFacade;
import school.sptech.EncantoPersonalizados.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoFacade facade;
    private final ProdutoService service;

    public ProdutoController(ProdutoFacade facade, ProdutoService service) {
        this.facade = facade;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(
            @RequestBody ProdutoRequestDTO dto
            ){
        ProdutoResponseDTO response = facade.store(dto);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listar(){
        List<ProdutoResponseDTO> produtos = service.get();
        if(produtos.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(produtos);
    }
}
