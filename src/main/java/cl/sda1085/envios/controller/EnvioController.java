package cl.sda1085.envios.controller;

import cl.sda1085.envios.dto.EnvioRequestDTO;
import cl.sda1085.envios.dto.EnvioResponseDTO;
import cl.sda1085.envios.service.EnvioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/envios")

public class EnvioController {

    private final EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<EnvioResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(envioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO> obtenerPorId(@PathVariable Long id) {
        return envioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EnvioResponseDTO> guardar(@Valid @RequestBody EnvioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(envioService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnvioResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EnvioRequestDTO dto) {
        return envioService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        envioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    //endpoints personalizados
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<EnvioResponseDTO> buscarPorCodigo(@PathVariable String codigo) {
        return envioService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/subasta/{idSubasta}")
    public ResponseEntity<EnvioResponseDTO> buscarPorSubasta(@PathVariable Long idSubasta) {
        return envioService.buscarPorSubasta(idSubasta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<EnvioResponseDTO>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(envioService.buscarPorEstado(estado));
    }


}
