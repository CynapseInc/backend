package school.sptech.EncantoPersonalizados.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.EncantoPersonalizados.dto.LoginRequestDTO;
import school.sptech.EncantoPersonalizados.entities.Usuario;
import school.sptech.EncantoPersonalizados.service.UsuarioService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Integer id){
        Optional<Usuario> usuario = service.getById(id);
        if(usuario.isPresent()) {
            return ResponseEntity.status(200).body(usuario.get());
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> get(){
        List<Usuario> usuarios = service.get();
        if(!usuarios.isEmpty()){
            return ResponseEntity.status(200).body(usuarios);
        }
        return ResponseEntity.status(204).build();
    }

    @PostMapping
    public ResponseEntity<Void> store(@RequestBody Usuario usuario){
        Integer id = service.store(usuario);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Usuario usuario, @PathVariable Integer id){
        service.update(usuario, id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Integer id){
        service.destroy(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Integer id,
                                               @RequestBody Usuario usuario){
        service.updatePassword(usuario, id);
        return ResponseEntity.status(204).build();
    }

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request){
        boolean validator = service.validateLogin(request.getEmail(), request.getPassword());

        if(!validator){
            return ResponseEntity.status(404).body("Invalid username or password");
        }

        return ResponseEntity.status(200).body("Login com sucesso");
    }

}
