package com.spring.jpa.spring.boot.jpa.models.service;

import com.spring.jpa.spring.boot.jpa.models.entity.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> findAll();
    public void save(Cliente cliente);
    public Cliente findOne(Long id);
    public void delete(Long id);
}
