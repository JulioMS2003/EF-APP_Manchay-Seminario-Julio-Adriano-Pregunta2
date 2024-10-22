package cibertec.edu.pe.app_pregunta2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class OperacionService {


    private static final Logger logger = LoggerFactory.getLogger(OperacionService.class);

    private Integer libros = 0;
    private Integer cantidad = 0;

    public void setLibros(Integer libros) {
        if (libros < 0) {
            throw new IllegalArgumentException("La cantidad de libros no puede ser negativa.");
        }
        this.libros = libros;
        logger.info("Cantidad de libros establecida: {}", libros);
    }

    public void setCantidad(Integer cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad a comprar no puede ser negativa.");
        }
        this.cantidad = cantidad;
        logger.info("Cantidad a comprar establecida: {}", cantidad);
    }

    public String Compra() {
        if (cantidad > libros) {
            logger.error("Intento de compra fallido: cantidad solicitada {} es mayor que la cantidad disponible {}.", cantidad, libros);
            throw new RuntimeException("No hay esa cantidad de libros. " +
                    "La cantidad disponible es: " + libros);
        }

        libros -= cantidad;
        logger.info("Compra realizada: {} libros comprados. Libros restantes: {}", cantidad, libros);
        return "Libros comprados exitosamente: " + cantidad;
    }
}
