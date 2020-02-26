package com.spring.jpa.spring.boot.jpa.controllers;

import com.spring.jpa.spring.boot.jpa.models.dao.IClienteDao;
import com.spring.jpa.spring.boot.jpa.models.entity.Cliente;
import com.spring.jpa.spring.boot.jpa.models.service.IClienteService;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private IClienteService iClienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listar() {
        return new ResponseEntity<>(iClienteService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/form")
    public ResponseEntity<?> save(@RequestBody @Valid Cliente cliente, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }else {
            iClienteService.save(cliente);
            return new ResponseEntity<>("Cliente añadido", HttpStatus.OK);
        }
    }

    @PutMapping("/form/{id}")
    public ResponseEntity<?> editar(@RequestBody @Valid Cliente client, BindingResult result, @PathVariable Long id){
        if(id>0){
            if(iClienteService.findOne(id)!=null && !result.hasErrors()){
                iClienteService.save(client);
                return new ResponseEntity<>(client, HttpStatus.CREATED);
            }else if(result.hasErrors()){
                return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
            }
            else{
                return new ResponseEntity<>("El cliente con el id "+id+" no existe.", HttpStatus.BAD_REQUEST);
            }
        }else{
            return new ResponseEntity<>("El cliente no ha podido ser actualizado.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/form/{id}/delete")
    public ResponseEntity<?> eliminar(@PathVariable Long id) throws IllegalArgumentException {
        try {
            if (id > 0) {
                iClienteService.delete((long) 3);
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Hubo un fallo", HttpStatus.CONFLICT);
        }
        return null;
    }
}
