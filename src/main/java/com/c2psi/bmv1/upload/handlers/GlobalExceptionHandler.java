package com.c2psi.bmv1.upload.handlers;

import com.c2psi.bmv1.upload.exceptions.BMException;
import com.c2psi.bmv1.upload.exceptions.UploadDirectoriesNotCreatedException;
import com.c2psi.bmv1.upload.exceptions.UploadFileException;
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

    @ExceptionHandler(UploadDirectoriesNotCreatedException.class)
    public ResponseEntity<?> handleException(UploadDirectoriesNotCreatedException exception,
                                             WebRequest webRequest){
        log.info("An UploadDirectoriesNotCreatedException is thrown");
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
        map.put("message", "The directories used in the uploaded process can't be created");
        map.put("data", errorDto);
        map.put("cause", "Les repertoires dans lesquel les upload seront effectue n'ont pas ete cree");
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UploadFileException.class)
    public ResponseEntity<?> handleException(UploadFileException exception,
                                             WebRequest webRequest){
        log.info("An UploadFileException is thrown");
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
        map.put("message", "Error during the uploaded process");
        map.put("data", errorDto);
        map.put("cause", "Erreur pendant le process d'upload d'image");
        return new ResponseEntity(map, HttpStatus.BAD_REQUEST);
    }

}
