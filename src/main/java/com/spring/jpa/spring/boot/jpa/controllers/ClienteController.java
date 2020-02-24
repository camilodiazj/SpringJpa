package com.spring.jpa.spring.boot.jpa.controllers;

import com.spring.jpa.spring.boot.jpa.models.dao.IClienteDao;
import com.spring.jpa.spring.boot.jpa.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private IClienteDao iClienteDao;

    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listar(){
        return new ResponseEntity<List<Cliente>>(iClienteDao.findAll(),HttpStatus.OK);
    }
}
