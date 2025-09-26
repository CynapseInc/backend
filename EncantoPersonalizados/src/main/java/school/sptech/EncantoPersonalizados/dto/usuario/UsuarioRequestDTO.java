package school.sptech.EncantoPersonalizados.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioRequestDTO(
         @Schema(description = "Nome do usuário")
         String name,
         @Schema(description = "Email do usuário")
         String email,
         @Schema(description = "Senha do usuário")
         String password,
         @Schema(description = "Base 64 da imagem")
         String foto,
         @Schema(description = "CPF do usuário")
         String cpf,
         @Schema(description = "Data de nascimento no formato yyyy-mm-dd")
         LocalDate dataNasc,
         @Schema(description = "Cargo do usuário")
         String cargo
) {
}
