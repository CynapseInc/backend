package school.sptech.EncantoPersonalizados.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemProdutoNaoEncontradoException extends RuntimeException{
    public ItemProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
