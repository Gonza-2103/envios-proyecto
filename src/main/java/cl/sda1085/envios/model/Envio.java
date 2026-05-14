package cl.sda1085.envios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_subasta", nullable = false)
    private Long idSubasta;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(name = "estado_envio", nullable = false, length = 20)  //Pendiente, en camino, entregado y devuelto
    private String estadoEnvio;

    @Column(name = "codigo_seguimiento", unique = true, length = 50)  //Generado para que el cliente rastree su obra de arte
    private String codigoSeguimiento;
}
