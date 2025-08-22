package school.sptech.EncantoPersonalizados.controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.EncantoPersonalizados.service.UsuarioService;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public boolean login(@RequestParam String email, @RequestParam String password){
        return service.validateLogin(email, password);
    }

}
