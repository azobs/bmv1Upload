package com.c2psi.bmv1.bmapp.handlers;

import com.c2psi.bmv1.bmapp.dto.ErrorDto;
import com.c2psi.bmv1.bmapp.exceptions.*;
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
        log.info("An InvalidEntityException is thrown "+exception.getMessage());
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

    @ExceptionHandler(InvalidColumnNameException.class)
    public ResponseEntity<?> handleException(InvalidColumnNameException exception,
                                             WebRequest webRequest){
        log.info("An InvalidColumnNameException is thrown "+exception.getMessage());
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
        map.put("message", "Some columns names sent in the request are not valid.");
        map.put("data", errorDto);
        map.put("cause", "Certains noms de colonne envoye pour affiner le filtre ne correspondent pas a des noms des champs " +
                "accessible via l'objet sur lequel le filtre est applique");
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFilterOperatorException.class)
    public ResponseEntity<?> handleException(InvalidFilterOperatorException exception,
                                             WebRequest webRequest){
        log.info("An InvalidFilterOperatorException is thrown "+exception.getMessage());
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
        map.put("message", "Some operators sent in the request are not valid.");
        map.put("data", errorDto);
        map.put("cause", "Certains operateurs envoyes permettant de relier les filtres ne sont pas valides ou alors " +
                "ne sont pas encore pris en compte par le systeme");
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidSortDirectionException.class)
    public ResponseEntity<?> handleException(InvalidSortDirectionException exception,
                                             WebRequest webRequest){
        log.info("An InvalidSortDirectionException is thrown "+exception.getMessage());
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
        map.put("message", "Some sort direction sent in the request are not valid.");
        map.put("data", errorDto);
        map.put("cause", "Une des direction de tri envoye n'est pas valide. La direction doit etre ASC ou DESC");
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullValueException.class)
    public ResponseEntity<?> handleException(NullValueException exception,
                                             WebRequest webRequest){
        log.info("An NullValueException is thrown");
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
        map.put("message", "Some arguments has null value and the request can't proceed.");
        map.put("data", errorDto);
        map.put("cause", "Certains arguments ont une valeur null et la requete ne peut etre execute sur une valeur null");
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordNotConfirmedException.class)
    public ResponseEntity<?> handleException(PasswordNotConfirmedException exception,
                                             WebRequest webRequest){
        log.info("An PasswordNotConfirmedException is thrown");
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
        map.put("message", "The password value must be equal to the Repassword to be validated.");
        map.put("data", errorDto);
        map.put("cause", "Le password n'a pas ete valide car est different de la valeur resaisi");
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

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<?> handleException(ModelNotFoundException exception,
                                             WebRequest webRequest){
        log.info("An ModelNotFoundException is thrown");
        final HttpStatus badRequest = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto =  ErrorDto.builder()
                .httpCode(badRequest.value())
                .exceptionMessage(exception.getMessage())
                .errorAppMessage(exception.getErrorCode())
                .errorList(exception.getErrors()!=null?(!exception.getErrors().isEmpty()?exception.getErrors().stream().toList():null):null)
                .build();
        Map<String, Object> map = new LinkedHashMap<>();
        map.clear();
        map.put("status", HttpStatus.NOT_FOUND);
        map.put("message", "Entity not found in the DB");
        map.put("data", errorDto);
        map.put("cause", "L'entite recherche n'a pas ete trouve");
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotDeleatableException.class)
    public ResponseEntity<?> handleException(EntityNotDeleatableException exception,
                                             WebRequest webRequest){
        log.info("An EntityNotDeleatableException is thrown");
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
        map.put("message", "Deleting the entity will cause some conflict");
        map.put("data", errorDto);
        map.put("cause", "La suppression de l'entite va causer des conflits");
        return new ResponseEntity(map, HttpStatus.CONFLICT);
    }

}
