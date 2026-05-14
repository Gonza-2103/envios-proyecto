package cl.sda1085.envios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnvioNotFoundException extends RuntimeException {
    public EnvioNotFoundException(Long id) {
        super("No se encontró el envío con ID: " + id);
    }
}
