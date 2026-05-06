package cl.sda1085.envios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioRequestDTO {

    @NotNull(message = "El ID de la subasta es obligatorio.")
    private Long idSubasta;

    @NotBlank(message = "La direccion de envío no puede estar vacía.")
    private String direccion;

    @NotBlank(message = "El estado del envío no puede estar vacío.")
    private String estadoEnvio;
}
