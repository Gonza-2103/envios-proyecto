package cl.sda1085.envios.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 255, message = "La dirección no puede superar los 255 caracteres.")
    private String direccion;

    @NotBlank(message = "El estado del envío no puede estar vacío.")
    @Size(max = 20, message = "El estado no puede superar los 20 caracteres.")
    private String estadoEnvio;

    //Solo en el caso si se quiere enviar manualmente
    @Size(max = 50, message = "El código de seguimiento no puede superar los 50 caracteres.")
    private String codigoSeguimiento;
}
