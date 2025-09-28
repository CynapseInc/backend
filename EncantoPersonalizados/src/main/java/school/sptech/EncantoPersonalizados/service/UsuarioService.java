package school.sptech.EncantoPersonalizados.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import school.sptech.EncantoPersonalizados.config.GerenciadorTokenJwt;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioMapper;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioRequestDTO;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioResponseDTO;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.EncantoPersonalizados.dto.usuario.UserTokenDTO;
import school.sptech.EncantoPersonalizados.entities.Usuario;
import school.sptech.EncantoPersonalizados.repository.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository repository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public UsuarioService(
            PasswordEncoder passwordEncoder,
            UsuarioRepository repository,
            GerenciadorTokenJwt gerenciadorTokenJwt,
            AuthenticationManager authenticationManager
    ){
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.authenticationManager = authenticationManager;
    }

    public UserTokenDTO validateLogin(String email, String password) {

        Usuario usuario = repository.findUsuarioByEmail(email).orElseThrow(()-> new ResponseStatusException(404, "Email do usuário não cadastrado", null));

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(email, password);
        final Authentication authentication = this.authenticationManager.authenticate(credentials);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuario, token);
    }


    public boolean updatePassword(Usuario usuario, Integer id) {
        Optional<Usuario> usuarioAtualizar = repository.findById(id);
        if (usuarioAtualizar.isPresent()) {
            var usuarioAtual = usuarioAtualizar.get();
            String encryptedPassword = passwordEncoder.encode(usuario.getPassword());
            usuarioAtual.setPassword(encryptedPassword);
            repository.save(usuarioAtual);
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

        Usuario newEntity = repository.save(usuario);
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
