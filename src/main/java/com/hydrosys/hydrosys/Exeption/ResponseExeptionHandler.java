package com.hydrosys.hydrosys.Exeption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestControllerAdvice
public class ResponseExeptionHandler extends ResponseEntityExceptionHandler {

    // Logger para registrar excepciones
    private static final Logger logger = LoggerFactory.getLogger(ResponseExeptionHandler.class);

    /**
     * Maneja excepciones de tipo ResourceNotFoundException y devuelve una respuesta personalizada.
     *
     * @param ex la excepción capturada
     * @return ResponseEntity con detalles del error
     */
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ResponseExeption> handleRuntimeException(RuntimeException ex) {
        ResponseExeption response = new ResponseExeption(
                new Date(),
                "Error en la operación",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    /**
     * Maneja excepciones generales y devuelve una respuesta personalizada.
     *
     * @param ex la excepción capturada
     * @return ResponseEntity con detalles del error
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseExeption> handleException(Exception ex) {
        ResponseExeption responseExeption = new ResponseExeption(
                new Date(),
                "Ocurrió un error inesperado",
                ex.getMessage()
        );
        return new ResponseEntity<>(responseExeption, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Maneja excepciones de validación de argumentos y devuelve una respuesta personalizada.
     *
     * @param ex la excepción de validación
     * @param headers los encabezados HTTP
     * @param status el estado HTTP
     * @param request la solicitud web
     * @return ResponseEntity con detalles de los errores de validación
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        ResponseExeption responseExeption = new ResponseExeption(
                new Date(),
                "Error en la validación de los campos",
                "Hay errores en el formulario",
                errors
        );

        return new ResponseEntity<>(responseExeption, HttpStatus.BAD_REQUEST);
    }
}
