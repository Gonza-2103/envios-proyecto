package cl.sda1085.envios.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder  //Permite construir el DTO de forma fluida en 'service'
public class EnvioResponseDTO {

    //DTO de salida (respuesta)
    //No existen las anotaciones de validación

    private Long id;
    private Long idSubasta;
    private String direccion;
    private String estadoEnvio;
    private String codigoSeguimiento;
}
