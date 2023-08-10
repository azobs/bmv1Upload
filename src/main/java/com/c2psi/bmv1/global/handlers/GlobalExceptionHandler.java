package com.c2psi.bmv1.global.handlers;

import com.c2psi.bmv1.global.dto.ErrorDto;
import com.c2psi.bmv1.global.exceptions.BMException;
import com.c2psi.bmv1.global.exceptions.DuplicateEntityException;
import com.c2psi.bmv1.global.exceptions.InvalidEntityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BMException.class)
    public ResponseEntity<?> handleException(BMException exception,
                                             WebRequest webRequest){
        log.info("A BMException is launch on the server side means it is not correspond to any specific exception " +
                "that our application can throw");
        final HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorDto errorDto =  ErrorDto.builder()
                .httpCode(badRequest.value())
                .exceptionMessage(exception.getMessage())
                .errorAppMessage(exception.getErrorCode())
                .errorList(exception.getErrors()!=null?(!exception.getErrors().isEmpty()?exception.getErrors().stream().toList():null):null)
                .build();
        Map<String, Object> map = new LinkedHashMap<>();
        map.clear();
        map.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        map.put("message", "Some problems occurs during execution: Internal Server Error");
        map.put("data", errorDto);
        map.put("cause", "Des exceptions ont ete lance pendant l'execution de la methode. consulter le errorDto " +
                "pour plus de details");
        return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<?> handleException(InvalidEntityException exception,
                                             WebRequest webRequest){
        log.info("An InvalidEntityException is thrown");
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto =  ErrorDto.builder()
                .httpCode(badRequest.value())
                .exceptionMessage(exception.getMessage())
                .errorAppMessage(exception.getErrorCode())
                .errorList(exception.getErrors()!=null?(!exception.getErrors().isEmpty()?exception.getErrors().stream().toList():null):null)
                .build();
        Map<String, Object> map = new LinkedHashMap<>();
        map.clear();
        map.put("status", HttpStatus.BAD_REQUEST);
        map.put("message", "Some values sent in the request are not valid.");
        map.put("data", errorDto);
        map.put("cause", "Certaines donnees ne sont pas valides dans la requetes envoyees");
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateEntityException.class)
    public ResponseEntity<?> handleException(DuplicateEntityException exception,
                                             WebRequest webRequest){
        log.info("An DuplicateEntityException is thrown");
        final HttpStatus badRequest = HttpStatus.CONFLICT;
        final ErrorDto errorDto =  ErrorDto.builder()
                .httpCode(badRequest.value())
                .exceptionMessage(exception.getMessage())
                .errorAppMessage(exception.getErrorCode())
                .errorList(exception.getErrors()!=null?(!exception.getErrors().isEmpty()?exception.getErrors().stream().toList():null):null)
                .build();
        Map<String, Object> map = new LinkedHashMap<>();
        map.clear();
        map.put("status", HttpStatus.CONFLICT);
        map.put("message", "Some unique keys in the entity is violated");
        map.put("data", errorDto);
        map.put("cause", "Certaines donnees unique de l'entite seront violees si cette requete est accepte");
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    }

}
