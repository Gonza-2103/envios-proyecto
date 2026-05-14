package cl.sda1085.envios.config;

import cl.sda1085.envios.model.Envio;
import cl.sda1085.envios.repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final EnvioRepository envioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (envioRepository.count() > 0) {
            log.info("Base de datos de envíos ya contiene datos. Omitiendo inicialización.");
            return;
        }
        log.info("Iniciando la creación de envíos de prueba...");

        // Envio 1: Vasija Precolombina (Subasta 1) - Ya entregado
        Envio e1 = new Envio(
                null,
                1L,
                "Av. Libertad 123, Viña del Mar",
                "ENTREGADO",
                generarCodigoSeguimiento()
        );

        // Envio 2: Espada Medieval (Subasta 2) - En camino
        Envio e2 = new Envio(
                null,
                2L,
                "Calle Los Leones 456, Providencia, Santiago",
                "EN_CAMINO",
                generarCodigoSeguimiento()
        );

        // Envio 3: Armadura Samurai (Subasta 14) - Recién pagada, pendiente de envío
        Envio e3 = new Envio(
                null,
                14L,
                "Av. Apoquindo 789, Las Condes, Santiago",
                "PENDIENTE",
                generarCodigoSeguimiento()
        );

        //Se generan sólo 3 envíos de prueba para mantener consistencia con DataInitializer de Pagos (de 5 intentos de pago: 3 fueron exitosos, 1 fue rechazado y 1 está pendiente, por lo que no generan despacho)
        envioRepository.saveAll(List.of(e1, e2, e3));

        log.info("✅ Se han registrado 3 envíos de prueba con códigos de seguimiento generados dinámicamente.");
    }

    //Generación codigo único de seguimiento
    private String generarCodigoSeguimiento() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}

