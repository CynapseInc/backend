package school.sptech.EncantoPersonalizados.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class LoginRequestDTO {

    @Schema(description = "Email do usuário", example = "email@.com")
    private String email;
    @Schema(description = "Senha do usuário", example = "111111@A")
    private String password;

    public LoginRequestDTO() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
