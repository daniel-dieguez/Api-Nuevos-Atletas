package com.atletas.atletas.controller;


import com.atletas.atletas.dao.services.IAtletasServicesImpl;
import com.atletas.atletas.models.model.Atletas;
import jakarta.validation.Valid;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/atletas/v1/atletas")
public class AtletasController {

    @Autowired
    private IAtletasServicesImpl iAtletasServices;

    private Logger logger = LoggerFactory.getLogger(Atletas.class);

    @GetMapping
    public ResponseEntity<?> atletalista(){
        Map<String, Object> response = new HashMap <>();
        this.logger.debug("inica consulta");
        try{
            List<Atletas> atletas =  this.iAtletasServices.findAll();
            if(atletas == null && atletas.isEmpty()){
                logger.warn("No existe registro de entidad");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                logger.info("se ejecuta la consulta");
                return new ResponseEntity<List<Atletas>>(atletas, HttpStatus.OK);
            }
        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }catch(DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Atletas value, BindingResult result){
        Map<String, Object> response = new HashMap<>();
        if(result.hasErrors() ==true){
            List<String> errores = result.getFieldErrors().stream().map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList()); // getFielError me enlistara los campos que se encuentraron errores, El collector nos ayuda a enlistar lso errorres PREGUNTAR A CHATGPT
            response.put("errores", errores);
            logger.info("se encontraron errores al momento de validar");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try{
            Atletas atletas = new Atletas();
            atletas.setId_atleta(UUID.randomUUID().toString());
            atletas.setNombre(value.getNombre());
            atletas.setEmail(value.getEmail());
            atletas.setPassword(value.getPassword());
            atletas.setPago(value.getPago());
            logger.info("Se acaba de agregar nuevo atleta");
            response.put("mensaje", "Un nuevo atleta se ingreso");
            response.put("Atleta", atletas);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        }catch (CannotCreateTransactionException e){
            response = this.getTransactionExepcion(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }catch (DataAccessException e){
            response = this.getDataAccessException(response, e);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.SERVICE_UNAVAILABLE);

        }

    }


    private Map<String, Object> getTransactionExepcion(Map<String,Object> response, CannotCreateTransactionException e){
        logger.error("Error al momento de conectarse a la base de datos");
        response.put("mensajee", "error al moneotno de contectarse a la");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;
    }

    private Map<String, Object> getDataAccessException(Map<String, Object> response, DataAccessException e){
        logger.error("El error al momento de ejecutlar la consulta ea  la base d adatos");
        response.put("mensaje", "Error al momenot de ejecutar ola consulta a la base de datos");
        response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
        return response;

    }

}
