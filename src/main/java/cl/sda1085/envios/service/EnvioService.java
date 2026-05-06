package cl.sda1085.envios.service;

import cl.sda1085.envios.dto.EnvioRequestDTO;
import cl.sda1085.envios.dto.EnvioResponseDTO;
import cl.sda1085.envios.model.Envio;
import cl.sda1085.envios.repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnvioService {

    private final EnvioRepository envioRepository;

    //Método de apoyo para encapsulamiento de datos
    private EnvioResponseDTO mapToResponseDTO(Envio envio){
        return new EnvioResponseDTO(
                envio.getId(),
                envio.getIdSubasta(),
                envio.getDireccion(),
                envio.getEstadoEnvio(),
                envio.getCodigoSeguimiento()
        );
    }

    //Método auxiliar de conversión (reutilizable)
    private EnvioResponseDTO convertirADTO(Envio envio){
        return new EnvioResponseDTO(
                envio.getId(),
                envio.getIdSubasta(),
                envio.getDireccion(),
                envio.getEstadoEnvio(),
                envio.getCodigoSeguimiento()
        );
    }

    //Obtener todos los envíos
    public List<EnvioResponseDTO> obtenerTodos(){
        return envioRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    //Obtener envío por ID
    public Optional<EnvioResponseDTO> obtenerPorId(Long id){
        return envioRepository.findById(id).map(this::mapToResponseDTO);
    }

    //Crear (guardar) envío
    public EnvioResponseDTO guardar(EnvioRequestDTO dto){
        if (envioRepository.existsByIdSubasta(dto.getIdSubasta())){
            throw new RuntimeException("Ya existen un envío programado para esta subasta.");
        }

        Envio envio = new Envio();
        envio.setIdSubasta(dto.getIdSubasta());
        envio.setDireccion(dto.getDireccion());
        envio.setEstadoEnvio("PENDIENTE"); //Estado inicial por defecto

        //Generación automática del código de seguimiento (8 caracteres únicos)
        String codigoGenerado = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        envio.setCodigoSeguimiento(codigoGenerado);

        //Guardar en la base de datos
        Envio envioGuardado = envioRepository.save(envio);

        //Devolver la respuesta como DTO
        return convertirADTO(envioGuardado);
    }

    //Actualizar envío
    public Optional<EnvioResponseDTO> actualizar(Long id, EnvioRequestDTO dto){
        return envioRepository.findById(id).map(envioExistente -> {
           envioExistente.setDireccion(dto.getDireccion());
           envioExistente.setEstadoEnvio(dto.getEstadoEnvio());
           return mapToResponseDTO(envioRepository.save(envioExistente));
        });
    }

    //Eliminar envío
    public void eliminar(Long id){
        envioRepository.deleteById(id);
    }


    //CRUD personalizado

    //Buscar por código de seguimiento
    public Optional<EnvioResponseDTO> buscarPorCodigo(String codigo){
        return envioRepository.findByCodigoSeguimiento(codigo)
                .map(this::mapToResponseDTO);
    }

    //Buscar envío de una subasta específica
    public Optional<EnvioResponseDTO> buscarPorSubasta(Long idSubasta){
        return envioRepository.findByIdSubasta(idSubasta)
                .map(this::mapToResponseDTO);
    }

    //Verificar si existe el envío
    public boolean existeEnvioParaSubasta(Long idSubasta){
        return envioRepository.existsByIdSubasta(idSubasta);
    }

    //Filtrar por estado
    public List<EnvioResponseDTO> buscarPorEstado(String estado){
        return envioRepository.findByEstadoEnvio(estado).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    //Búsqueda flexible por dirección
    public List<EnvioResponseDTO> buscarPorDireccion(String direccion){
        return envioRepository.findByDireccionContainingIgnoreCase(direccion)
                .stream().map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
}
