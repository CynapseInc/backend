package school.sptech.EncantoPersonalizados.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.EncantoPersonalizados.config.GerenciadorTokenJwt;
import school.sptech.EncantoPersonalizados.dto.usuario.UserTokenDTO;
import school.sptech.EncantoPersonalizados.dto.usuario.UsuarioMapper;
import school.sptech.EncantoPersonalizados.entities.Usuario;
import school.sptech.EncantoPersonalizados.repository.UsuarioRepository;

import java.util.Optional;

@Service
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository repository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public LoginService(PasswordEncoder passwordEncoder, UsuarioRepository repository, GerenciadorTokenJwt gerenciadorTokenJwt, AuthenticationManager authenticationManager) {
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
}
