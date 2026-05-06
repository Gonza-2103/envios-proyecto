package cl.sda1085.envios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EnvioResponseDTO {

    //DTO de salida (respuesta)
    //No existen las anotaciones de validación

    private Long id;
    private Long idSubasta;
    private String direccion;
    private String estadoEnvio;
    private String codigoSeguimiento;
}
