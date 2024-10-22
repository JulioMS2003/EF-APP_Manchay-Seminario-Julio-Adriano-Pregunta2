package cibertec.edu.pe.app_pregunta2.controller;

import cibertec.edu.pe.app_pregunta2.service.OperacionService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/operacion")
public class OperacionController {

    private final OperacionService operacionService;
    private static final Logger logger = LoggerFactory.getLogger(OperacionService.class);


    public OperacionController(OperacionService operacionService) {
        this.operacionService = operacionService;
    }

    @PostMapping("/setLibros")
    public ResponseEntity<String> setLibros(@RequestParam Integer libros) {
        operacionService.setLibros(libros); // Llama al servicio para establecer libros
        return ResponseEntity.status(HttpStatus.OK).body("Cantidad de libros establecida: " + libros);
    }

    @PostMapping("/comprar")
    @CircuitBreaker(name = "myService", fallbackMethod = "errorService")
    public ResponseEntity<String> comprarLibros(@RequestParam Integer cantidad) {
        operacionService.setCantidad(cantidad);

        String respuesta = operacionService.Compra();

        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }

    public ResponseEntity<String> errorService(Throwable throwable) {
        logger.info("Error detectado: ", throwable);
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Servicio Temporalmente no Disponible. Inténtalo de nuevo");
    }

}
