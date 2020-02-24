package com.spring.jpa.spring.boot.jpa.models.dao;

import com.spring.jpa.spring.boot.jpa.models.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteDao {
    public List<Cliente> findAll();
    public void save(Cliente cliente);
}
