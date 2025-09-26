package school.sptech.EncantoPersonalizados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.EncantoPersonalizados.dto.UserMapper;
import school.sptech.EncantoPersonalizados.dto.UserTokenDTO;
import school.sptech.EncantoPersonalizados.entities.Usuario;
import school.sptech.EncantoPersonalizados.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public UserTokenDTO validateLogin(String email, String password) {

        Optional<Usuario> opt = repository.findUsuarioByEmail(email);

        Usuario user = opt.get();

        Usuario usuarioAutenticado = repository.findUsuarioByEmail(user.getEmail()).orElseThrow(
                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
        );

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UserMapper.of(usuarioAutenticado, token);

    }

//    public boolean validateLogin(String email, String password) {
//
//        Optional<Usuario> opt = repository.findUsuarioByEmail(email);
//
//        if (opt.isEmpty()) {
//            return false;
//        }
//
//        Usuario user = opt.get();
//
//        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
//
//        final Authentication authentication = this.authenticationManager.authenticate(credentials);
//
//        Usuario usuarioAutenticado = repository.findUsuarioByEmail(user.getEmail()).orElseThrow(
//                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
//        );
//
//        final String token = gerenciadorTokenJwt.generateToken(authentication);
//
//        return UserMapper;
//
//    }

    public void updatePassword(Usuario usuario, Integer id) {
        Optional<Usuario> usuarioAtualizar = repository.findById(id);
        if (usuarioAtualizar.isPresent()) {
            var usuarioAtua = usuarioAtualizar.get();
            usuarioAtua.setPassword(usuario.getPassword());
            repository.save(usuarioAtua);
        }

    }

    public void destroy(Integer id) {
        var user = repository.findById(id);
        if (user.isPresent()) {
            repository.deleteById(id);
        }
    }

    public List<Usuario> get() {
        return repository.findAll();
    }

    public Optional<Usuario> getById(Integer id) {
        return repository.findById(id);
    }

    public Integer store(Usuario usuario) {
        String encryptedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encryptedPassword);
        usuario.setCreatedAt(LocalDateTime.now());
        return repository.save(usuario).getId();
    }

    public void update(Usuario usuario, Integer id) {
        Optional<Usuario> usuarioAtualizar = repository.findById(id);
        if (usuarioAtualizar.isPresent()) {
            var usuarioAtual = usuarioAtualizar.get();
            usuarioAtual.setName(usuario.getName());
            usuarioAtual.setCargo(usuario.getCargo());
            usuarioAtual.setCpf(usuario.getCpf());
            usuarioAtual.setEmail(usuario.getEmail());
            usuarioAtual.setUpdatedAt(LocalDateTime.now());
            // implementar logica de upload de foto
            usuarioAtual.setFoto(usuario.getFoto());
            usuarioAtual.setDataNasc(usuario.getDataNasc());

            repository.save(usuarioAtual);
        }

    }

}
