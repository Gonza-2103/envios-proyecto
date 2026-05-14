package cl.sda1085.envios.repository;

import cl.sda1085.envios.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

    //Buscar por el código único de seguimiento
    Optional<Envio> findByCodigoSeguimiento(String codigoSeguimiento);

    //Buscar el envío asociado a una subasta específica
    Optional<Envio> findByIdSubasta(Long idSubasta);

    //Verificar si una subasta ya tiene un envío generado
    boolean existsByIdSubasta(Long idSubasta);

    //Filtrar envíos por su estado
    List<Envio> findByEstadoEnvio(String estadoEnvio);

    //Buscar direcciones que contengan un texto
    List<Envio> findByDireccionContainingIgnoreCase(String direccion);

    //Contar cuántos envíos hay en un estado particular (Ej: "En Camino")
    long countByEstadoEnvio(String estadoEnvio);
}
