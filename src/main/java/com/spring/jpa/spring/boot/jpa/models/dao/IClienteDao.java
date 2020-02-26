package com.spring.jpa.spring.boot.jpa.models.dao;

import com.spring.jpa.spring.boot.jpa.models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IClienteDao extends CrudRepository<Cliente,Long> {
}
