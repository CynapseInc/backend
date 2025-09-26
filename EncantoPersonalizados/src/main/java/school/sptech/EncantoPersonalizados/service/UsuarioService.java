package school.sptech.EncantoPersonalizados.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioMapper;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioRequestDTO;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioResponseDTO;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.EncantoPersonalizados.dto.UserMapper;
import school.sptech.EncantoPersonalizados.dto.UserTokenDTO;
import school.sptech.EncantoPersonalizados.entities.Usuario;
import school.sptech.EncantoPersonalizados.repository.UsuarioRepository;

import javax.swing.text.html.Option;
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


    public boolean updatePassword(Usuario usuario, Integer id) {
        Optional<Usuario> usuarioAtualizar = repository.findById(id);
        if (usuarioAtualizar.isPresent()) {
            var usuarioAtua = usuarioAtualizar.get();
            usuarioAtua.setPassword(usuario.getPassword());
            repository.save(usuarioAtua);
            return true;
        }
        return false;

    }

    public boolean destroy(Integer id) {
        var user = repository.findById(id);
        if (user.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<UsuarioResponseDTO> get() {
         List<Usuario> entities = repository.findAll();
         List<UsuarioResponseDTO> dtos = UsuarioMapper.toResponseDTO(entities);
         return dtos;
    }

    public UsuarioResponseDTO getById(Integer id) {
        Optional<Usuario> entity = repository.findById(id);
        if (entity.isEmpty()) return null;
        return UsuarioMapper.toResponseDTO(entity.get());

    }

    public UsuarioResponseDTO store(Usuario usuario) {
        String encryptedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encryptedPassword);
        usuario.setCreatedAt(LocalDateTime.now());

        Usuario newEntity = repository.save(entity);
        return UsuarioMapper.toResponseDTO(newEntity);
    }

    public UsuarioResponseDTO update(Usuario usuario, Integer id) {
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

            Usuario entity = repository.save(usuarioAtual);
            return UsuarioMapper.toResponseDTO(entity);

        }
        return null;


    }

}
