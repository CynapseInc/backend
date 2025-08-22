package school.sptech.EncantoPersonalizados.service;

import org.springframework.stereotype.Service;
import school.sptech.EncantoPersonalizados.entities.Usuario;
import school.sptech.EncantoPersonalizados.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public boolean validateLogin(String email, String password){

        Optional<Usuario> opt = repository.findUsuarioByEmail(email);

        if(opt.isEmpty()){
            return false;
        }

        Usuario user = opt.get();

        return user.getPassword().equals(password);

    }

}
