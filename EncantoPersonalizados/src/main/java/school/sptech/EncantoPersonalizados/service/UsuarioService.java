package school.sptech.EncantoPersonalizados.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import school.sptech.EncantoPersonalizados.entities.Usuario;
import school.sptech.EncantoPersonalizados.repository.UsuarioRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository repository;



    public boolean validateLogin(String email, String password){

        Optional<Usuario> opt = repository.findUsuarioByEmail(email);

        if(opt.isEmpty()){
            return false;
        }

        Usuario user = opt.get();

        return user.getPassword().equals(password);

    }



    public void updatePassword(Usuario usuario, Integer id){
        Optional<Usuario> usuarioAtualizar = repository.findById(id);
        if(usuarioAtualizar.isPresent()) {
            var usuarioAtua = usuarioAtualizar.get();
            usuarioAtua.setPassword(usuario.getPassword());
            repository.save(usuarioAtua);
        }

    }

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void destroy(Integer id){
        var user = repository.findById(id);
        if(user.isPresent()){
            repository.deleteById(id);
        }

    }

    public List<Usuario> get(){
        return repository.findAll();
    }

    public Optional<Usuario> getById(Integer id){
        return repository.findById(id);
    }

    public Integer store(Usuario usuario){

        usuario.setCreatedAt(LocalDateTime.now());
        return repository.save(usuario).getId();
    }

    public void update(Usuario usuario, Integer id){
        Optional<Usuario> usuarioAtualizar = repository.findById(id);
        if(usuarioAtualizar.isPresent()){
            var usuarioAtua = usuarioAtualizar.get();
            usuarioAtua.setName(usuario.getName());
            usuarioAtua.setCargo(usuario.getCargo());
            usuarioAtua.setCpf(usuario.getCpf());
            usuarioAtua.setEmail(usuario.getEmail());
            usuarioAtua.setUpdatedAt(LocalDateTime.now());
            // implementar logica de upload de foto
            usuarioAtua.setFoto(usuario.getFoto());
            usuarioAtua.setDataNasc(usuario.getDataNasc());



            repository.save(usuarioAtua);
        }

    }


}
